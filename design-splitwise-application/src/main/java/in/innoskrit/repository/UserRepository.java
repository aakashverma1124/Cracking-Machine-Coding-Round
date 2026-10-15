package in.innoskrit.repository;

import in.innoskrit.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    Map<String, User> userData;

    public UserRepository() {
        userData = new HashMap<>();
    }

    public void addUser(User user) {
        userData.put(user.getUserId(), user);
    }

    public User getUser(String userId) {
        return userData.get(userId);
    }

    public User getUserByName(String userName) {
        for (User user : userData.values()) {
            if (user.getUserName().equals(userName)) return user;
        }
        return null;
    }

}
