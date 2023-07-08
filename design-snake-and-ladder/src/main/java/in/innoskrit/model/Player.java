package in.innoskrit.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Player {
    private final String id;
    private final String name;
    private int currentPosition;

    public Player(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.currentPosition = 0;
    }

    public void setCurrentPosition(int position) {
        this.currentPosition = position;
    }

}
