package in.innoskrit.model.expense;

import in.innoskrit.model.User;
import in.innoskrit.model.split.PercentageSplit;
import in.innoskrit.model.split.Split;

import java.util.List;

public class PercentageExpense extends Expense {

    public PercentageExpense(double amount, String expenseBy, List<Split> splits, ExpenseMetaData expenseData) {
        super(amount, expenseBy, splits, expenseData);
    }

    @Override
    public boolean validate() {
        double totalSplitPercent = 0;
        for(Split split : getSplits()) {
            if(!(split instanceof PercentageSplit)) return false;
            PercentageSplit percentageSplit = (PercentageSplit) split;
            totalSplitPercent += percentageSplit.getPercent();
        }
        return totalSplitPercent == 100;
    }
}
