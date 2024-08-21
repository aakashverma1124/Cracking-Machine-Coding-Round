package in.innoskrit.service;

import in.innoskrit.model.Agent;

import java.util.List;

public class DefaultStrategy implements IAssignStrategy {

    public Agent assignAgentBySpecialization(List<Agent> agents, String issueId) {

        if(agents == null || agents.size() == 0) {
            return null;
        }

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
            if(suitableAgent.getWaitingQueue().size() == 1) {
                System.out.println("Issue " + issueId + " assigned to " + suitableAgent.getAgentName());
            } else {
                System.out.println("Issue " + issueId + " added to wait-list of " + suitableAgent.getAgentName());
            }
        }
        return suitableAgent;
    }

    @Override
    public Agent assignAgent(List<Agent> agents, String issueId) {
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
        if(suitableAgent.getWaitingQueue().size() == 1) {
            System.out.println("Issue " + issueId + " assigned to " + suitableAgent.getAgentName());
        } else {
            System.out.println("Issue " + issueId + " added to wait-list of " + suitableAgent.getAgentName());
        }
        return suitableAgent;
    }


}
