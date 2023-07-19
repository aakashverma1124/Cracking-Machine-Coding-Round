package in.innoskrit.service;

import in.innoskrit.model.Interval;
import in.innoskrit.model.Meeting;
import in.innoskrit.model.MeetingRoom;
import in.innoskrit.model.User;

import java.util.List;

public class MeetingScheduler {
    private static MeetingScheduler meetingScheduler;

    private MeetingScheduler() {

    }

    public static MeetingScheduler getMeetingScheduler() {
        if(meetingScheduler == null) {
            meetingScheduler = new MeetingScheduler();
        }
        return meetingScheduler;
    }

    public Meeting scheduleMeeting(List<User> users, Interval interval, MeetingRoom meetingRoom) {
        for(User user : users) {
            // TODO
        }
        return null;
    }

    public void cancelMeeting(Meeting meeting) {

    }

    public Meeting updateMeeting(Meeting meeting, List<User> users, Interval interval, MeetingRoom meetingRoom) {
        return null;
    }


}
