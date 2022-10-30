package utils;

import java.io.FileInputStream;
import java.util.HashMap;

public class FileReadHelper {
    String inputString;

    public FileReadHelper(String inputFile) {
        StringBuilder input = new StringBuilder();

        try {
            FileInputStream fin = new FileInputStream(inputFile);
            int i;
            while ((i = fin.read()) != -1) {
                input.append((char) i);
            }
            fin.close();
            this.inputString = input.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getInput() {
        return this.inputString;
    }

    public HashMap<String, String> buildKeyMap() {
        HashMap<String, String> keyDetails = new HashMap<>();
        String[] keyElements = inputString.split(" - ");
        keyDetails.put("IV", keyElements[0]);
        keyDetails.put("Key", keyElements[1]);
        keyDetails.put("Nonce", keyElements[2]);
        return keyDetails;
    }
}
