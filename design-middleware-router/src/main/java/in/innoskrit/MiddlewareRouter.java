package in.innoskrit;

public interface MiddlewareRouter {
    void add(String path, String result);
    String route(String path);
}
