package in.innoskrit;

public class Main {
    public static void main(String[] args) {
        MiddlewareRouter router = new MiddlewareRouterImpl();
        router.add("/bar", "result");
        System.out.println(router.route("/bar")); // -> "result"

        router.add("/bar/abc", "abc");
        System.out.println(router.route("/bar/abc"));// -> "abc"
        System.out.println(router.route("/bar/abc/dd")); //-> null

        router.add("/bar/abc/dd", "dd");
        router.add("/bar/abc1/cde/dd", "ee");
        System.out.println(router.route("/bar/abc/dd"));// -> "dd"
        System.out.println(router.route("/bar/abc1/cde/dd")); // -> "ee"

        System.out.println(router.route("/bar/*/dd"));// -> "dd"
        System.out.println(router.route("/bar/*/cde/dd")); // -> "ee"
    }
}