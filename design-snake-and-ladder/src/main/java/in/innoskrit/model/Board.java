package in.innoskrit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Board {
    private final int size;
    private final List<Snake> snakes;
    private final List<Ladder> ladders;
}
