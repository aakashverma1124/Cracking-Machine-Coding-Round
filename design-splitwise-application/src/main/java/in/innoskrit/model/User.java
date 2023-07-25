package in.innoskrit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class User {
    private String userId;
    private String userName;
    private String email;
    private String phoneNumber;

    public User(String userName, String email, String phoneNumber) {
        this.userId = UUID.randomUUID().toString();
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
