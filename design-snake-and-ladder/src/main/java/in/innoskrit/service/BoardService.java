package in.innoskrit.service;

import in.innoskrit.model.Board;
import in.innoskrit.model.Ladder;
import in.innoskrit.model.Snake;

import java.util.stream.Stream;

public class BoardService {
    private Board board;

    public BoardService(Board board) {
        this.board = board;
    }

    // Assuming snake and ladder will never collide, neither at start nor at the end.
    public int checkIfSnakeLadderPresent(int position) {
        for(Snake snake : board.getSnakes()) {
            if(snake.getStart() == position) return snake.getEnd();
        }
        for(Ladder ladder : board.getLadders()) {
            if(ladder.getStart() == position) return ladder.getEnd();
        }
        return position;
    }

}
