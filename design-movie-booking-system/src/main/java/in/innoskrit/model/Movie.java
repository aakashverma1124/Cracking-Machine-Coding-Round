package in.innoskrit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Movie {
    private final String id;
    private final String title;
    private final String genre;
    private final LocalDateTime releaseDate;
    private final String language;
    private final Integer durationInSeconds;
}
