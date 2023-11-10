package in.innoskrit.service;

import in.innoskrit.model.Specialization;
import in.innoskrit.model.issue.Issue;
import in.innoskrit.model.issue.IssueState;
import in.innoskrit.repository.IssueManagementRepository;
import lombok.NonNull;

import java.util.Set;

public class IssueManagementService {

    private final IssueManagementRepository issueManagementRepository;

    public IssueManagementService(@NonNull IssueManagementRepository issueManagementRepository) {
        this.issueManagementRepository = issueManagementRepository;
    }

    public void createIssue(String issueId, String transactionId, Specialization issueType, String subject, String description, String createdBy) {
        Issue issue = issueManagementRepository.createIssue(issueId, transactionId, issueType, subject, description, createdBy);
        System.out.println("Issue "  + issue.getIssueId() + " created against transaction " + transactionId);
    }

    public Set<Issue> getIssueByEmail(String email) {
        return issueManagementRepository.getIssueByEmail(email);
    }

    public Issue getIssueByType() {
        return null;
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
        System.out.println(issueId + " marked as resolved.");
    }

    public void assignIssue(String issueId) {
        issueManagementRepository.assignIssue(issueId);
    }
}

