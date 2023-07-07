package in.innoskrit.service;

import in.innoskrit.model.Contest;
import in.innoskrit.model.User;

import java.util.List;

public interface IUserService {
    User createUser(User user);
    List<User> createMultipleUsers(List<User> users);
    void assignContestToUser(User user, Contest contest);
    List<User> findAllContestUsers(Long contestId);
    void delete(User user);
}
