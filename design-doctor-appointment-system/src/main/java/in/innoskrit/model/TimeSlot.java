package in.innoskrit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class TimeSlot {
    private LocalDateTime start;
    private LocalDateTime end;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeSlot)) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return start.equals(timeSlot.start) && end.equals(timeSlot.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
