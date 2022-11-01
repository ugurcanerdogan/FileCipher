package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class FileReadHelper {
    String inputString;

    public FileReadHelper(String path) throws IOException {
        this.inputString = readInput(path);
    }

    public static String readInput(String inputPath) throws IOException {
        Path fileName = Path.of(inputPath);
        return Files.readString(fileName);
    }

    public String getInput() {
        return this.inputString;
    }

    public HashMap<String, String> buildKeyMap() {
        HashMap<String, String> keyDetails = new HashMap<>();
        String[] keyElements = this.inputString.split(" - ");
        keyDetails.put("IV", keyElements[0]);
        keyDetails.put("Key", keyElements[1]);
        keyDetails.put("Nonce", keyElements[2]);
        return keyDetails;
    }
}
