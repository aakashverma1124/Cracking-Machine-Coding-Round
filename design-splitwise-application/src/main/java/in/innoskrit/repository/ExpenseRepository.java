package in.innoskrit.repository;

import in.innoskrit.model.ExpenseType;
import in.innoskrit.model.expense.*;
import in.innoskrit.model.split.EqualSplit;
import in.innoskrit.model.split.ExactSplit;
import in.innoskrit.model.split.PercentageSplit;
import in.innoskrit.model.split.Split;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseRepository {
    Map<String, Expense> expenseMap;
    Map<String, Map<String, Double>> balanceSheet;

    public ExpenseRepository() {
        this.expenseMap = new HashMap<String, Expense>();
        this.balanceSheet = new HashMap<String, Map<String, Double>>();
    }

    public void createExpense(ExpenseType expenseType, double amount, String expenseBy, List<Split> splits, ExpenseMetaData expenseMetaData) {
        Expense expense = null;
        if (expenseType == ExpenseType.EQUAL) {
            expense = new EqualExpense(amount, expenseBy, splits, expenseMetaData);
            expense.validate();
            expenseMap.put(expense.getId(), expense);
            if(!balanceSheet.containsKey(expenseBy))
                balanceSheet.put(expenseBy, new HashMap<String, Double>());
            Map<String, Double> map = balanceSheet.get(expenseBy);
            for(Split split : splits) {
                EqualSplit equalSplit = (EqualSplit) split;
                if(!map.containsKey(equalSplit.getUserId()))
                    map.put(equalSplit.getUserId(), 0.0);
                map.put(equalSplit.getUserId(), map.get(equalSplit.getUserId()) + amount/splits.size());
            }
        } else if(expenseType == ExpenseType.EXACT) {
            expense = new ExactExpense(amount, expenseBy, splits, expenseMetaData);
            expense.validate();
            expenseMap.put(expense.getId(), expense);
            if(!balanceSheet.containsKey(expenseBy))
                balanceSheet.put(expenseBy, new HashMap<String, Double>());
            Map<String, Double> map = balanceSheet.get(expenseBy);
            for(Split split : splits) {
                ExactSplit exactSplit = (ExactSplit) split;
                if(!map.containsKey(exactSplit.getUserId()))
                    map.put(exactSplit.getUserId(), 0.0);
                map.put(exactSplit.getUserId(), map.get(exactSplit.getUserId()) + exactSplit.getAmount());
            }
        } else {
            expense = new PercentageExpense(amount, expenseBy, splits, expenseMetaData);
            expense.validate();
            expenseMap.put(expense.getId(), expense);
            if(!balanceSheet.containsKey(expenseBy))
                balanceSheet.put(expenseBy, new HashMap<String, Double>());
            Map<String, Double> map = balanceSheet.get(expenseBy);
            for(Split split : splits) {
                PercentageSplit percentageSplit = (PercentageSplit) split;
                if(!map.containsKey(percentageSplit.getUserId()))
                    map.put(percentageSplit.getUserId(), 0.0);
                map.put(percentageSplit.getUserId(), map.get(percentageSplit.getUserId()) + (percentageSplit.getPercent()*amount)/100);
            }
        }
    }

    public Map<String, Double> showBalanceByUser(String userId) {
        return balanceSheet.get(userId);
    }

    public Map<String, Map<String, Double>> showBalance() {
        return balanceSheet;
    }


}
