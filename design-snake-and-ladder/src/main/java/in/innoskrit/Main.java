package in.innoskrit;

import in.innoskrit.model.*;
import in.innoskrit.service.GameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private Board setUpBoard() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter board size: ");
        int boardSize = Integer.parseInt(reader.readLine().trim());

        System.out.print("Enter number of dice: ");
        int numberOfDice = Integer.parseInt(reader.readLine().trim());
        Dice dice = new Dice(numberOfDice);

        System.out.print("Enter number of players: ");
        int numberOfPlayers = Integer.parseInt(reader.readLine().trim());

        System.out.println("Enter player details");
        List<Player> players = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++) {
            String name = reader.readLine().trim();
            players.add(new Player(name));
        }

        System.out.print("Enter number of snakes: ");
        int numberOfSnakes = Integer.parseInt(reader.readLine().trim());

        System.out.println("Enter snake details");
        List<Snake> snakes = new ArrayList<>();
        for(int i = 0; i < numberOfSnakes; i++) {
            String[] snakePositions = reader.readLine().trim().split("\\s+");
            snakes.add(new Snake(Integer.parseInt(snakePositions[0]), Integer.parseInt(snakePositions[1])));
        }

        System.out.print("Enter number of ladders: ");
        int numberOfLadders = Integer.parseInt(reader.readLine().trim());

        System.out.println("Enter ladder details");
        List<Ladder> ladders = new ArrayList<>();
        for(int i = 0; i < numberOfLadders; i++) {
            String[] ladderPositions = reader.readLine().trim().split("\\s+");
            ladders.add(new Ladder(Integer.parseInt(ladderPositions[0]), Integer.parseInt(ladderPositions[1])));
        }

        return new Board(boardSize, dice, snakes, ladders, players);
    }
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        GameService gameService = new GameService();

        Board board = main.setUpBoard();
        gameService.startGame(board);
    }
}