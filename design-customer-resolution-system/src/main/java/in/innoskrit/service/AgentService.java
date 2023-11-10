package in.innoskrit.service;

import in.innoskrit.model.Agent;
import in.innoskrit.model.Specialization;
import in.innoskrit.repository.AgentRepository;

import java.util.List;

public class AgentService {

    private final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public Agent addAgent(String email, String agentName, List<Specialization> specializations) {
        Agent agent = agentRepository.addAgent(email, agentName, specializations);
        System.out.println(agentName + " created.");
        return agent;
    }

    public List<Agent> getAgentsBySpecialization(Specialization specialization) {
        return agentRepository.getAgentsBySpecialization(specialization);
    }

    public List<Agent> getAllAgents() {
        return agentRepository.getAllAgents();
    }
}
