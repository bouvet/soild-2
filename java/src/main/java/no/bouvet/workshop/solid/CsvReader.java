package no.bouvet.workshop.solid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {

    public void parse(File file) throws IOException {
        System.out.println("Starting to parse: " + file);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] country = line.split(",");
                System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
            }
        }
        System.out.println("Done parsing: " + file);
    }
}
