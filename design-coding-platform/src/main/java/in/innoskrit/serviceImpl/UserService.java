package in.innoskrit.serviceImpl;

import in.innoskrit.model.Contest;
import in.innoskrit.model.User;
import in.innoskrit.service.IUserService;

import java.util.*;

public class UserService implements IUserService {

    private ContestService contestService;
    Map<Long, User> userData = new HashMap<>();

    public User createUser(User user) {
        user.setScore(1500);
        return userData.putIfAbsent(user.getId(), user);
    }

    public List<User> createMultipleUsers(final List<User> users) {
        users.stream().filter(Objects::nonNull)
                .forEach(user -> user.setScore(1500));
        users.stream().forEach(user -> userData.putIfAbsent(user.getId(), user));
        return users;
    }

    public void assignContestToUser(User user, Contest contest) {
        setUserContestQuestion(user, contest);
        userData.putIfAbsent(user.getId(), user);
    }

    public List<User> findAllContestUsers(Long contestId) {
        List<User> users = new ArrayList<>();

        for (Map.Entry<Long, User> entry : userData.entrySet()) {
            User user = entry.getValue();
            List<Contest> contests = user.getContests();

            for (Contest contest : contests) {
                if (contest.getId().equals(contestId)) {
                    users.add(user);
                    break;
                }
            }
        }

        return users;
    }

    private void setUserContestQuestion(User user, Contest contest) {
        validateUserAndContest(user, contest);
        user.getContests().add(contest);
    }

    public void delete(User user) {
        validateUser(user);
        userData.remove(user.getId());
    }

    public Map<Long, User> getAll() {
        return userData;
    }

    private void validateUserAndContest(User user, Contest contest) {
        validateUser(user);
        validateContest(contest);
    }

    private void validateUser(User user) {
        Optional.ofNullable(userData.get(user.getId()))
                .orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    private void validateContest(Contest contest) {
        Optional.ofNullable(contestService.contestData.get(contest.getId()))
                .orElseThrow(() -> new RuntimeException("Contest Not Found"));
    }
}
