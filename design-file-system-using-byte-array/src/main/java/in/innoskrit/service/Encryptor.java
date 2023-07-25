package in.innoskrit.service;

public class Encryptor implements IEncryptor {
    public byte[] encrypt(byte[] data) {
        byte[] encryptedData = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            encryptedData[i] = (byte) (data[i] ^ 0xFF);
        }
        return encryptedData;
    }

    public byte[] decrypt(byte[] encryptedData) {
        byte[] decryptedData = new byte[encryptedData.length];
        for (int i = 0; i < encryptedData.length; i++) {
            decryptedData[i] = (byte) (encryptedData[i] ^ 0xFF);
        }
        return decryptedData;
    }
}
