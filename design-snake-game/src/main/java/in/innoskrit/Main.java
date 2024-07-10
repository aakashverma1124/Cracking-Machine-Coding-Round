package in.innoskrit;

public class Main {

    public static void main(String[] args) {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        SnakeGame snakeGame = new SnakeGame(3, 2, new int[][]{{1, 2}, {0, 1}});
        System.out.println(snakeGame.move("R")); // return 0
        System.out.println(snakeGame.move("D")); // return 0
        System.out.println(snakeGame.move("R")); // return 1, snake eats the first piece of food. The second piece of food appears at (0, 1).
        System.out.println(snakeGame.move("U")); // return 1
        System.out.println(snakeGame.move("L")); // return 2, snake eats the second food. No more food appears.
        System.out.println(snakeGame.move("U")); // return -1, game over because snake collides with border

    }
}