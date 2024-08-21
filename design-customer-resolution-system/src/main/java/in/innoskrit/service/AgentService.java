package in.innoskrit.service;

import in.innoskrit.model.Agent;
import in.innoskrit.model.Specialization;
import in.innoskrit.repository.AgentRepository;

import java.util.ArrayList;
import java.util.List;

public class AgentService {

    private final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public void addAgent(String email, String agentName, List<Specialization> specializations) {
        agentRepository.addAgent(email, agentName, specializations);
        System.out.println(agentName + " created.");
    }

    public List<Agent> getAgentsBySpecialization(Specialization specialization) {
        return agentRepository.getAgentsBySpecialization(specialization);
    }

    public List<Agent> getAllAgents() {
        return agentRepository.getAllAgents();
    }

    public Agent getAgentByEmail(String email) {
        return agentRepository.getAgentByEmail(email);
    }

    public List<String> viewAgentsWorkHistory(String email) {
        Agent agent = agentRepository.getAgentByEmail(email);
        List<String> issues = new ArrayList<>();
        issues.addAll(agent.getResolvedIssues());
        issues.addAll(agent.getWaitingQueue());
        return issues;
    }
}
