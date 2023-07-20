package in.innoskrit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.print.Doc;
import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Patient {
    private Integer id;
    private String name;
    Set<TimeSlot> bookedSlots;
}
