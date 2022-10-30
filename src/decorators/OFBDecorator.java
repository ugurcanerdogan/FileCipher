package decorators;

import algorithms.Algorithm;

public class OFBDecorator extends AlgorithmModeDecorator {
    public OFBDecorator(Algorithm encryptionMethod) {
        super(encryptionMethod);
    }
}
