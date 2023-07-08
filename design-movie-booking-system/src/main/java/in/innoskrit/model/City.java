package in.innoskrit.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class City {
    private final String name;
    private final String state;
    private final int zipCode;
    private final List<Cinema> cinemas;

    public City(@NonNull final String name, @NonNull final String state, @NonNull final int zipCode) {
        this.name = name;
        this.state = state;
        this.zipCode = zipCode;
        this.cinemas = new ArrayList<>();
    }

    public void addCinema(Cinema cinema) {
        cinemas.add(cinema);
    }
}
