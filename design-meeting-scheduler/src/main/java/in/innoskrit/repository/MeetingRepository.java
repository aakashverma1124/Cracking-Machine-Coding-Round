package in.innoskrit.repository;

import in.innoskrit.model.Meeting;

import java.util.List;

public class MeetingRepository {
    private List<Meeting> meetings;

    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }
}
