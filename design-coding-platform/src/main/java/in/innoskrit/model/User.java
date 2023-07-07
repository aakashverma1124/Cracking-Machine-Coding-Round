package in.innoskrit.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class User {
    private Long id;
    private String username;
    private int score;
    private List<Contest> contests;
}
