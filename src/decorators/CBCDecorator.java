package decorators;

import algorithms.Algorithm;

public class CBCDecorator extends AlgorithmModeDecorator{
    public CBCDecorator(Algorithm algorithm) {
        super(algorithm);
    }
}
