package in.innoskrit.service;

import java.util.Random;

public class DiceService {
    private int diceValue;

    public int rollDice() {
        diceValue = new Random().nextInt(6) + 1;
        return diceValue;
    }
}
