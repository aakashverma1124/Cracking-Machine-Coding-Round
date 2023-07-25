package in.innoskrit.service;

import in.innoskrit.exception.InvalidUserException;
import in.innoskrit.exception.UserNotFoundException;
import in.innoskrit.model.User;
import in.innoskrit.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public void addUser(User user) {
        try {
            userRepository.addUser(user);
        } catch(InvalidUserException e) {
            System.out.println(e.getMessage());
        }
    }

    public User getUser(String userId) {
        try {
            return userRepository.getUser(userId);
        } catch(UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
