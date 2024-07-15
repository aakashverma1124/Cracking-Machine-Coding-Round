package in.innoskrit;

import java.util.HashMap;
import java.util.Map;

public class PathNode {
    String value;
    Map<String, PathNode> path;

    public PathNode() {
        this.path = new HashMap<>();
    }
}
