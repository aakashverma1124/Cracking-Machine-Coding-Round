package in.innoskrit.repository;

import in.innoskrit.model.issue.Issue;

import java.util.HashMap;
import java.util.Map;

public class IssueManagementRepository {

    Map<String, Issue> issueMap;

    public IssueManagementRepository() {
        issueMap = new HashMap<>();
    }

    public void createIssue(Issue issue) {
        issueMap.put(issue.getIssueId(), issue);
    }

    public Issue getIssueById(String issueId) {
        return issueMap.get(issueId);
    }

}
