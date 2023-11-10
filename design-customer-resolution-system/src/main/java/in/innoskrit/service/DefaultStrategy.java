package in.innoskrit.service;

import in.innoskrit.model.Agent;

import java.util.List;

public class DefaultStrategy implements IAssignStrategy {

    public boolean assignAgentBySpecialization(List<Agent> agents, String issueId) {
        boolean assigned = false;
        Agent suitableAgent = null;
        int queueSize = Integer.MAX_VALUE;
        for(Agent agent : agents) {
            if(agent.getWaitingQueue().size() < queueSize) {
                queueSize = agent.getWaitingQueue().size();
                suitableAgent = agent;
            }
        }
        if(suitableAgent != null) {
            suitableAgent.getWaitingQueue().add(issueId);
            System.out.println("Issue " + issueId + " assigned to " + suitableAgent.getAgentName());
            assigned = true;
        }
        return assigned;

    }

    @Override
    public boolean assignAgent(List<Agent> agents, String issueId) {
        Agent suitableAgent = null;
        int queueSize = Integer.MAX_VALUE;
        for(Agent agent : agents) {
            if(agent.getWaitingQueue().size() < queueSize) {
                queueSize = agent.getWaitingQueue().size();
                suitableAgent = agent;
            }
        }
        assert suitableAgent != null;
        suitableAgent.getWaitingQueue().add(issueId);
        System.out.println("Issue " + issueId + " added to wait-list of " + suitableAgent.getAgentName());
        return true;
    }


}
