# Design Middleware Router

### Duration - 1 Hour

Design a middleware router. 

For example:
```
add("/bar", "result");
router.route("/bar"); -> Output: result
add("/bar/abc", "abc");
route("/bar/abc"); -> Output: abc
route("/bar/abc/dd"); -> Output: null
add("/bar/abc/dd", "dd");
add("/bar/abc1/cde/dd", "ee");
route("/bar/abc/dd"); -> Output: dd
route("/bar/abc1/cde/dd"); -> Output: ee
```

You need to complete the below method.
```java
void add(String path, String result);
String route(String path);
```

## Follow-up
The path can contain `*` which means match any segment in the path.

For example:

```
add("/bar/abc/dd", "dd");
add("/bar/abc1/cde/dd", "ee");
route("/bar/*/dd"); -> Output: dd
route("/bar/*/cde/dd"); -> Output: ee
```