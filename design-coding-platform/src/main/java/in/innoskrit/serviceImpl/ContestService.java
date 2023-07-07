package in.innoskrit.serviceImpl;

import in.innoskrit.model.Contest;
import in.innoskrit.model.User;
import in.innoskrit.service.IContestService;

import java.util.HashMap;
import java.util.Map;

public class ContestService implements IContestService {
    Map<Long, Contest> contestData = new HashMap<>();

}
