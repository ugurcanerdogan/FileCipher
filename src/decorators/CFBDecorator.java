package decorators;

import algorithms.Algorithm;
import utils.AlgorithmHelper;

//Decorator concrete class - CFB MODE
public class CFBDecorator extends AlgorithmModeDecorator {
    public CFBDecorator(Algorithm algorithm, String modeSpec) {
        super(algorithm, modeSpec);
    }

    @Override
    public byte[] encrypt(byte[] input) {

        byte[][] inputBlocks = AlgorithmHelper.dismantleInput(input);
        byte[][] outputBlocks = new byte[inputBlocks.length][];
        int indexCounter = 0;
        byte[] xorInput;
        byte[] xorOutput = new byte[0];

        for (byte[] block : inputBlocks) {
            if (indexCounter == 0) {
                xorInput = this.algorithm.encrypt(this.modeSpec);
                xorOutput = AlgorithmHelper.xor(xorInput, block);
            } else {
                xorInput = this.algorithm.encrypt(xorOutput);
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
        byte[] xorInput;
        byte[] xorOutput;
        byte[] oldBlock = new byte[0];

        for (byte[] block : inputBlocks) {
            if (indexCounter == 0) {
                xorInput = this.algorithm.encrypt(this.modeSpec);
                xorOutput = AlgorithmHelper.xor(xorInput, block);
            } else {
                xorInput = this.algorithm.encrypt(oldBlock);
                xorOutput = AlgorithmHelper.xor(xorInput, block);
            }
            oldBlock = block;
            outputBlocks[indexCounter] = xorOutput;
            indexCounter++;
        }
        return AlgorithmHelper.mergeInput(outputBlocks);
    }
}
