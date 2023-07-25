package in.innoskrit.model.split;

import in.innoskrit.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExactSplit extends Split {
    double amount;
    public ExactSplit(String userId, double amount) {
        super(userId);
        this.amount = amount;
    }
}
