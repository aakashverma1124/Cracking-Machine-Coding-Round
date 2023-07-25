package in.innoskrit;

import in.innoskrit.service.Encryptor;
import in.innoskrit.service.FileSystemService;

public class Main {
    public static void main(String[] args) {
        FileSystemService fileSystemService =  FileSystemService.getFileSystemService(1024, new Encryptor());

        fileSystemService.createFile("file1.txt", "Hello, this is file 1.".getBytes());
        fileSystemService.createFile("file2.txt", "This is file 2 content.".getBytes());

        byte[] content1 = fileSystemService.readFile("file1.txt");
        System.out.println("Reading file1.txt: " + new String(content1));

        byte[] content2 = fileSystemService.readFile("file2.txt");
        System.out.println("Reading file2.txt: " + new String(content2));

        fileSystemService.updateFile("file1.txt", "Updated content for file 1.".getBytes());
        byte[] updatedContent1 = fileSystemService.readFile("file1.txt");
        System.out.println("Reading updated file1.txt: " + new String(updatedContent1));

        fileSystemService.deleteFile("file2.txt");
        byte[] deletedContent2 = fileSystemService.readFile("file2.txt");
        System.out.println("Reading deleted file2.txt: " + (deletedContent2 == null ? "File not found." : new String(deletedContent2)));

        // Perform defragmentation (optional, but can be done periodically)
        fileSystemService.defragment();
    }
}