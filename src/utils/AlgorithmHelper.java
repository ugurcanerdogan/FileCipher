package utils;

import java.util.Arrays;

public class AlgorithmHelper {

    // Splits the input one-dimensional byte array into a two-dimensional array containing 8 byte arrays.
    public static byte[][] dismantleInput(byte[] inputArray) {
        int numberOfFullBlocks = inputArray.length / 8;
        int lastBlockLength = inputArray.length % 8; // number of remaining bytes at the last block seperation
        int pointer = 0;
        int totalSize;

        if (lastBlockLength == 0) {
            totalSize = numberOfFullBlocks; // if it can be divided into blocks consisting of 8 bytes, without any leftovers
        } else {
            totalSize = numberOfFullBlocks + 1; // if there are leftovers
        }
        byte[][] dismantledBlocks = new byte[totalSize][8]; // returning 2d array

        for (int i = 0; i < numberOfFullBlocks; i++) {
            byte[] block = Arrays.copyOfRange(inputArray, pointer, pointer + 8); // fill blocks that can be completely filled
            dismantledBlocks[i] = block;
            pointer += 8;
        }

        int j = 0;
        byte[] newLastBlock = new byte[8];
        if (lastBlockLength != 0) {
            for (int i = pointer; i < pointer + lastBlockLength; i++) {
                newLastBlock[j] = inputArray[i]; // fill the last block that can't be completely filled, only place the leftovers
                j++;
            }
            dismantledBlocks[dismantledBlocks.length - 1] = newLastBlock; // place the last block, padded with zeros at the end
        }
        return dismantledBlocks;
    }

    // Reduces the 2d array consisting of 8 byte arrays to one dimension.
    public static byte[] mergeInput(byte[][] inputArray) {
        int numberOfTotalBytes = (inputArray.length) * 8; // calculate how many elements the blocks contain in total
        int pointer = 0;
        byte[] mergedInput = new byte[numberOfTotalBytes];

        for (byte[] bytes : inputArray) {
            for (int j = 0; j < 8; j++) {
                mergedInput[pointer] = bytes[j]; // place each block bytes to 1d array
                pointer++;
            }
        }
        return mergedInput;
    }

    // Byte to string converter with masking.
    public static String byteToString(byte aByte) {
        return String.valueOf((char) (aByte & 0xFF));
    }

    // Moving string elements to byte array indices.
    public static byte[] stringToByteArray(String inp) {
        byte[] byteArray = new byte[inp.length()];
        for (int i = 0; i < inp.length(); i++) {
            byteArray[i] = (byte) inp.charAt(i);    // Move each element of string to array indices
        }
        return byteArray;
    }

    // Converting byte array elements to string characters.
    public static String byteArrayToString(byte[] inputArray) {
        String res = "";
        for (byte b : inputArray) {
            res += byteToString(b);
        }
        return res;
    }

    // XOR function with masking enhancement.
    public static byte[] xor(byte[] first, byte[] second) {
        if (first.length != second.length) { // checking the length
            throw new IllegalStateException("Different byte array sizes: " + first.length + " and " + second.length);
        }
        byte[] array = new byte[first.length];
        for (int i = 0; i < array.length; i++)
            array[i] = (byte) ((first[i] & 0xff) ^ (second[i] & 0xff));
        return array;
    }

    // Function that reflects the digits of an integer value to a byte array.
    // 123 -> {0,0,0,0,0,1,2,3}
    public static byte[] integerToByteArrayView(int counter) {
        int size = 64;
        String empty = "";
        String str = Integer.toBinaryString(counter);   // convert integer to binary string
        for (int i = 0; i < size - str.length(); i++) {
            empty += "0"; // append the required amount of zeros
        }
        empty += str; // append the real binary value of integer
        int pointer = 0;
        byte[] byteFormat = new byte[8];
        for (int i = 0; i < 8; i++) {
            String sub = empty.substring(pointer, pointer + 8); // divide the 64 bit binary by 8 and fill the byteArray accordingly
            byteFormat[i] = (byte) Integer.parseInt(sub, 2);
            pointer += 8; // go to the next block
        }
        return byteFormat;
    }
}
