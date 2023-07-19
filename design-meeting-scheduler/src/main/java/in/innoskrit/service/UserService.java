package in.innoskrit.service;

import in.innoskrit.exception.UserNotFoundException;
import in.innoskrit.model.*;
import in.innoskrit.repository.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public void addUsers(List<User> users) {
        userRepository.addUsers(users);
    }
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUser(String email) {
        try {
            return userRepository.getUser(email);
        } catch (UserNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    public MeetingInviteResponse respondInvitation(Notification notification) {
        return MeetingInviteResponse.ACCEPT;
    }

    public List<Meeting> getMeetings(User user) {
        return userRepository.getMeetings(user);
    }

    public Meeting createBooking(List<User> participants, Interval interval, MeetingRoom meetingRoom) {
        Meeting meeting = MeetingScheduler.getMeetingScheduler().scheduleMeeting(participants, interval, meetingRoom);
        return meeting;
    }
}
