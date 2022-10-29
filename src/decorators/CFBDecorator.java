package decorators;

import algorithms.Algorithm;

public class CFBDecorator extends AlgorithmModeDecorator{
    public CFBDecorator(Algorithm encryptionMethod) {
        super(encryptionMethod);
    }
}
