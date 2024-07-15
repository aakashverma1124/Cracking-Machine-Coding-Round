package in.innoskrit;

public class Main {
    public static void main(String[] args) {
        IFileSystem fileSystem = new FileSystem();
        System.out.println(fileSystem.createPath("/foo", 1)); // return true
        System.out.println(fileSystem.createPath("/foo/bar", 2)); // return true
        System.out.println(fileSystem.get("/foo/bar")); // return 2
        System.out.println(fileSystem.createPath("/c/d", 1)); // return false because the parent path "/c" doesn't exist.
        System.out.println(fileSystem.get("/c")); // return -1 because this path doesn't exist.
    }
}