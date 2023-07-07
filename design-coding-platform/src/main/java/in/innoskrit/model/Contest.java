package in.innoskrit.model;

import in.innoskrit.constant.ContestStatus;
import in.innoskrit.constant.Difficulty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Contest {
    private Long id;
    private String name;
    private Difficulty difficulty;
    private ContestStatus status;
    private List<Question> questions;
}
