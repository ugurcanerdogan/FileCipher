package algorithms;

import utils.AlgorithmHelper;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

//Decorator concrete class - DES Algorithm
public class DES implements Algorithm {
    SecretKey sKey;
    private Cipher encCipher;
    private Cipher decCipher;

    public DES(String key) throws Exception {
        byte[] keyBytes = AlgorithmHelper.stringToByteArray(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        this.sKey = keyFactory.generateSecret(new DESKeySpec(keyBytes));
        initCiphers();
    }

    public void initCiphers() throws Exception {
        this.encCipher = Cipher.getInstance("DES/ECB/NoPadding");
        this.decCipher = Cipher.getInstance("DES/ECB/NoPadding");

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
