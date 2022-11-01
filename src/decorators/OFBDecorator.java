package decorators;

import algorithms.Algorithm;
import utils.AlgorithmHelper;

//Decorator concrete class - OFB MODE
public class OFBDecorator extends AlgorithmModeDecorator {
    public OFBDecorator(Algorithm algorithm, String modeSpec) {
        super(algorithm, modeSpec);
    }

    @Override
    public byte[] encrypt(byte[] input) {

        byte[][] inputBlocks = AlgorithmHelper.dismantleInput(input);
        byte[][] outputBlocks = new byte[inputBlocks.length][];
        int indexCounter = 0;
        byte[] xorInput = new byte[0];
        byte[] xorOutput;

        for (byte[] block : inputBlocks) {
            if (indexCounter == 0) {
                xorInput = this.algorithm.encrypt(this.modeSpec);
                xorOutput = AlgorithmHelper.xor(xorInput, block);
            } else {
                xorInput = this.algorithm.encrypt(xorInput);
                xorOutput = AlgorithmHelper.xor(xorInput, block);
            }
            outputBlocks[indexCounter] = xorOutput;
            indexCounter++;
        }
        return AlgorithmHelper.mergeInput(outputBlocks);
    }

    @Override
    public byte[] decrypt(byte[] input) {

        byte[][] inputBlocks = AlgorithmHelper.dismantleInput(input);
        byte[][] outputBlocks = new byte[inputBlocks.length][];
        int indexCounter = 0;
        byte[] xorInput = new byte[0];
        byte[] xorOutput;

        for (byte[] block : inputBlocks) {
            if (indexCounter == 0) {
                xorInput = this.algorithm.encrypt(this.modeSpec);
                xorOutput = AlgorithmHelper.xor(xorInput, block);
            } else {
                xorInput = this.algorithm.encrypt(xorInput);
                xorOutput = AlgorithmHelper.xor(xorInput, block);
            }
            outputBlocks[indexCounter] = xorOutput;
            indexCounter++;
        }
        return AlgorithmHelper.mergeInput(outputBlocks);
    }
}
