package utils;

import java.io.IOException;

public class Logger {
    String inputFile;
    String outputFile;
    String operation;
    String cryptAlgorithm;
    String mode;

    public Logger(String inputFile, String outputFile, String operation, String cryptAlgorithm, String mode) {

        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.cryptAlgorithm = cryptAlgorithm;
        this.mode = mode;

        if (operation.equals("-e")) {
            this.operation = "enc";
        } else {
            this.operation = "dec";
        }
    }

    public void logToFile(long elapsedTime) throws IOException {
        String logText = this.inputFile + " " + this.outputFile + " " + this.operation + " " + this.cryptAlgorithm + " " + this.mode + " " + elapsedTime;
        FileWriteHelper.logToFile("run.log", logText);
    }
}
