package in.innoskrit.model.expense;

import in.innoskrit.model.User;
import in.innoskrit.model.split.EqualSplit;
import in.innoskrit.model.split.Split;

import java.util.List;

public class EqualExpense extends Expense {

    public EqualExpense(double amount, String expenseBy, List<Split> splits, ExpenseMetaData expenseData) {
        super(amount, expenseBy, splits, expenseData);
    }

    @Override
    public boolean validate() {
        for(Split split : getSplits()) {
            if(!(split instanceof EqualSplit)) return false;
        }
        return true;
    }
}
