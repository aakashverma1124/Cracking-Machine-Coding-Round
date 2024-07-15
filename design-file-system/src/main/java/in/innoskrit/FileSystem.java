package in.innoskrit;

public class FileSystem implements IFileSystem {

    private final PathNode pathNode;

    public FileSystem() {
        this.pathNode = new PathNode();
    }

    private boolean insert(String[] segments, int value) {
        PathNode rootNode = pathNode;
        for(int i = 1; i < segments.length; i++) {
            String segment = segments[i];
            if(!rootNode.path.containsKey(segment)) {
                if(i != segments.length - 1) return false;
                rootNode.path.put(segment, new PathNode());
            }
            rootNode = rootNode.path.get(segment);
        }
        rootNode.value = value;
        return true;
    }

    @Override
    public boolean createPath(String path, int value) {
        String[] segments = path.trim().split("/");
        return insert(segments, value);
    }

    private int search(String[] segments, int index, PathNode rootNode) {
        if(index >= segments.length) return -1;
        String segment = segments[index];
        if(!rootNode.path.containsKey(segment)) {
            return -1;
        }
        rootNode = rootNode.path.get(segment);
        if(index == segments.length - 1) {
            return rootNode.value;
        }
        return search(segments, index + 1, rootNode);

    }
    @Override
    public int get(String path) {
        String[] segments = path.trim().split("/");
        return search(segments, 1, pathNode);
    }
}
