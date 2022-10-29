package decorators;

import algorithms.Algorithm;

public class CTRDecorator extends AlgorithmModeDecorator{
    public CTRDecorator(Algorithm encryptionMethod) {
        super(encryptionMethod);
    }
}
