package no.bouvet.workshop.solid.solution;

import no.bouvet.workshop.solid.a1_output.CsvConsumer;
import org.junit.Test;

import java.io.File;

public class ConsumerCsvReader extends OpenClosedCsvReader {

    private final CsvConsumer csvConsumer;

    public ConsumerCsvReader(CsvConsumer csvConsumer) {
        this.csvConsumer = csvConsumer;
    }

    @Override
    protected void visitLine(String formatted) {
        csvConsumer.visitLine(formatted);
    }

    @Override
    protected void visitEnd(File file) {
        csvConsumer.visitEnd(file);
    }

    @Override
    protected void visitStart(File file) {
        csvConsumer.visitStart(file);
    }
}
