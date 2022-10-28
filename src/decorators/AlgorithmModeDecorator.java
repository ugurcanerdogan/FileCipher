package decorators;

import algorithms.EncryptionAlgorithm;

public class AlgorithmModeDecorator implements EncryptionAlgorithm {

    private EncryptionAlgorithm encryptionMethod;

    public AlgorithmModeDecorator(EncryptionAlgorithm encryptionMethod) {
        this.encryptionMethod = encryptionMethod;
    }

    @Override
    public void encrypt() {

    }

    @Override
    public void decrypt() {

    }
}
