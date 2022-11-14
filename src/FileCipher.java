import algorithms.Algorithm;
import algorithms.DES;
import algorithms.TripleDES;
import decorators.CBCDecorator;
import decorators.CFBDecorator;
import decorators.CTRDecorator;
import decorators.OFBDecorator;
import utils.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class FileCipher {
    public static void main(String[] args) throws Exception {

        String operation = args[0];
        String inputFile = args[2];
        String outputFile = args[4];
        String cryptAlgorithm = args[5];
        String mode = args[6];
        String keyFile = args[7];

        Algorithm algorithm;
        Stopwatch watch = new Stopwatch();
        FileReadHelper keyReader = new FileReadHelper(keyFile);

        String keyString = keyReader.buildKeyMap().get("Key");
        String ivString = keyReader.buildKeyMap().get("IV");
        String nonceString = keyReader.buildKeyMap().get("Nonce");

        Logger logger = new Logger(inputFile, outputFile, operation, cryptAlgorithm, mode);

        switch (cryptAlgorithm) {
            case "DES" -> algorithm = new DES(keyString);
            case "3DES" -> algorithm = new TripleDES(keyString);
            default -> {
                System.out.println("ALGORITHM (des/3des) SELECTION FAILED!");
                algorithm = new DES(keyString);
            }
        }

        switch (mode) {
            case "CBC" -> algorithm = new CBCDecorator(algorithm, ivString);
            case "CFB" -> algorithm = new CFBDecorator(algorithm, ivString);
            case "OFB" -> algorithm = new OFBDecorator(algorithm, ivString);
            case "CTR" -> algorithm = new CTRDecorator(algorithm, ivString, nonceString);
            default -> {
                System.out.println("ALGORITHM MODE (cbc/cfb..) SELECTION FAILED!");
                algorithm = new CBCDecorator(algorithm, ivString);
            }
        }

        switch (operation) {
            case "-e" -> {
                FileReadHelper inputReader = new FileReadHelper(inputFile);
                String inputText = new String(Base64.getEncoder().encode(inputReader.getInput().getBytes(StandardCharsets.UTF_16BE)));
                byte[] initalizedByteArray = AlgorithmHelper.stringToByteArray(inputText);
                watch.start();
                byte[] encryptedOutput = algorithm.encrypt(initalizedByteArray);
                watch.stop();
                String output = AlgorithmHelper.byteArrayToString(encryptedOutput);
                FileWriteHelper.writeString(outputFile, output, false);
            }
            case "-d" -> {
                FileReadHelper inputReader = new FileReadHelper(inputFile);
                String inputText = inputReader.getInput();
                byte[] initalizedByteArray = AlgorithmHelper.stringToByteArray(inputText);
                watch.start();
                byte[] decryptedOutput = algorithm.decrypt(initalizedByteArray);
                watch.stop();
                String output = new String(Base64.getDecoder().decode(AlgorithmHelper.byteArrayToString(decryptedOutput).trim()), StandardCharsets.UTF_16BE);
                FileWriteHelper.writeString(outputFile, output, true);
            }
            default -> System.out.println("ALGORITHM OPERATION (enc/dec) SELECTION FAILED!");
        }
        logger.logToFile(watch.getElapsedTime());
    }
}