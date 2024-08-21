package in.innoskrit.service;

import in.innoskrit.model.Agent;
import in.innoskrit.model.Customer;
import in.innoskrit.model.Specialization;
import in.innoskrit.model.issue.*;
import in.innoskrit.repository.IssueManagementRepository;
import lombok.NonNull;

import java.util.List;
import java.util.Set;

public class IssueManagementService {

    private final IssueManagementRepository issueManagementRepository;
    private final AgentService agentService;
    private final CustomerService customerService;
    private final IAssignStrategy defaultStrategy;


    public IssueManagementService(@NonNull AgentService agentService, @NonNull IAssignStrategy assignStrategy, @NonNull CustomerService customerService) {
        this.agentService = agentService;
        this.defaultStrategy = assignStrategy;
        this.customerService = customerService;
        this.issueManagementRepository = new IssueManagementRepository();
    }

    public void createIssue(String issueId, String transactionId, Specialization issueType, String subject, String description, String createdBy) {
        Issue issue = null;
        if(issueType == Specialization.PAYMENT) {
            issue = new PaymentRelatedIssue(issueId, transactionId, subject, description, createdBy);
        } else if(issueType == Specialization.MUTUAL_FUND) {
            issue = new MutualFundRelatedIssue(issueId, transactionId, subject, description, createdBy);
        } else if(issueType == Specialization.INSURANCE) {
            issue = new InsuranceRelatedIssue(issueId, transactionId, subject, description, createdBy);
        } else if(issueType == Specialization.GOLD){
            issue = new GoldRelatedIssue(issueId, transactionId, subject, description, createdBy);
        }
        Customer customer = customerService.getCustomerByEmail(createdBy);
        customer.getIssues().add(issue.getIssueId());
        issueManagementRepository.createIssue(issue);
        System.out.println("Issue "  + issue.getIssueId() + " created against transaction " + transactionId);
    }

    public Set<String> getIssueByEmail(String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        return customer.getIssues();
    }

    public void updateIssue(String issueId, IssueState issueState, String message) {
        Issue issue = issueManagementRepository.getIssueById(issueId);
        issue.setIssueState(issueState);
        issue.setStatusMessage(message);
        System.out.println(issueId + " status updated to In Progress");
    }

    public void resolveIssue(String issueId, String message) {
        Issue issue = issueManagementRepository.getIssueById(issueId);
        issue.setIssueState(IssueState.RESOLVED);
        issue.setStatusMessage(message);
        Agent agent = agentService.getAgentByEmail(issue.getAssignedTo());
        agent.getResolvedIssues().add(issue.getIssueId());
        agent.getWaitingQueue().remove(issueId);
        System.out.println(issueId + " marked as resolved.");
    }

    public void assignIssue(String issueId) {
        Agent assignedAgent;
        List<Agent> agents = null;
        Issue issue = issueManagementRepository.getIssueById(issueId);
        if(issue instanceof PaymentRelatedIssue) {
            agents = agentService.getAgentsBySpecialization(Specialization.PAYMENT);
        } else if(issue instanceof MutualFundRelatedIssue) {
            agents = agentService.getAgentsBySpecialization(Specialization.MUTUAL_FUND);
        } else if(issue instanceof GoldRelatedIssue) {
            agents = agentService.getAgentsBySpecialization(Specialization.GOLD);
        } else if(issue instanceof InsuranceRelatedIssue) {
            agents = agentService.getAgentsBySpecialization(Specialization.INSURANCE);
        }
        assignedAgent = defaultStrategy.assignAgentBySpecialization(agents, issueId);
        if(assignedAgent == null) {
            assignedAgent = defaultStrategy.assignAgent(agentService.getAllAgents(), issueId);
        }
        issue.setAssignedTo(assignedAgent.getAgentEmail());
    }
}

