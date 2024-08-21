package in.innoskrit;

import in.innoskrit.model.Specialization;
import in.innoskrit.model.issue.IssueState;
import in.innoskrit.repository.AgentRepository;
import in.innoskrit.repository.CustomerRepository;
import in.innoskrit.service.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        AgentService agentService = new AgentService(new AgentRepository());
        CustomerService customerService = new CustomerService(new CustomerRepository());
        IAssignStrategy assignStrategy = new DefaultStrategy();
        IssueManagementService issueManagementService = new IssueManagementService(agentService, assignStrategy, customerService);

        customerService.addCustomer("testUser1@test.com", "Test User 1");
        customerService.addCustomer("testUser2@test.com", "Test User 2");

        issueManagementService.createIssue("I1", "T1", Specialization.PAYMENT, "Payment Failed", "My payment failed but money is debited", "testUser1@test.com");
        issueManagementService.createIssue("I2", "T2", Specialization.MUTUAL_FUND, "Purchase Failed", "Unable to purchase Mutual Fund", "testUser2@test.com");
        issueManagementService.createIssue("I3", "T3", Specialization.PAYMENT, "Payment Failed", "My payment failed but money is debited", "testUser2@test.com");

        agentService.addAgent("agent1@test.com", "Agent 1", Arrays.asList(Specialization.PAYMENT, Specialization.GOLD));
        agentService.addAgent("agent2@test.com", "Agent 2", Arrays.asList(Specialization.PAYMENT));

        issueManagementService.assignIssue("I1");
        issueManagementService.assignIssue("I2");
        issueManagementService.assignIssue("I3");

        System.out.println(issueManagementService.getIssueByEmail("testUser1@test.com"));
        System.out.println(issueManagementService.getIssueByEmail("testUser2@test.com"));

        issueManagementService.updateIssue("I3", IssueState.IN_PROGRESS, "Waiting for payment confirmation");
        issueManagementService.resolveIssue("I3", "Payment Failed debited amount will get reversed");

        System.out.println(agentService.viewAgentsWorkHistory("agent1@test.com").toString());
        System.out.println(agentService.viewAgentsWorkHistory("agent2@test.com").toString());
    }
}
