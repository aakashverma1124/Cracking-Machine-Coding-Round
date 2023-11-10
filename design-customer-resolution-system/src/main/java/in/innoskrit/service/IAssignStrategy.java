package in.innoskrit.service;

import in.innoskrit.model.Agent;

import java.util.List;

public interface IAssignStrategy {
    boolean assignAgentBySpecialization(List<Agent> agents, String issueId);
    boolean assignAgent(List<Agent> agents, String issueId);
}
