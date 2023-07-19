package in.innoskrit.preloader;

import in.innoskrit.model.Calender;
import in.innoskrit.model.User;

import java.util.ArrayList;
import java.util.List;

public class DummyUserCreator {

    public static List<User> createUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User("user1@gmail.com", "User 1", new Calender(new ArrayList<>()));
        User user2 = new User("user2@gmail.com", "User 2", new Calender(new ArrayList<>()));
        User user3 = new User("user3@gmail.com", "User 3", new Calender(new ArrayList<>()));
        User user4 = new User("user4@gmail.com", "User 4", new Calender(new ArrayList<>()));
        User user5 = new User("user5@gmail.com", "User 5", new Calender(new ArrayList<>()));
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        return users;
    }



}
