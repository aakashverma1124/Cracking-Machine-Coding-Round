package in.innoskrit.model.issue;

import lombok.Data;

@Data
public abstract class Issue {
    private final String issueId;
    private final String transactionId;
    private final String subject;
    private final String description;
    private final String createdBy;
    private String statusMessage;
    private IssueState issueState;

    public Issue(String issueId, String transactionId, String subject, String description, String createdBy) {
        this.issueId = issueId;
        this.transactionId = transactionId;
        this.subject = subject;
        this.description = description;
        this.createdBy = createdBy;
        this.statusMessage = "Issue has been raised.";
        this.issueState = IssueState.OPEN;
    }
}
