package in.innoskrit.model.issue;

public class PaymentRelatedIssue extends Issue {
    public PaymentRelatedIssue(String issueId, String transactionId, String subject, String description, String createdBy) {
        super(issueId, transactionId, subject, description, createdBy);
    }
}
