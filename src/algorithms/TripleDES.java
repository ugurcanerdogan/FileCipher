package algorithms;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class TripleDES implements Algorithm {
    Base64.Encoder encoder;
    Base64.Decoder decoder;
    SecretKeySpec sKey;
    private Cipher encCipher;
    private Cipher decCipher;

    public TripleDES() throws Exception {
        initCiphers();
    }

    public TripleDES(String key) throws Exception {
        this.sKey = new SecretKeySpec(key.getBytes(), "3DES");
        initCiphers();
    }

    public void initCiphers() throws Exception {
        this.encCipher = Cipher.getInstance("3DES/ECB/PKCS5Padding");
        this.decCipher = Cipher.getInstance("3DES/ECB/PKCS5Padding");

        this.encCipher.init(Cipher.ENCRYPT_MODE, sKey);
        this.decCipher.init(Cipher.DECRYPT_MODE, sKey);

        this.encoder = java.util.Base64.getEncoder();
        this.decoder = java.util.Base64.getDecoder();
    }

    @Override
    public String encrypt(String input) {
        byte[] encrypted = new byte[0];
        try {
            encrypted = this.encCipher.doFinal(input.getBytes());
        } catch (Exception e) {
            System.out.println(e);
        }
        return this.encoder.encodeToString(encrypted);
    }

    @Override
    public String decrypt(String input) {
        byte[] decrypted = new byte[0];
        try {
            decrypted = this.decCipher.doFinal(this.decoder.decode(input));
        } catch (Exception e) {
            System.out.println(e);
        }
        return new String(decrypted);
    }

    public void setKey(String key) {
        this.sKey = new SecretKeySpec(key.getBytes(), "3DES");
    }
}
