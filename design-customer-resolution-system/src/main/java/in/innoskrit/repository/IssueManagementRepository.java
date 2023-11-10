package in.innoskrit.repository;

import in.innoskrit.model.Agent;
import in.innoskrit.model.Customer;
import in.innoskrit.model.Specialization;
import in.innoskrit.model.issue.*;
import in.innoskrit.service.AgentService;
import in.innoskrit.service.CustomerService;
import in.innoskrit.service.IAssignStrategy;
import lombok.NonNull;

import java.util.*;

public class IssueManagementRepository {

    Map<String, Issue> issueMap;
    private final AgentService agentService;
    private final CustomerService customerService;

    private final IAssignStrategy defaultStrategy;

    public IssueManagementRepository(@NonNull AgentService agentService, @NonNull IAssignStrategy defaultStrategy, @NonNull CustomerService customerService) {
        issueMap = new HashMap<>();
        this.agentService = agentService;
        this.defaultStrategy = defaultStrategy;
        this.customerService = customerService;
    }

    public Issue createIssue(String issueId, String transactionId, Specialization issueType, String subject, String description, String createdBy) {
        Issue issue = null;
        if(issueType == Specialization.PAYMENT) {
            issue = new PaymentRelatedIssue(issueId, transactionId, subject, description, createdBy);
            issueMap.put(issue.getIssueId(), issue);
        } else if(issueType == Specialization.MUTUAL_FUND) {
            issue = new MutualFundRelatedIssue(issueId, transactionId, subject, description, createdBy);
            issueMap.put(issue.getIssueId(), issue);
        } else if(issueType == Specialization.INSURANCE) {
            issue = new InsuranceRelatedIssue(issueId, transactionId, subject, description, createdBy);
            issueMap.put(issue.getIssueId(), issue);
        } else if(issueType == Specialization.GOLD){
            issue = new GoldRelatedIssue(issueId, transactionId, subject, description, createdBy);
            issueMap.put(issue.getIssueId(), issue);
        }
        Customer customer = customerService.getCustomerByEmail(createdBy);
        customer.getIssues().add(issue);
        return issue;
    }

    public Set<Issue> getIssueByEmail(String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        return customer.getIssues();
    }

    public Issue getIssueByType() {
        return null;
    }

    public Issue getIssueById(String issueId) {
        return issueMap.get(issueId);
    }

    public void assignIssue(String issueId) {
        boolean assigned = false;
        Issue issue = issueMap.get(issueId);
        if(issue instanceof PaymentRelatedIssue) {
            List<Agent> agents = agentService.getAgentsBySpecialization(Specialization.PAYMENT);
            assigned = defaultStrategy.assignAgentBySpecialization(new ArrayList<>(agents), issueId);
        } else if(issue instanceof MutualFundRelatedIssue) {
            List<Agent> agents = agentService.getAgentsBySpecialization(Specialization.MUTUAL_FUND);
            assigned = defaultStrategy.assignAgentBySpecialization(new ArrayList<>(agents), issueId);
        } else if(issue instanceof GoldRelatedIssue) {
            List<Agent> agents = agentService.getAgentsBySpecialization(Specialization.GOLD);
            assigned = defaultStrategy.assignAgentBySpecialization(new ArrayList<>(agents), issueId);
        } else if(issue instanceof InsuranceRelatedIssue) {
            List<Agent> agents = agentService.getAgentsBySpecialization(Specialization.INSURANCE);
            assigned = defaultStrategy.assignAgentBySpecialization(new ArrayList<>(agents), issueId);
        }
        if(!assigned) {
            assigned = defaultStrategy.assignAgent(agentService.getAllAgents(), issueId);
//            Agent suitableAgent = null;
//            int queueSize = Integer.MAX_VALUE;
//            for(Agent agent : agentService.getAllAgents()) {
//                if(agent.getWaitingQueue().size() < queueSize) {
//                    queueSize = agent.getWaitingQueue().size();
//                    suitableAgent = agent;
//                }
//            }
//            assert suitableAgent != null;
//            suitableAgent.getWaitingQueue().add(issueId);
        }


    }


}
