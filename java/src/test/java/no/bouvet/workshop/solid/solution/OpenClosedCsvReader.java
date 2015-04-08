package no.bouvet.workshop.solid.solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OpenClosedCsvReader {

    public void parse(File file) throws IOException {
        visitStart(file);
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] country = line.split(",");
                    visitLine(format(country));
                }
            }
        } catch (IOException e) {
            handle(file, e);
        }
        visitEnd(file);
    }

    protected void visitStart(File file) {
        System.out.println("Starting to parse: " + file);
    }

    protected void visitEnd(File file) {
        System.out.println("Done parsing: " + file);
    }

    protected void visitLine(String formatted) {
        System.out.println(formatted);
    }

    protected String format(String[] country) {
        return "Country [code= " + country[4] + " , name=" + country[5] + "]";
    }

    protected void handle(File file, IOException exception) throws IOException {
        throw exception;
    }
}
