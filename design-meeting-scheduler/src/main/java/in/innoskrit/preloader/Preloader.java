package in.innoskrit.preloader;

import in.innoskrit.service.MeetingRoomService;
import in.innoskrit.service.UserService;

public class Preloader {

    public static void preload() {
        UserService userService = new UserService();
        userService.addUsers(DummyUserCreator.createUsers());

        MeetingRoomService meetingRoomService = new MeetingRoomService();
        meetingRoomService.addRooms(DummyMeetingRoomsCreator.createRooms());
    }

}
