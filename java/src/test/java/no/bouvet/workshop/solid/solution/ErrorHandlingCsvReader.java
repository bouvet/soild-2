package no.bouvet.workshop.solid.solution;

import java.io.File;
import java.io.IOException;

public class ErrorHandlingCsvReader extends OpenClosedCsvReader {

    @Override
    protected void handle(File file, IOException exception) throws IOException {
        System.out.println("Could not read: " + file);
    }
}
