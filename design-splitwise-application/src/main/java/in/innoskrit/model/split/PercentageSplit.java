package in.innoskrit.model.split;

import in.innoskrit.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PercentageSplit extends Split {
    double percent;
    public PercentageSplit(String userId, double percent) {
        super(userId);
        this.percent = percent;
    }
}
