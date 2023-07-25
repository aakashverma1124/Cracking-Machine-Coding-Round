package in.innoskrit.service;

import in.innoskrit.model.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystemService implements IFileSystemService {
    private final byte[] data;
    private final Map<String, File> fileIndexMap;
    private final Encryptor encryptor;
    private final Lock lock;

    public static volatile FileSystemService fileSystemService;

    private FileSystemService(int capacity, Encryptor encryptor) {
        data = new byte[capacity];
        this.encryptor = encryptor;
        fileIndexMap = new HashMap<String, File>();
        lock = new ReentrantLock();
    }
    public static FileSystemService getFileSystemService(int capacity, Encryptor encryptor) {
        if(fileSystemService == null) {
            synchronized (FileSystemService.class) {
                if(fileSystemService == null) {
                    fileSystemService = new FileSystemService(capacity, encryptor);
                }
            }
        }
        return fileSystemService;
    }
    public void createFile(String filename, byte[] content) {
        lock.lock();
        try {
            if (fileIndexMap.containsKey(filename)) {
                throw new IllegalArgumentException("File already exists.");
            }

            byte[] encryptedContent = encryptor.encrypt(content);
            int startIndex = findAvailableSpace(encryptedContent.length);
            int endIndex = startIndex + encryptedContent.length - 1;

            if (startIndex != -1) {
                System.arraycopy(encryptedContent, 0, data, startIndex, encryptedContent.length);
                fileIndexMap.put(filename, new File(filename, startIndex, endIndex));
            } else {
                throw new IllegalArgumentException("Not enough space to create the file.");
            }
        } finally {
            lock.unlock();
        }
    }

    public byte[] readFile(String filename) {
        lock.lock();
        try {
            File file = fileIndexMap.get(filename);
            if (file != null) {
                int length = file.getEndIndex() - file.getStartIndex() + 1;
                byte[] encryptedContent = new byte[length];
                System.arraycopy(data, file.getStartIndex(), encryptedContent, 0, length);
                return encryptor.decrypt(encryptedContent);
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    public void updateFile(String filename, byte[] content) {
        lock.lock();
        try {
            deleteFile(filename);
            createFile(filename, content);
        } finally {
            lock.unlock();
        }
    }

    public void deleteFile(String filename) {
        lock.lock();
        try {
            File file = fileIndexMap.get(filename);
            if (file != null) {
                int length = file.getEndIndex() - file.getStartIndex() + 1;
                clearDataRange(file.getStartIndex(), length);
                fileIndexMap.remove(filename);
                defragment();
            } else {
                throw new IllegalArgumentException("File not found.");
            }
        } finally {
            lock.unlock();
        }
    }

    public void defragment() {
        lock.lock();
        try {
            List<File> files = new ArrayList<File>(fileIndexMap.values());
            fileIndexMap.clear();

            int currentIndex = 0;
            for (File file : files) {
                int length = file.getEndIndex() - file.getStartIndex() + 1;
                System.arraycopy(data, file.getStartIndex(), data, currentIndex, length);
                file.setStartIndex(currentIndex);
                file.setEndIndex(currentIndex + length - 1);
                currentIndex += length;
                fileIndexMap.put(file.getFilename(), file);
            }

            // Clear remaining bytes after the last file
            for (int i = currentIndex; i < data.length; i++) {
                data[i] = 0;
            }
        } finally {
            lock.unlock();
        }
    }

    private int findAvailableSpace(int requiredSize) {
        for (int i = 0; i < data.length; i++) {
            boolean found = true;
            for (int j = 0; j < requiredSize; j++) {
                if (i + j >= data.length || data[i + j] != 0) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return i;
            }
        }
        return -1;
    }

    private void clearDataRange(int startIndex, int length) {
        for (int i = startIndex; i < startIndex + length; i++) {
            data[i] = 0;
        }
    }
}

