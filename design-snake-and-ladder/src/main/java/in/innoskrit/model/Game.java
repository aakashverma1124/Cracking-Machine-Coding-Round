package in.innoskrit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Game {
    private final Board board;
    private final List<Player> players;
    private final int numberOfDice;
    private final boolean isMultiplayerWinnerGame;
}
