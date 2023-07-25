package in.innoskrit.model.expense;

import in.innoskrit.model.User;
import in.innoskrit.model.split.Split;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public abstract class Expense {
    private final String id;
    private double amount;
    private String expenseBy;
    private List<Split> splits;
    private ExpenseMetaData expenseData;

    public Expense(double amount, String expenseBy, List<Split> splits, ExpenseMetaData expenseData) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.expenseBy = expenseBy;
        this.splits = splits;
        this.expenseData = expenseData;
    }

    public abstract boolean validate();
}
