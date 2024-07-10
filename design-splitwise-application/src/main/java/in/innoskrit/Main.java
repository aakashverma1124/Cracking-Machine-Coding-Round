package in.innoskrit;

import in.innoskrit.model.User;
import in.innoskrit.model.split.Split;
import in.innoskrit.repository.UserRepository;
import in.innoskrit.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        UserService userService = new UserService(new UserRepository());
        userService.addUser(new User("u1", "u1@gmail.com", "9999999999"));
        userService.addUser(new User("u2", "u2@gmail.com", "9999999999"));
        userService.addUser(new User("u3", "u3@gmail.com", "9999999999"));
        userService.addUser(new User("u4", "u4@gmail.com", "9999999999"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] expenseDetails1 = reader.readLine().trim().split("\\s+");
        String expenseCreatedBy = expenseDetails1[0];
        double amount = Double.parseDouble(expenseDetails1[1]);
        int numberOfUsersInvolved = Integer.parseInt(expenseDetails1[2]);
        List<Split> splits = new ArrayList<>();
        for(int i = 3; i < (i + numberOfUsersInvolved); i++) {
            String userInvolved = expenseDetails1[i];
        }

    }
}