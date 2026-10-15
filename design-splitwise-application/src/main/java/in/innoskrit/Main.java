package in.innoskrit;

import in.innoskrit.exception.InvalidExpenseException;
import in.innoskrit.exception.InvalidUserException;
import in.innoskrit.exception.UserNotFoundException;
import in.innoskrit.model.ExpenseType;
import in.innoskrit.model.User;
import in.innoskrit.model.split.EqualSplit;
import in.innoskrit.model.split.ExactSplit;
import in.innoskrit.model.split.PercentageSplit;
import in.innoskrit.model.split.Split;
import in.innoskrit.repository.ExpenseRepository;
import in.innoskrit.repository.UserRepository;
import in.innoskrit.service.ExpenseService;
import in.innoskrit.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        UserService userService = new UserService(new UserRepository());
        ExpenseService expenseService = new ExpenseService(new ExpenseRepository(), userService);
        userService.addUser(new User("u1", "u1@gmail.com", "9999999999"));
        userService.addUser(new User("u2", "u2@gmail.com", "9999999999"));
        userService.addUser(new User("u3", "u3@gmail.com", "9999999999"));
        userService.addUser(new User("u4", "u4@gmail.com", "9999999999"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            try {
                processCommand(line.split("\\s+"), userService, expenseService);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number in command: " + line);
            } catch (IllegalArgumentException | InvalidUserException | UserNotFoundException | InvalidExpenseException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void processCommand(String[] command, UserService userService, ExpenseService expenseService) {
        switch (command[0]) {
            case "EXPENSE":
                createExpense(command, userService, expenseService);
                break;
            case "SHOW":
                if (command.length == 1) expenseService.showBalance();
                else if (command.length == 2) expenseService.showBalanceByUser(userService.getUserByName(command[1]).getUserId());
                else throw new IllegalArgumentException("Invalid command. Usage: SHOW or SHOW <user-id>");
                break;
            default:
                throw new IllegalArgumentException("Invalid command: " + command[0]);
        }
    }

    // EXPENSE <paid-by> <amount> <no-of-users> <users...> <EQUAL|EXACT|PERCENT> <values in case of EXACT or PERCENT...>
    private static void createExpense(String[] command, UserService userService, ExpenseService expenseService) {
        if (command.length < 6) throw new IllegalArgumentException("Invalid EXPENSE command.");
        String expenseCreatedBy = userService.getUserByName(command[1]).getUserId();
        double amount = Double.parseDouble(command[2]);
        int numberOfUsersInvolved = Integer.parseInt(command[3]);
        int expenseTypeIndex = 4 + numberOfUsersInvolved;
        if (numberOfUsersInvolved <= 0 || command.length <= expenseTypeIndex)
            throw new IllegalArgumentException("Invalid EXPENSE command.");

        ExpenseType expenseType = parseExpenseType(command[expenseTypeIndex]);
        int expectedLength = expenseType == ExpenseType.EQUAL ? expenseTypeIndex + 1 : expenseTypeIndex + 1 + numberOfUsersInvolved;
        if (command.length != expectedLength)
            throw new IllegalArgumentException("Invalid EXPENSE command. " + expenseType + " expects "
                    + (expectedLength - expenseTypeIndex - 1) + " values.");

        List<Split> splits = new ArrayList<>();
        for (int i = 0; i < numberOfUsersInvolved; i++) {
            String userInvolved = userService.getUserByName(command[4 + i]).getUserId();
            if (expenseType == ExpenseType.EQUAL) {
                splits.add(new EqualSplit(userInvolved));
            } else if (expenseType == ExpenseType.EXACT) {
                splits.add(new ExactSplit(userInvolved, Double.parseDouble(command[expenseTypeIndex + 1 + i])));
            } else {
                splits.add(new PercentageSplit(userInvolved, Double.parseDouble(command[expenseTypeIndex + 1 + i])));
            }
        }
        expenseService.createExpense(expenseType, amount, expenseCreatedBy, splits, null);
    }

    private static ExpenseType parseExpenseType(String expenseType) {
        switch (expenseType) {
            case "EQUAL":
                return ExpenseType.EQUAL;
            case "EXACT":
                return ExpenseType.EXACT;
            case "PERCENT":
                return ExpenseType.PERCENTAGE;
            default:
                throw new IllegalArgumentException("Invalid expense type: " + expenseType);
        }
    }
}
