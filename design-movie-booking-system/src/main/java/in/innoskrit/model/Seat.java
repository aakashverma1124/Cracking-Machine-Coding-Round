package in.innoskrit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Seat {
    private final String id;
    private final int rowNumber;
    private final int seatNumber;
}
