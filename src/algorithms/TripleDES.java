package algorithms;

import utils.AlgorithmHelper;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

//Decorator concrete class - 3DES Algorithm
public class TripleDES implements Algorithm {
    SecretKey sKey;
    private Cipher encCipher;
    private Cipher decCipher;

    public TripleDES(String key) throws Exception {
        byte[] keyBytes = AlgorithmHelper.stringToByteArray(key + key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        this.sKey = keyFactory.generateSecret(new DESedeKeySpec(keyBytes));
        initCiphers();
    }

    public void initCiphers() throws Exception {
        this.encCipher = Cipher.getInstance("DESede/ECB/NoPadding");
        this.decCipher = Cipher.getInstance("DESede/ECB/NoPadding");

        this.encCipher.init(Cipher.ENCRYPT_MODE, sKey);
        this.decCipher.init(Cipher.DECRYPT_MODE, sKey);
    }

    @Override
    public byte[] encrypt(byte[] input) {
        byte[] encrypted = new byte[0];
        try {
            encrypted = this.encCipher.doFinal(input);
        } catch (Exception e) {
            System.out.println(e);
        }
        return encrypted;
    }

    @Override
    public byte[] decrypt(byte[] input) {
        byte[] decrypted = new byte[0];
        try {
            decrypted = this.decCipher.doFinal(input);
        } catch (Exception e) {
            System.out.println(e);
        }
        return decrypted;
    }
}
