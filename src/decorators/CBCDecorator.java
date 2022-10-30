package decorators;

import algorithms.Algorithm;

public class CBCDecorator extends AlgorithmModeDecorator {
    public CBCDecorator(Algorithm algorithm) {
        super(algorithm);
    }

    @Override
    public String encrypt(String input) {

        return super.encrypt(input);
    }

    @Override
    public String decrypt(String input) {
        return super.decrypt(input);
    }
}
