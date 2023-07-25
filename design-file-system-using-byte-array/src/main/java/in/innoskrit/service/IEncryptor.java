package in.innoskrit.service;

public interface IEncryptor {
    byte[] encrypt(byte[] data);
    byte[] decrypt(byte[] encryptedData);
}
