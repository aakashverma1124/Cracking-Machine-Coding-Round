package in.innoskrit.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Screen {
    private final String id;
    private final String name;
    private final Cinema cinema;
    //Other screen metadata.

    private final List<Seat> seats;

    public Screen(@NonNull final String id, @NonNull final String name, @NonNull final Cinema cinema) {
        this.id = id;
        this.name = name;
        this.cinema = cinema;
        this.seats = new ArrayList<>();
    }

    public void addSeat(@NonNull final Seat seat) {
        seats.add(seat);
    }
}
