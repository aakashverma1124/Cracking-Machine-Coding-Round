package in.innoskrit.repository;

import in.innoskrit.model.expense.Expense;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExpenseRepository {
    Map<String, Expense> expenseMap;
    // balanceSheet.get(a).get(b) is the amount b owes a. It is negative when a owes b.
    Map<String, Map<String, Double>> balanceSheet;

    public ExpenseRepository() {
        this.expenseMap = new HashMap<>();
        this.balanceSheet = new HashMap<>();
    }

    public void addExpense(Expense expense) {
        expenseMap.put(expense.getId(), expense);
    }

    public double getBalance(String userId, String otherUserId) {
        Map<String, Double> balances = balanceSheet.get(userId);
        if (balances == null || !balances.containsKey(otherUserId)) return 0.0;
        return balances.get(otherUserId);
    }

    public void updateBalance(String userId, String otherUserId, double amount) {
        if (!balanceSheet.containsKey(userId))
            balanceSheet.put(userId, new HashMap<>());
        balanceSheet.get(userId).put(otherUserId, amount);
    }

    public Map<String, Double> getBalancesByUser(String userId) {
        if (!balanceSheet.containsKey(userId)) return Collections.emptyMap();
        return Collections.unmodifiableMap(balanceSheet.get(userId));
    }

    public Map<String, Map<String, Double>> getBalanceSheet() {
        return Collections.unmodifiableMap(balanceSheet);
    }

}
