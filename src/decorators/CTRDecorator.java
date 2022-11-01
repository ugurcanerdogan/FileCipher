package decorators;

import algorithms.Algorithm;
import utils.AlgorithmHelper;

//Decorator concrete class - CTR MODE
public class CTRDecorator extends AlgorithmModeDecorator {

    private final String nonce;

    public CTRDecorator(Algorithm algorithm, String modeSpec, String nonce) {
        super(algorithm, modeSpec);
        this.nonce = nonce;
    }

    @Override
    public byte[] encrypt(byte[] input) {

        byte[][] inputBlocks = AlgorithmHelper.dismantleInput(input);
        byte[][] outputBlocks = new byte[inputBlocks.length][];
        int indexCounter = 0;
        int ctr = 0;
        byte[] nonceBytes = AlgorithmHelper.xor(AlgorithmHelper.stringToByteArray(this.nonce.substring(0, 8)), AlgorithmHelper.integerToByteArrayView(ctr));
        byte[] encOutput;
        byte[] xorOutput;

        for (byte[] block : inputBlocks) {
            encOutput = this.algorithm.encrypt(nonceBytes);
            xorOutput = AlgorithmHelper.xor(encOutput, block);
            outputBlocks[indexCounter] = xorOutput;
            ctr += 1;
            nonceBytes = AlgorithmHelper.xor(AlgorithmHelper.stringToByteArray(this.nonce.substring(0, 8)), AlgorithmHelper.integerToByteArrayView(ctr));
            indexCounter++;
        }
        return AlgorithmHelper.mergeInput(outputBlocks);
    }

    @Override
    public byte[] decrypt(byte[] input) {

        byte[][] inputBlocks = AlgorithmHelper.dismantleInput(input);
        byte[][] outputBlocks = new byte[inputBlocks.length][];
        int indexCounter = 0;
        int ctr = 0;
        byte[] nonceBytes = AlgorithmHelper.xor(AlgorithmHelper.stringToByteArray(this.nonce.substring(0, 8)), AlgorithmHelper.integerToByteArrayView(ctr));
        byte[] encOutput;
        byte[] xorOutput;

        for (byte[] block : inputBlocks) {
            encOutput = this.algorithm.encrypt(nonceBytes);
            xorOutput = AlgorithmHelper.xor(encOutput, block);
            outputBlocks[indexCounter] = xorOutput;
            ctr += 1;
            nonceBytes = AlgorithmHelper.xor(AlgorithmHelper.stringToByteArray(this.nonce.substring(0, 8)), AlgorithmHelper.integerToByteArrayView(ctr));
            indexCounter++;
        }
        return AlgorithmHelper.mergeInput(outputBlocks);
    }
}
