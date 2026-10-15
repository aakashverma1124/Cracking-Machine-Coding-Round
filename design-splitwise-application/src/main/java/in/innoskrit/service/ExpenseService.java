package in.innoskrit.service;

import in.innoskrit.exception.InvalidExpenseException;
import in.innoskrit.model.ExpenseType;
import in.innoskrit.model.expense.*;
import in.innoskrit.model.split.ExactSplit;
import in.innoskrit.model.split.PercentageSplit;
import in.innoskrit.model.split.Split;
import in.innoskrit.repository.ExpenseRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    public ExpenseService(ExpenseRepository expenseRepository, UserService userService) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
    }

    public void createExpense(ExpenseType expenseType, double amount, String expenseBy, List<Split> splits, ExpenseMetaData expenseMetaData) {
        validateUsers(expenseBy, splits);
        if (amount <= 0) throw new InvalidExpenseException("Expense amount must be positive.");

        Expense expense = buildExpense(expenseType, amount, expenseBy, splits, expenseMetaData);
        if (!expense.validate()) throw new InvalidExpenseException(validationMessage(expenseType, amount));
        expenseRepository.addExpense(expense);

        long[] shares = calculateSharesInPaise(expenseType, amount, splits);
        for (int i = 0; i < splits.size(); i++) {
            addDebt(expenseBy, splits.get(i).getUserId(), shares[i]);
        }
    }

    public void showBalanceByUser(String userId) {
        userService.getUser(userId);
        List<String> balances = new ArrayList<>();
        for (Map.Entry<String, Double> entry : expenseRepository.getBalancesByUser(userId).entrySet()) {
            if (entry.getValue() > 0) balances.add(formatBalance(entry.getKey(), userId, entry.getValue()));
            else if (entry.getValue() < 0) balances.add(formatBalance(userId, entry.getKey(), -entry.getValue()));
        }
        printBalances(balances);
    }

    public void showBalance() {
        List<String> balances = new ArrayList<>();
        for (Map.Entry<String, Map<String, Double>> userEntry : expenseRepository.getBalanceSheet().entrySet()) {
            for (Map.Entry<String, Double> entry : userEntry.getValue().entrySet()) {
                if (entry.getValue() > 0) balances.add(formatBalance(entry.getKey(), userEntry.getKey(), entry.getValue()));
            }
        }
        printBalances(balances);
    }

    private void validateUsers(String expenseBy, List<Split> splits) {
        if (splits == null || splits.isEmpty())
            throw new InvalidExpenseException("Expense must be split with at least one user.");
        userService.getUser(expenseBy);
        Set<String> userIds = new HashSet<>();
        for (Split split : splits) {
            userService.getUser(split.getUserId());
            if (!userIds.add(split.getUserId()))
                throw new InvalidExpenseException("A user can appear only once in an expense.");
        }
    }

    private Expense buildExpense(ExpenseType expenseType, double amount, String expenseBy, List<Split> splits, ExpenseMetaData expenseMetaData) {
        switch (expenseType) {
            case EQUAL:
                return new EqualExpense(amount, expenseBy, splits, expenseMetaData);
            case EXACT:
                return new ExactExpense(amount, expenseBy, splits, expenseMetaData);
            case PERCENTAGE:
                return new PercentageExpense(amount, expenseBy, splits, expenseMetaData);
            default:
                throw new InvalidExpenseException("Unsupported expense type " + expenseType);
        }
    }

    private String validationMessage(ExpenseType expenseType, double amount) {
        switch (expenseType) {
            case EXACT:
                return "Sum of exact amounts must be equal to the total amount " + formatAmount(amount) + ".";
            case PERCENTAGE:
                return "Sum of percentages must be equal to 100.";
            default:
                return "Invalid " + expenseType + " expense.";
        }
    }

    // Shares are calculated in paise to avoid floating point errors. The first user gets whatever is left after
    // rounding, so 100 split equally among 3 users becomes 33.34, 33.33 and 33.33.
    private long[] calculateSharesInPaise(ExpenseType expenseType, double amount, List<Split> splits) {
        long totalInPaise = toPaise(amount);
        long[] shares = new long[splits.size()];
        long assigned = 0;
        for (int i = 1; i < splits.size(); i++) {
            Split split = splits.get(i);
            if (expenseType == ExpenseType.EQUAL) {
                shares[i] = totalInPaise / splits.size();
            } else if (expenseType == ExpenseType.EXACT) {
                shares[i] = toPaise(((ExactSplit) split).getAmount());
            } else {
                shares[i] = Math.round(totalInPaise * ((PercentageSplit) split).getPercent() / 100);
            }
            assigned += shares[i];
        }
        shares[0] = totalInPaise - assigned;
        return shares;
    }

    // Records that owedBy owes paidBy, netting it against what paidBy already owes owedBy.
    private void addDebt(String paidBy, String owedBy, long amountInPaise) {
        if (paidBy.equals(owedBy)) return;
        long balance = toPaise(expenseRepository.getBalance(paidBy, owedBy)) + amountInPaise;
        expenseRepository.updateBalance(paidBy, owedBy, balance / 100.0);
        expenseRepository.updateBalance(owedBy, paidBy, -balance / 100.0);
    }

    private void printBalances(List<String> balances) {
        if (balances.isEmpty()) {
            System.out.println("No balances");
            return;
        }
        Collections.sort(balances);
        for (String balance : balances) {
            System.out.println(balance);
        }
    }

    private String formatBalance(String owedBy, String owedTo, double amount) {
        return userService.getUser(owedBy).getUserName() + " owes " + userService.getUser(owedTo).getUserName()
                + ": " + formatAmount(amount);
    }

    private String formatAmount(double amount) {
        return String.format("%.2f", amount);
    }

    private long toPaise(double amount) {
        return Math.round(amount * 100);
    }

}
