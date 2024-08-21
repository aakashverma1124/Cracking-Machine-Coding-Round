package in.innoskrit.model;

import in.innoskrit.model.issue.Issue;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString
public class Customer {
    private String customerEmail;
    private String customerName;
    private Set<String> issues;

    public Customer(String email, String name) {
        this.customerEmail = email;
        this.customerName = name;
        this.issues = new HashSet<>();
    }
}