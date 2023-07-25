package in.innoskrit.model.expense;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ExpenseMetaData {
    private String title;
    private String description;
}
