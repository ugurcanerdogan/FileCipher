package decorators;

import algorithms.Algorithm;
import utils.AlgorithmHelper;

//Decorator concrete class - CBC MODE
public class CBCDecorator extends AlgorithmModeDecorator {
    public CBCDecorator(Algorithm algorithm, String modeSpec) {
        super(algorithm, modeSpec);
    }

    @Override
    public byte[] encrypt(byte[] input) {

        byte[][] inputBlocks = AlgorithmHelper.dismantleInput(input);
        byte[][] outputBlocks = new byte[inputBlocks.length][];
        int indexCounter = 0;
        byte[] encryptionInput;
        byte[] encryptionOutput = new byte[0];

        for (byte[] block : inputBlocks) {
            if (indexCounter == 0) {
                encryptionInput = AlgorithmHelper.xor(this.modeSpec, block);
            } else {
                encryptionInput = AlgorithmHelper.xor(encryptionOutput, block);
            }
            encryptionOutput = this.algorithm.encrypt(encryptionInput);
            outputBlocks[indexCounter] = encryptionOutput;
            indexCounter++;
        }
        return AlgorithmHelper.mergeInput(outputBlocks);
    }

    @Override
    public byte[] decrypt(byte[] input) {

        byte[][] inputBlocks = AlgorithmHelper.dismantleInput(input);
        byte[][] outputBlocks = new byte[inputBlocks.length][];
        int indexCounter = 0;
        byte[] decryptionOutput;
        byte[] prevBlock = new byte[0];

        for (byte[] block : inputBlocks) {
            byte[] output;
            decryptionOutput = this.algorithm.decrypt(block);
            if (indexCounter == 0) {
                output = AlgorithmHelper.xor(this.modeSpec, decryptionOutput);
            } else {
                output = AlgorithmHelper.xor(prevBlock, decryptionOutput);
            }
            prevBlock = block;
            outputBlocks[indexCounter] = output;
            indexCounter++;
        }
        return AlgorithmHelper.mergeInput(outputBlocks);
    }
}
