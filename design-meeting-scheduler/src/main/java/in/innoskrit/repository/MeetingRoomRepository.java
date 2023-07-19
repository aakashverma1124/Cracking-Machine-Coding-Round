package in.innoskrit.repository;

import in.innoskrit.model.MeetingRoom;

import java.util.List;

public class MeetingRoomRepository {
    private List<MeetingRoom> rooms;

    public void addRoom(MeetingRoom room) {
        rooms.add(room);
    }

    public void addRooms(List<MeetingRoom> roomList) {
        rooms.addAll(rooms);
    }

    public List<MeetingRoom> getRooms() {
        return rooms;
    }
}
