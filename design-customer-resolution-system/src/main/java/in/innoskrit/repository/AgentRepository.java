package in.innoskrit.repository;


import in.innoskrit.model.Agent;
import in.innoskrit.model.Specialization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgentRepository {

    Map<String, Agent> agentMap;

    public AgentRepository() {
        agentMap = new HashMap<>();
    }

    public Agent addAgent(String email, String agentName, List<Specialization> specializations) {
        Agent agent = new Agent(email, agentName, specializations);
        agentMap.put(agent.getAgentEmail(), agent);
        return agent;
    }

    public List<Agent> getAgentsBySpecialization(Specialization specialization) {
        List<Agent> agentList = new ArrayList<>();
        for(Map.Entry<String, Agent> entry : agentMap.entrySet()) {
            Agent agent = entry.getValue();
            List<Specialization> agentSpecializations = agent.getSpecializations();
            for(Specialization sp : agentSpecializations) {
                if(sp == specialization) {
                    agentList.add(agent);
                }
            }
        }
        return agentList;
    }

    public List<Agent> getAllAgents() {
        List<Agent> agentList = new ArrayList<>();
        for(Map.Entry<String, Agent> entry : agentMap.entrySet()) {
            agentList.add(entry.getValue());
        }
        return agentList;
    }
}
