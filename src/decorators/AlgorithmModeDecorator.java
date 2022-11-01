package decorators;

import algorithms.Algorithm;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

//Decorator abstract class
public abstract class AlgorithmModeDecorator implements Algorithm {

    protected final Algorithm algorithm;
    protected final byte[] modeSpec;

    public AlgorithmModeDecorator(Algorithm algorithm, String modeSpec) {
        this.algorithm = algorithm;
        this.modeSpec = Arrays.copyOfRange(modeSpec.getBytes(StandardCharsets.UTF_16BE), 0, 8);
    }

    @Override
    public byte[] encrypt(byte[] input) {
        return algorithm.encrypt(input);
    }

    @Override
    public byte[] decrypt(byte[] input) {
        return algorithm.decrypt(input);
    }
}
