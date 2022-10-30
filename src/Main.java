import algorithms.Algorithm;
import algorithms.DES;
import algorithms.TripleDES;
import decorators.CBCDecorator;
import decorators.CFBDecorator;
import decorators.CTRDecorator;
import decorators.OFBDecorator;
import utils.FileReadHelper;
import utils.FileWriteHelper;
import utils.Logger;
import utils.Stopwatch;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");

        String operation = args[0];
        String inputFile = args[2];
        String outputFile = args[4];
        String cryptAlgorithm = args[5];
        String mode = args[6];
        String keyFile = args[7];

        Algorithm algorithm;
        Stopwatch watch = new Stopwatch();
        FileReadHelper inputReader = new FileReadHelper(inputFile);
        FileReadHelper keyReader = new FileReadHelper(keyFile);
        FileWriteHelper outputWriter = new FileWriteHelper(outputFile);

        String keyString = keyReader.buildKeyMap().get("Key");
        String inputText = inputReader.getInput();

        Logger logger = new Logger(inputFile, outputFile, operation, cryptAlgorithm, mode);

        // TODO: switch içlerine print at ve dene tek tek
        switch (cryptAlgorithm) {
            case "DES" -> algorithm = new DES(keyString);
            case "3DES" -> algorithm = new TripleDES(keyString);
            default -> {
                System.out.println("ALGORITHM (des/3des) SELECTION FAILED!");
                algorithm = new DES(keyString);
            }
        }

        switch (mode) {
            case "CBC" -> algorithm = new CBCDecorator(algorithm);
            case "CFB" -> algorithm = new CFBDecorator(algorithm);
            case "OFB" -> algorithm = new OFBDecorator(algorithm);
            case "CTR" -> algorithm = new CTRDecorator(algorithm);
            default -> {
                System.out.println("ALGORITHM MODE (cbc/cfb..) SELECTION FAILED!");
                algorithm = new CBCDecorator(algorithm);
            }
        }

        switch (operation) {
            case "-e" -> {
                watch.start();
                algorithm.encrypt(inputText);
                watch.stop();
            }
            case "-d" -> {
                watch.start();
                algorithm.decrypt(inputText);
                watch.stop();
            }
            default -> System.out.println("ALGORITHM OPERATION (enc/dec) SELECTION FAILED!");
        }
        logger.logToFile(watch.getElapsedTime());
    }
}