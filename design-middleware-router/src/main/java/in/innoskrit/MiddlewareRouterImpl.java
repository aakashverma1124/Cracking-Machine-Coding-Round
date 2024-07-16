package in.innoskrit;

import java.util.Map;

public class MiddlewareRouterImpl implements MiddlewareRouter {
    private final PathNode pathNode;

    public MiddlewareRouterImpl() {
        this.pathNode = new PathNode();
    }

    private void insert(String[] segments, String result) {
        PathNode rootNode = pathNode;
        for(String segment : segments) {
            if(!rootNode.path.containsKey(segment)) {
                rootNode.path.put(segment, new PathNode());
            }
            rootNode = rootNode.path.get(segment);
        }
        rootNode.value = result;
    }

    @Override
    public void add(String path, String result) {
        String[] segments = path.trim().split("/");
        insert(segments, result);
    }

    private String search(String[] segments, int index, PathNode rootNode) {
        if(index >= segments.length) return null;
        String segment = segments[index];
        if(segment.equals("*")) {
            for(Map.Entry<String, PathNode> entry : rootNode.path.entrySet()) {
                String tempResult = search(segments, index + 1, entry.getValue());
                if(tempResult != null) {
                    return tempResult;
                }
            }
        } else {
            if(!rootNode.path.containsKey(segment)) {
                return null;
            }
            rootNode = rootNode.path.get(segment);
            if(index == segments.length - 1) {
                return rootNode.value;
            }
            return search(segments, index + 1, rootNode);
        }
        return null;
    }

    @Override
    public String route(String path) {
        String[] segments = path.trim().split("/");
        return search(segments, 0, pathNode);
    }
}
