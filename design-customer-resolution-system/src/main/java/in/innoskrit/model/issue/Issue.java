package in.innoskrit.model.issue;

import in.innoskrit.constants.Constant;
import lombok.Data;

@Data
public abstract class Issue {
    private final String issueId;
    private final String transactionId;
    private final String subject;
    private final String description;
    private final String createdBy;

    private String assignedTo;
    private String statusMessage;

    private IssueState issueState;

    public Issue(String issueId, String transactionId, String subject, String description, String createdBy) {
        this.issueId = issueId;
        this.transactionId = transactionId;
        this.subject = subject;
        this.description = description;
        this.createdBy = createdBy;
        this.statusMessage = Constant.INITIAL_STATUS;
        this.issueState = IssueState.OPEN;
    }

}
