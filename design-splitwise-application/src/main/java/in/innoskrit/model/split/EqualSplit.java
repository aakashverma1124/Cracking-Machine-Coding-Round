package in.innoskrit.model.split;

import in.innoskrit.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EqualSplit extends Split {
    public EqualSplit(String userId) {
        super(userId);
    }
}
