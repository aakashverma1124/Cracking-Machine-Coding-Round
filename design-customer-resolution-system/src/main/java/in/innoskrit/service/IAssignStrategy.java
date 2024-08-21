package in.innoskrit.service;

import in.innoskrit.model.Agent;

import java.util.List;

public interface IAssignStrategy {
    Agent assignAgentBySpecialization(List<Agent> agents, String issueId);
    Agent assignAgent(List<Agent> agents, String issueId);
}
