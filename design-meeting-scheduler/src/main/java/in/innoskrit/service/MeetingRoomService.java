package in.innoskrit.service;

import in.innoskrit.model.MeetingRoom;
import in.innoskrit.repository.MeetingRoomRepository;

import java.util.List;

public class MeetingRoomService {

    private MeetingRoomRepository meetingRoomRepository;

    public MeetingRoomService() {
        meetingRoomRepository = new MeetingRoomRepository();
    }

    public void addRoom(MeetingRoom room) {
        meetingRoomRepository.addRoom(room);
    }

    public void addRooms(List<MeetingRoom> roomList) {
        meetingRoomRepository.addRooms(roomList);
    }

    public List<MeetingRoom> getRooms() {
        return meetingRoomRepository.getRooms();
    }

}
