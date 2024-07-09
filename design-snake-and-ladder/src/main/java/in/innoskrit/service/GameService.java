package in.innoskrit.service;

import in.innoskrit.model.Board;
import in.innoskrit.model.Ladder;
import in.innoskrit.model.Player;
import in.innoskrit.model.Snake;

import java.util.LinkedList;
import java.util.Queue;

public class GameService {
    private Board board;

    public GameService() {
    }

    public void startGame(Board board) {
        this.board = board;
        boolean isGameOver = false;
        Queue<Player> queue = new LinkedList<>();
        for(Player player : board.getPlayers()) {
            queue.offer(player);
        }

        while(!isGameOver) {
            Player currentPlayer = queue.poll();
            int move = board.getDice().rollDice();
            assert currentPlayer != null;
            int newPosition = currentPlayer.getCurrentPosition() + move;

            if(newPosition > board.getSize()) {
                queue.offer(currentPlayer);
                continue;
            }

            for(Snake snake : board.getSnakes()) {
                if(snake.getStart() == newPosition) {
                    newPosition = snake.getEnd();
                }
            }

            for(Ladder ladder : board.getLadders()) {
                if(ladder.getStart() == newPosition) {
                    newPosition = ladder.getEnd();
                }
            }

            System.out.println(currentPlayer.getName() + " rolled a " + move + " and moved from " + currentPlayer.getCurrentPosition() + " to " + newPosition);

            currentPlayer.setCurrentPosition(newPosition);
            if(currentPlayer.getCurrentPosition() == board.getSize()) {
                System.out.println(currentPlayer.getName() + " wins the game");
                isGameOver = true;
            }

            queue.offer(currentPlayer);
        }
    }
}
