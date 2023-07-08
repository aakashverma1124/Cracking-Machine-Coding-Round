package in.innoskrit.service;

import in.innoskrit.model.Game;
import in.innoskrit.model.Player;

import java.util.LinkedList;
import java.util.Queue;

public class GameService {

    private Game game;
    private Queue<Player> playerSequence;
    private boolean isGameOver;
    private DiceService diceService;
    private BoardService boardService;

    public GameService(Game game) {
        this.game = game;
        playerSequence = new LinkedList<>(game.getPlayers());
        this.diceService = new DiceService();
        this.boardService = new BoardService(game.getBoard());
    }

    public void startGame() {

        System.out.println("Game Starts");
        System.out.println("Initial Positions of Players:");
        for(Player player : game.getPlayers()) {
            System.out.println(player.getName() + " : " + player.getCurrentPosition());
        }

        while(!isGameOver) {
            Player player = playerSequence.poll();
            int diceValue = diceService.rollDice();
            int playerCurrentPosition = player.getCurrentPosition();
            int playerNewPosition = playerCurrentPosition + diceValue;

            System.out.println(player.getName() + " rolled dice and got " + diceValue);

            if(playerNewPosition > 100) {
                playerNewPosition = playerCurrentPosition;
                System.out.println(player.getName() + " reached at " + playerNewPosition);
            } else if(playerNewPosition == game.getBoard().getSize()) {
                this.isGameOver = true;
                System.out.println(player.getName() + " has won the game.");
            } else {
                int playerUpdatedPosition = boardService.checkIfSnakeLadderPresent(playerNewPosition);
                if(playerUpdatedPosition < playerNewPosition) {
                    System.out.println(player.getName() + " got bit by Snake.");
                } else if (playerUpdatedPosition > playerNewPosition) {
                    System.out.println(player.getName() + " got Ladder.");
                }
                player.setCurrentPosition(playerUpdatedPosition);
                System.out.println(player.getName() + " reached at " + playerUpdatedPosition);
            }
            playerSequence.add(player);
        }
    }


}
