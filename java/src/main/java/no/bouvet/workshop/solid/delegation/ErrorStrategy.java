package no.bouvet.workshop.solid.delegation;

import java.io.File;
import java.io.IOException;

public interface ErrorStrategy {

    void handle(File file, Exception e) throws IOException;
}
