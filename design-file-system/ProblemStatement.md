# Design File System
### Duration - 1 Hour

Design a file system that allows you to create new paths and associate them with different values.

The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, `"/foo"` and `"/foo/bar"` are valid paths while an empty string `""` and `"/"` are not.

Implement the `FileSystem` class:

`bool createPath(string path, int value)`: Creates a new path and associates a value to it if possible and returns `true`. Returns `false` if the path already exists or its parent path doesn't exist.

`int get(string path)`: Returns the `value` associated with path or returns `-1` if the path doesn't exist.

```java
fileSystem.createPath("/a", 1); // return true
fileSystem.get("/a"); // return 1
fileSystem.createPath("/foo", 1); // return true
fileSystem.createPath("/foo/bar", 2); // return true
fileSystem.get("/foo/bar"); // return 2
fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
fileSystem.get("/c"); // return -1 because this path doesn't exist.
```