package no.bouvet.workshop.solid.delegation;

import java.io.File;

public interface OutputConsumer {

    void consumeStart(File file);

    void consume(String line);

    void consumeEnd(File file);
}
