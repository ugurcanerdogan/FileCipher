package algorithms;

// Decorator Main Interface
public interface Algorithm {

    byte[] encrypt(byte[] input);

    byte[] decrypt(byte[] input);

}
