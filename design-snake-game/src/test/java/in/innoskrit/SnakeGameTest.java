package in.innoskrit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SnakeGameTest {

    @Test
    public void testRightMove() {
        SnakeGame snakeGame = new SnakeGame(3, 2, new int[][]{{1, 2}, {0, 1}});
        assertEquals(0, snakeGame.move("R"));
    }

    @Test
    public void testDownMove() {
        SnakeGame snakeGame = new SnakeGame(3, 2, new int[][]{{1, 2}, {0, 1}});
        snakeGame.move("R");
        assertEquals(0, snakeGame.move("D"));
    }

    @Test
    public void testRightMoveAndEatFood() {
        SnakeGame snakeGame = new SnakeGame(3, 2, new int[][]{{1, 2}, {0, 1}});
        snakeGame.move("R");
        snakeGame.move("D");
        assertEquals(1, snakeGame.move("R"));
    }

    @Test
    public void testOutOfBound() {
        SnakeGame snakeGame = new SnakeGame(3, 2, new int[][]{{1, 2}, {0, 1}});
        snakeGame.move("R");
        snakeGame.move("D");
        assertEquals(-1, snakeGame.move("D"));
    }
}
