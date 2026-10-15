package in.innoskrit.service;

import in.innoskrit.exception.InvalidUserException;
import in.innoskrit.exception.UserNotFoundException;
import in.innoskrit.model.User;
import in.innoskrit.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        if (user == null) throw new InvalidUserException("Invalid User.");
        if (userRepository.getUserByName(user.getUserName()) != null)
            throw new InvalidUserException("User " + user.getUserName() + " already exists.");
        userRepository.addUser(user);
    }

    public User getUser(String userId) {
        User user = userRepository.getUser(userId);
        if (user == null) throw new UserNotFoundException("User doesn't exist.");
        return user;
    }

    public User getUserByName(String userName) {
        User user = userRepository.getUserByName(userName);
        if (user == null) throw new UserNotFoundException("User " + userName + " doesn't exist.");
        return user;
    }
}
