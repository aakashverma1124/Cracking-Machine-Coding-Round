### Splitwise - Expense Sharing Application

Splitwise helps groups of people such as flatmates, friends on a trip or colleagues sharing a meal keep track of shared expenses. One person pays the bill, the amount is split among the people involved, and the application keeps a running balance between every pair of users so that everyone knows how much they owe and how much they are owed.

You have to design an expense sharing system where users can add an expense, split it among any of the available users in different ways and view the balances. An expense can be split in three ways:
- **EQUAL** - the amount is divided equally among all the participants.
- **EXACT** - the exact amount owed by each participant is provided.
- **PERCENT** - the percentage share of each participant is provided.

### Features:
- Each user has a userId, name, email and mobile number.
- A user can add an expense of any amount, select any split type and split it with any of the available users. The payer may or may not be one of the participants.
- Amounts and percentages can have up to two decimal places.
- For EXACT, the sum of the shares must be equal to the total amount. For PERCENT, the sum of the percentages must be 100. Invalid expenses are rejected and do not change any balance.
- Every share is rounded off to two decimal places and the rounding remainder goes to the first participant. Say User1 paid 100 and it is split equally among 3 people, then the first person owes 33.34 and the others owe 33.33.
- Balances between two users are netted. If User1 owes User2 100 and User2 owes User1 30, then User1 owes User2 70.
- Show balances for everyone, or for a single user. Only non-zero balances are shown.

### Implementation Requirements:
Your solution should implement the following functions. Feel free to use the representation for objects you deem fit for the problem and the provided use cases. The functions are ordered in the decreasing order of importance (highest to lowest). We understand that you may not be able to complete the implementation for all the functions listed here. So try to implement them in the order in which they are declared down below.

```
addUser(userId, name, email, mobileNumber)
addExpense(paidBy, amount, expenseType, List<userId> participants, List<value> values) /* values are empty for EQUAL,
the exact amounts for EXACT and the percentages for PERCENT, in the same order as participants */
showBalances()        // all non-zero balances
showBalances(userId)  // non-zero balances the user is part of
```

### Input Format:
Users can be created in the main method, there is no need to take them as input. The driver program receives the following commands:

```
EXPENSE <paid-by-user-id> <amount> <no-of-users> <space-separated-user-ids> <EQUAL|EXACT|PERCENT> <space-separated-values-for-EXACT-or-PERCENT>
SHOW
SHOW <user-id>
```

### Output Format:
- `EXPENSE` does not print anything when the expense is added successfully.
- Every balance is printed as `<user-id-of-x> owes <user-id-of-y>: <amount>`, with the amount rounded off to two decimal places.
- For `SHOW <user-id>`, if the user owes money they are x, otherwise they are y.
- If there are no balances to show, print `No balances`.
- Invalid commands and invalid expenses print an error and do not change any state.

### Example:

```aidl
// Users u1, u2, u3 and u4 are created in the main method

SHOW
>>> No balances

EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
SHOW u4
>>> u4 owes u1: 250.00

EXPENSE u2 600 2 u1 u3 EXACT 250 350
SHOW u1                      // u1 and u2 owe each other 250, so they are settled
>>> u3 owes u1: 250.00
>>> u4 owes u1: 250.00

EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
EXPENSE u3 100 3 u1 u2 u3 EQUAL   // u1 owes 33.34, u2 owes 33.33

EXPENSE u1 500 2 u2 u3 EXACT 200 250
>>> Sum of exact amounts must be equal to the total amount 500.00.

EXPENSE u1 300 3 u1 u2 u3 PERCENT 50 30 30
>>> Sum of percentages must be equal to 100.

SHOW
>>> u1 owes u4: 230.00
>>> u2 owes u4: 240.00
>>> u3 owes u1: 216.66
>>> u3 owes u2: 316.67
>>> u3 owes u4: 240.00

SHOW u2
>>> u2 owes u4: 240.00
>>> u3 owes u2: 316.67

SHOW u9
>>> User u9 doesn't exist.
```

### Things to keep in mind:
- Time Limit is 120 minutes.
- You are only allowed to use in-memory data structures.
- You are NOT allowed to use any databases.
- You are NOT required to have a full-fledged web service or APIs exposed.
- You are NOT allowed to use any other third-party libraries apart from the standard ones that are part of the language runtime.
- Please ensure you submit your solution on time even if it is not complete. A partial solution submitted on time is better than a complete solution submitted after time.
- Spend time on implementing the core features first and then proceed to implement the optional features.
- Writing test cases to prove the correctness of your code will be appreciated.
- You are required to demonstrate the working of your solution during code review.
- A driver program with a main class that simulates the above operations is enough. No need to create any GUI or CLI to make the application interactive.

### Evaluation Criteria

- Separation of concerns
- Abstractions
- Application of Object Oriented Design Principles
- Testability
- Extensibility
- Code readability
- Language proficiency
- [execution time limit] 3 seconds (java)
- [memory limit] 1 GB
