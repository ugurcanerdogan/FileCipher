package utils;

import java.io.FileOutputStream;

public class FileWriteHelper {
    FileOutputStream fout = null;

    public FileWriteHelper(String outputFile) {

        try {
            this.fout = new FileOutputStream(outputFile, true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void writeString(String outputText) {
        try {
            char[] chars = outputText.toCharArray();
            for (int i = 0; i < outputText.length(); i++) {
                this.fout.write(chars[i]);
            }
            this.fout.write(10);
            this.fout.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
