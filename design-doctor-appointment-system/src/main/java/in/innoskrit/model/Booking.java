package in.innoskrit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Booking {
    private Integer bookingId;
    private Doctor doctor;
    private Patient patient;
    private TimeSlot slot;
    private Boolean waitList;
}
