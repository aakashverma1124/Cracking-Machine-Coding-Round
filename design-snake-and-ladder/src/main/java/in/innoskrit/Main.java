package in.innoskrit;

import in.innoskrit.model.*;
import in.innoskrit.service.GameService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Board Size: ");
        int boardSize = Integer.parseInt(reader.readLine());

        System.out.print("Enter Number Of Players: ");
        int numberOfPlayers = Integer.parseInt(reader.readLine());

        List<Player> players = new ArrayList<>();

        System.out.println("--Enter Player Details--");
        for(int i = 0; i < numberOfPlayers; i++) {
            String playerName = reader.readLine();
            players.add(new Player(playerName));
        }

        System.out.print("Enter Number Of Snakes: ");
        int numberOfSnakes = Integer.parseInt(reader.readLine());

        List<Snake> snakes = new ArrayList<>();

        System.out.println("--Enter Snake Positions--");
        for(int i = 0; i < numberOfSnakes; i++) {
            String[] snakePositions = reader.readLine().trim().split("\\s+");
            snakes.add(new Snake(Integer.parseInt(snakePositions[0]), Integer.parseInt(snakePositions[1])));
        }

        System.out.print("Enter Number Of Ladders: ");
        int numberOfLadders = Integer.parseInt(reader.readLine());

        List<Ladder> ladders = new ArrayList<>();

        System.out.println("--Enter Ladder Positions--");
        for(int i = 0; i < numberOfLadders; i++) {
            String[] ladderPositions = reader.readLine().trim().split("\\s+");
            ladders.add(new Ladder(Integer.parseInt(ladderPositions[0]), Integer.parseInt(ladderPositions[1])));
        }

        Board board = new Board(boardSize, snakes, ladders);
        GameService gameService = new GameService(new Game(board, players, 1, false));
        gameService.startGame();
    }
}