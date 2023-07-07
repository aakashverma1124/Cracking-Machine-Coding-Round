package in.innoskrit.model;

import in.innoskrit.constant.Difficulty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Question {
    private Long id;
    private String question;
    private Difficulty difficulty;
    private int score;
}
