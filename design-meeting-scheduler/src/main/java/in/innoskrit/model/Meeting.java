package in.innoskrit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Meeting {
    private int id;
    private List<User> participants;
    private Interval interval;
    private MeetingRoom meetingRoom;
    private User bookedBy;
}
