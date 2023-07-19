package in.innoskrit.preloader;

import in.innoskrit.model.MeetingRoom;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingRoomsCreator {
    public static List<MeetingRoom> createRooms() {
        List<MeetingRoom> meetingRooms = new ArrayList<>();
        MeetingRoom room1 = new MeetingRoom(1, 10, true, new ArrayList<>());
        MeetingRoom room2 = new MeetingRoom(1, 20, true, new ArrayList<>());
        MeetingRoom room3 = new MeetingRoom(1, 13, true, new ArrayList<>());
        MeetingRoom room4 = new MeetingRoom(1, 15, true, new ArrayList<>());
        MeetingRoom room5 = new MeetingRoom(1, 30, true, new ArrayList<>());
        meetingRooms.add(room1);
        meetingRooms.add(room2);
        meetingRooms.add(room3);
        meetingRooms.add(room4);
        meetingRooms.add(room5);
        return meetingRooms;
    }
}
