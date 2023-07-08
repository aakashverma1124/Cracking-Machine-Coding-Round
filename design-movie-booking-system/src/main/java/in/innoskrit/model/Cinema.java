package in.innoskrit.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Cinema {
    private final String id;
    private final String name;
    private final List<Screen> screens;
    private final City city;

    public Cinema(@NonNull final String id, @NonNull final String name, @NonNull final City city) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.screens = new ArrayList<>();
    }

    public void addScreen(@NonNull final Screen screen) {
        screens.add(screen);
    }
}
