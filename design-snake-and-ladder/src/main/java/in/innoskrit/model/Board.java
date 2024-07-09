package in.innoskrit.model;

import java.util.List;

public class Board {
    private int size;
    private Dice dice;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    private List<Player> players;

    public Board(int size, Dice dice, List<Snake> snakes, List<Ladder> ladders, List<Player> players) {
        this.size = size;
        this.dice = dice;
        this.snakes = snakes;
        this.ladders = ladders;
        this.players = players;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public void setLadders(List<Ladder> ladders) {
        this.ladders = ladders;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
