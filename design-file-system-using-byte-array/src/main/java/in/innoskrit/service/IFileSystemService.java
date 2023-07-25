package in.innoskrit.service;

public interface IFileSystemService {
    void createFile(String filename, byte[] content);
    byte[] readFile(String filename);
    void updateFile(String filename, byte[] content);
    void deleteFile(String filename);
    void defragment();
}
