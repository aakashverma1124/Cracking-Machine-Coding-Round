package in.innoskrit.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Player {
    private final String id;
    private final String name;

    public Player(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
