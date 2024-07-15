package in.innoskrit;

import java.util.HashMap;
import java.util.Map;

public class PathNode {
    int value;
    Map<String, PathNode> path;

    public PathNode() {
        this.path = new HashMap<>();
    }

    @Override
    public String toString() {
        return "PathNode{" +
                "value=" + value +
                ", path=" + path +
                '}';
    }
}
