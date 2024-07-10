package in.innoskrit.repository;

import in.innoskrit.exception.InvalidUserException;
import in.innoskrit.exception.UserNotFoundException;
import in.innoskrit.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    Map<String, User> userData;

    public UserRepository() {
        userData = new HashMap<>();
    }

    public void addUser(User user) {
        if(user == null) throw new InvalidUserException("Invalid User.");
        userData.put(user.getUserId(), user);
    }

    public User getUser(String userId) {
        if(!userData.containsKey(userId)) throw new UserNotFoundException("User doesn't exist.");
        return userData.get(userId);
    }

}
