package no.bouvet.workshop.solid.delegation;

import java.io.IOException;

public interface ErrorStrategy {

    void handle(Exception e) throws IOException;
}
