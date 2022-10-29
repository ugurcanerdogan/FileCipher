package utils;

import java.io.FileInputStream;
import java.util.HashMap;

public class FileReadHelper {
    StringBuilder inputString = new StringBuilder();

    public FileReadHelper(String inputFile) {

        try {
            FileInputStream fin = new FileInputStream(inputFile);
            int i;
            while ((i = fin.read()) != -1) {
                this.inputString.append((char) i);
            }
            fin.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String buildString() {
        return this.inputString.toString();
    }

    public HashMap<String, String> buildKeyDetails() {
        HashMap<String, String> keyDetails = new HashMap<>();
        String keyString = this.buildString();
        String[] keyElements = keyString.split(" - ");
        keyDetails.put("IV", keyElements[0]);
        keyDetails.put("Key", keyElements[1]);
        keyDetails.put("Nonce", keyElements[2]);
        return keyDetails;
    }
}
