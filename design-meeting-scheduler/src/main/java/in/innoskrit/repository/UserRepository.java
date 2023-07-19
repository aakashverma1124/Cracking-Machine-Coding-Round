package in.innoskrit.repository;

import in.innoskrit.exception.UserNotFoundException;
import in.innoskrit.model.Meeting;
import in.innoskrit.model.MeetingInviteResponse;
import in.innoskrit.model.Notification;
import in.innoskrit.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users;

    public void addUser(User user) {
        users.add(user);
    }

    public void addUsers(List<User> userList) {
        users.addAll(userList);
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser(String email) {
        for(User user : users) {
            if(user.getEmail().equals(email)) {
                return user;
            } else {
                throw new UserNotFoundException("No user found with email " + email);
            }
        }
        return null;
    }

    public MeetingInviteResponse respondInvitation(Notification notification) {
        return MeetingInviteResponse.ACCEPT;
    }

    public List<Meeting> getMeetings(User user) {
        if(user == null) return new ArrayList<>();
        return user.getCalender().getMeetings();
    }
}
