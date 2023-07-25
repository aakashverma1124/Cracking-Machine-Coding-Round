package in.innoskrit.model.expense;

import in.innoskrit.model.User;
import in.innoskrit.model.split.ExactSplit;
import in.innoskrit.model.split.Split;

import java.util.List;

public class ExactExpense extends Expense {

    public ExactExpense(double amount, String expenseBy, List<Split> splits, ExpenseMetaData expenseData) {
        super(amount, expenseBy, splits, expenseData);
    }

    @Override
    public boolean validate() {
        double totalSplit = 0;
        for(Split split : getSplits()) {
            if(!(split instanceof ExactSplit)) return false;
            ExactSplit exactSplit = (ExactSplit) split;
            totalSplit += exactSplit.getAmount();
        }
        return getAmount() == totalSplit;
    }
}
