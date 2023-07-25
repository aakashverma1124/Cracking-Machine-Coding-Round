package in.innoskrit.service;

import in.innoskrit.model.ExpenseType;
import in.innoskrit.model.expense.ExpenseMetaData;
import in.innoskrit.model.split.Split;
import in.innoskrit.repository.ExpenseRepository;

import java.util.List;
import java.util.Map;

public class ExpenseService {
    private ExpenseRepository expenseRepository;

    public ExpenseService() {
        expenseRepository = new ExpenseRepository();
    }

    public void createExpense(ExpenseType expenseType, double amount, String expenseBy, List<Split> splits, ExpenseMetaData expenseMetaData) {
        expenseRepository.createExpense(expenseType, amount, expenseBy, splits, expenseMetaData);
    }

    public void showBalanceByUser(String userId) {
        Map<String, Double> balance = expenseRepository.showBalanceByUser(userId);
    }

    public Map<String, Map<String, Double>> showBalance() {
        return expenseRepository.showBalance();
    }

}
