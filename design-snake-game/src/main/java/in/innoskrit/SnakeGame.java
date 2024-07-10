package in.innoskrit;

import java.util.LinkedHashSet;

public class SnakeGame {
    private final int width;
    private final int height;
    LinkedHashSet<Pair> snake;
    LinkedHashSet<Pair> food;

    private int snakeX;
    private int snakeY;

    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.snake = new LinkedHashSet<>();
        this.food = new LinkedHashSet<>();
        for(int[] f : food) {
            this.food.add(new Pair(f[0], f[1]));
        }
        this.snake.add(new Pair(0, 0));
        this.snakeX = this.snakeY = 0;
    }

    private boolean checkForSnakeOrOutOfBound(int row, int col) {
        if(snake.contains(new Pair(row, col))) return true;
        return row < 0 || row >= height || col < 0 || col >= width;
    }

    private void updateSnakeSize(int row, int col) {
        Pair currentFood = food.stream().findFirst().get();
        snake.add(new Pair(row, col));
        if(currentFood.equals(new Pair(row, col))) {
            food.remove(currentFood);
        } else {
            Pair lastSnakePosition = snake.stream().findFirst().get();
            snake.remove(lastSnakePosition);
        }
    }

    public int move(String direction) {
        switch (direction) {
            case "U":
                if(checkForSnakeOrOutOfBound(snakeX - 1, snakeY)) return -1;
                snakeX -= 1;
                updateSnakeSize(snakeX, snakeY);
                break;
            case "D":
                if(checkForSnakeOrOutOfBound(snakeX + 1, snakeY)) return -1;
                snakeX += 1;
                updateSnakeSize(snakeX, snakeY);
                break;
            case "L":
                if(checkForSnakeOrOutOfBound(snakeX, snakeY - 1)) return -1;
                snakeY -= 1;
                updateSnakeSize(snakeX, snakeY);
                break;
            case "R":
                if(checkForSnakeOrOutOfBound(snakeX, snakeY + 1)) return -1;
                snakeY += 1;
                updateSnakeSize(snakeX, snakeY);
                break;
            default:
                System.out.println("Invalid Input");
                return -1;
        }
        return snake.size() - 1;
    }
}
