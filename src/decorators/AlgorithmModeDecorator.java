package decorators;

import algorithms.Algorithm;

public class AlgorithmModeDecorator implements Algorithm {

    private final Algorithm algorithm;


    public AlgorithmModeDecorator(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public void encrypt() {
        this.algorithm.encrypt();
    }

    @Override
    public void decrypt() {
        this.algorithm.decrypt();
    }
}
