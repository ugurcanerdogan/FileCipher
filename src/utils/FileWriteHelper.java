package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteHelper {

    public FileWriteHelper() {
    }

    public static void writeString(String path, String str, boolean isDecryption) throws IOException {
        File myFile = new File(path);
        FileWriter myWriter = new FileWriter(path, false);
        if (isDecryption) {
            myWriter.write(str.trim());
        } else {
            myWriter.write(str);
        }
        myWriter.close();
    }

    public static void logToFile(String path, String str) throws IOException {
        File myFile = new File(path);
        FileWriter myWriter = new FileWriter(path, true);
        myWriter.write(str);
        myWriter.write("\n");
        myWriter.close();
    }
}
