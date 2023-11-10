package in.innoskrit.model;


import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Data
public class Agent {
    String agentEmail;
    String agentName;
    List<Specialization> specializations;
    Queue<String> waitingQueue;

    public Agent(String email, String agentName, List<Specialization> specializations) {
        this.agentEmail = email;
        this.agentName = agentName;
        this.specializations = specializations;
        this.waitingQueue = new LinkedList<>();
    }
}
