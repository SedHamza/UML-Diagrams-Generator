package org.mql.java.xml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class XMLUtils {
    public static void writeToFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
