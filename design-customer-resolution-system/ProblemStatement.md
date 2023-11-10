### Customer Issue Resolution System

PhonePe serves millions of transactions every day, some of these transactions fail (FAILED state) or remain in PENDING state due to multiple issues like Bank failure, NPCI issue or some internal PhonePe errors.

You have to design a system for issue resolution where customers can raise the issue against any such unsuccessful transactions. Customer Issues can be classified into multiple types like Payment Related, Mutual Fund Related, Gold related, Insurance Related. Customer Service agents can have their own expertise based on the issue type. Customer Service Agent and work on the issues assigned to them and update the status of the issues.

If all the customer service agents are already working on a ticket, then the tickets are added to the waiting list of those agents which can be picked after the current ticket is marked resolved.

### Features:
- Customer should be able to raise a issue for any transactions
- Agent can search customer issue with issue id or customer details (email)
- Agents have the list of issues assigned against them, which they will work on to resolve and mark the ticket Resolve

### Implementation Requirements:
Your solution should implement the following functions. Feel free to use the representation for objects you deem fit for the problem and the provided use cases. The functions are ordered in the decreasing order of importance (highest to lowest). We understand that you may not be able to complete the implementation for all the functions listed here. So try to implement them in the order in which they are declared down below.

```
createIssue(transactionId, issueType, subject, description, email)
addAgent(agentEmail, agentName ,List<issueType>)
assignIssue(issueId) /* Issue can be assigned to the agents based on different strategies.
For now, assign to any one of the free agents. */
getIssues(filter)  // issues against the provided filter
updateIssue(issueId, status, resolution)
resolveIssue(issueId, resolution)
viewAgentsWorkHistory() // a list of issue which agents worked on
```

### Example:

```aidl
createIssue("T1", "Payment Related", "Payment Failed", "My payment failed but money is debited", “testUser1@test.com”);
>>> Issue I1 created against transaction "T1"

createIssue("T2", "Mutual Fund Related", "Purchase Failed", "Unable to purchase Mutual Fund", “testUser2@test.com”);
>>> Issue I2 created against transaction "T2"

createIssue("T3", "Payment Related", "Payment Failed", "My payment failed but money is debited", , “testUser2@test.com”);
>>> Issue I3 created against transaction "T3"

addAgent(“agent1@test.com”, “Agent 1”, Arrays.asList("Payment Related", "Gold Related"));
>>> Agent A1 created


addAgent(“agent2@test.com”, “Agent 2”, Arrays.asList("Payment Related"));
>>> Agent A2 created


assignIssue("I1")
>>> Issue I1 assigned to agent A1

assignIssue("I2")
>>> Issue I2 assigned to agent A2

assignIssue("I3")
>>> Issue I3 added to waitlist of Agent A1



getIssue({"email": "testUser2@test.com"});
>>> I2 {"T2", "Mutual Fund Related", "Purchase Failed", "Unable to purchase Mutual Fund", "testUser2@test.com", "Open"},
 I3 {"T3", "Payment Related", "Payment Failed", "My payment failed but money is debited", , "testUser2@test.com", "Open"}

getIssue({"type": "Payment Related"});
>>> I1{"T1", "Payment Related", "Payment Failed", "My payment failed but money is debited", "testUser1@test.com", "Open"},
 I3 {"T3", "Payment Related", "Payment Failed", "My payment failed but money is debited", "testUser1@test.com", "Open"}

updateIssue("I3", "In Progress", "Waiting for payment confirmation");
>>> I3 status updated to In Progress

resolveIssue("I3", "PaymentFailed debited amount will get reversed");
>>> I3 issue marked resolved


viewAgentsWorkHistory()
>>> A1 -> {I1, I3}, 
    A2 -> {I2}

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