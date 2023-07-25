package in.innoskrit.model.split;

import in.innoskrit.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Split {
    String userId;
    public Split(String userId) {
        this.userId = userId;
    }
}
