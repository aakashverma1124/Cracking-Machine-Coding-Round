package in.innoskrit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Doctor {
    private Integer id;
    private String name;
    private Map<TimeSlot, SlotAvailability> slots;
    private Specialization specialization;
    private Double rating;
}
