package decorators;

import algorithms.Algorithm;

import java.util.Arrays;

public class AlgorithmModeDecorator implements Algorithm {

    private final Algorithm algorithm;

    public AlgorithmModeDecorator(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    protected static byte[][] dismantleInput(byte[] inputArray) {
        int numberOfFullBlocks = inputArray.length / 8;
        int lastBlockLength = inputArray.length % 8;
        int pointer = 0;
        byte[][] dismantledParts = new byte[numberOfFullBlocks + 1][];

        for (int i = 0; i < numberOfFullBlocks; i++) {
            byte[] dismantledPart = Arrays.copyOfRange(inputArray, pointer, pointer + 8);
            dismantledParts[i] = dismantledPart;
            pointer += 8;
        }
        dismantledParts[dismantledParts.length - 1] = Arrays.copyOfRange(inputArray, pointer, pointer + lastBlockLength);
        return dismantledParts;
    }

    protected static byte[] xor(byte[] a1, byte[] a2) {
        byte[] result = new byte[a1.length];
        for (int i = 0; i < a1.length; i++) {
            result[i] = (byte) (a1[i] ^ a2[i]);
        }
        return result;
    }

    @Override
    public String encrypt(String input) {
        return algorithm.encrypt(input);
    }

    @Override
    public String decrypt(String input) {
        return algorithm.decrypt(input);
    }
}
