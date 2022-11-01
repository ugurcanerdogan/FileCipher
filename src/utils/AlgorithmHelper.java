package utils;

import java.util.Arrays;

public class AlgorithmHelper {

    public static byte[][] dismantleInput(byte[] inputArray) {
        int numberOfFullBlocks = inputArray.length / 8;
        int lastBlockLength = inputArray.length % 8;
        int pointer = 0;
        int totalSize;

        if (lastBlockLength == 0) {
            totalSize = numberOfFullBlocks;
        } else {
            totalSize = numberOfFullBlocks + 1;
        }
        byte[][] dismantledBlocks = new byte[totalSize][8];

        for (int i = 0; i < numberOfFullBlocks; i++) {
            byte[] block = Arrays.copyOfRange(inputArray, pointer, pointer + 8);
            dismantledBlocks[i] = block;
            pointer += 8;
        }

        int j = 0;
        byte[] newLastBlock = new byte[8];
        if (lastBlockLength != 0) {
            for (int i = pointer; i < pointer + lastBlockLength; i++) {
                newLastBlock[j] = inputArray[i];
                j++;
            }
            dismantledBlocks[dismantledBlocks.length - 1] = newLastBlock;
        }
        return dismantledBlocks;
    }

    public static byte[] mergeInput(byte[][] inputArray) {
        int numberOfTotalBytesExceptLast = (inputArray.length - 1) * 8;
        int lastBlockLength = inputArray[inputArray.length - 1].length;
        int pointer = 0;
        int totalSize = numberOfTotalBytesExceptLast + lastBlockLength;
        byte[] mergedInput = new byte[totalSize];

        for (int i = 0; i < inputArray.length - 1; i++) {
            for (int j = 0; j < 8; j++) {
                mergedInput[pointer] = inputArray[i][j];
                pointer++;
            }
        }
        int j = 0;
        for (int i = pointer; i < totalSize; i++) {
            mergedInput[i] = inputArray[inputArray.length - 1][j];
            j++;
        }
        return mergedInput;
    }

    public static String byteToString(byte aByte) {
        return String.valueOf((char) (aByte & 0xFF));
    }

    public static byte[] stringToByteArray(String inp) {
        byte[] byteArray = new byte[inp.length()];  //Creating a byte array with length of string
        for (int i = 0; i < inp.length(); i++) {
            byteArray[i] = (byte) inp.charAt(i);    //Change ith element of byte array according to ith element of string
        }
        return byteArray;
    }

    public static String byteArrayToString(byte[] inputArray) {
        String res = "";
        for (byte b : inputArray) {
            res += byteToString(b);
        }
        return res;
    }

    public static byte[] xor(byte[] first, byte[] second) {
        if (first.length != second.length)   //checking the length
            throw new IllegalArgumentException("Different byte array sizes: " + first.length + " != " + second.length);
        byte[] array = new byte[first.length];
        for (int i = 0; i < array.length; i++)
            array[i] = (byte) ((first[i] & 0xff) ^ (second[i] & 0xff));

        return array;
    }

    public static byte[] findCounter(int counter) {
        int size = 64;     //final size of byte array
        String str = Integer.toBinaryString(counter);   //convert integer to binary string
        int filled = str.length();      //length of converted binary string
        String binary = "";
        //Fill full zeros until the binary format
        for (int i = 0; i < size - filled; i++) {
            binary += "0";
        }
        binary += str;      //add binary format to string
        int pointer = 0;        //pointer for creating sub strings for 64 bit binary string
        byte[] byteFormat = new byte[8];
        //divide 64 bit binary 8-by-8 and fill the byteArray according to this parts
        for (int i = 0; i < 8; i++) {
            String sub = binary.substring(pointer, pointer + 8);
            byteFormat[i] = (byte) Integer.parseInt(sub, 2);
            pointer += 8;       //slide pointer for next block
        }
        return byteFormat;
    }
}
