package in.innoskrit.model;

import java.util.Random;

public class Dice {
    private final int numberOfDice;

    public Dice(int diceNumber) {
        this.numberOfDice = diceNumber;
    }

    public int rollDice() {
        Random random = new Random();
        int min = this.numberOfDice;
        int max = this.numberOfDice * 6;
        return random.nextInt((max - min) + 1) + min;
    }
}
