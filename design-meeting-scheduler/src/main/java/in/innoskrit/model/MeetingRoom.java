package in.innoskrit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MeetingRoom {
    private int id;
    private int capacity;
    private boolean isAvailable;
    private List<Meeting> meetings;
}
