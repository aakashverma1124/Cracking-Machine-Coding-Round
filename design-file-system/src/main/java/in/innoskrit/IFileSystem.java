package in.innoskrit;

public interface IFileSystem {
    boolean createPath(String path, int value);
    int get(String path);
}
