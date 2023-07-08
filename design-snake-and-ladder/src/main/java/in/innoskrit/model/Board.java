package in.innoskrit.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Board {
    private final int size;
    private final Map<String, Integer> players;
    private final List<Snake> snakes;
    private final List<Ladder> ladders;

    public Board(int size) {
        this.size = size;
        players = new HashMap<>();
        snakes = new ArrayList<>();
        ladders = new ArrayList<>();
    }
}
