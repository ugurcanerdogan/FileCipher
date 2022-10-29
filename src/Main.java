import algorithms.Algorithm;
import algorithms.DES;
import algorithms.TripleDES;
import decorators.CBCDecorator;
import decorators.CFBDecorator;
import decorators.CTRDecorator;
import decorators.OFBDecorator;
import utils.FileReadHelper;
import utils.FileWriteHelper;

public class Main {
    public static void main2(String[] args) {
        System.out.println("Hello world!");

        String operation = args[0];
        String cryptAlgorithm = args[5];
        String mode = args[6];

        String inputFile = args[2];
        String outputFile = args[4];
        String keyFile = args[7];

        FileReadHelper inputReader = new FileReadHelper(inputFile);
        FileReadHelper keyReader = new FileReadHelper(keyFile);
        FileWriteHelper outputWriter = new FileWriteHelper(outputFile);


        Algorithm algorithm;

        // TODO: switch içlerine print at ve dene tek tek
        switch (cryptAlgorithm) {
            case "DES":
                algorithm = new DES();
                break;
            case "3DES":
                algorithm = new TripleDES();
                break;
            default:
                algorithm = new DES();
                break;
        }

        switch (mode) {
            case "CBC":
                algorithm = new CBCDecorator(algorithm);
                break;
            case "CFB":
                algorithm = new CFBDecorator(algorithm);
                break;
            case "OFB":
                algorithm = new OFBDecorator(algorithm);
                break;
            case "CTR":
                algorithm = new CTRDecorator(algorithm);
                break;
            default:
                algorithm = new CBCDecorator(algorithm);
                break;
        }

        switch (operation) {
            case "-e":
                algorithm.encrypt();
                break;
            case "-d":
                algorithm.decrypt();
                break;
            default:
                algorithm.encrypt();
                break;
        }
    }

    public static void main(String[] args) {

    }
}