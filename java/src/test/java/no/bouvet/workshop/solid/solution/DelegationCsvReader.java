package no.bouvet.workshop.solid.solution;

import no.bouvet.workshop.solid.a4_delegation.CsvConsumer;
import no.bouvet.workshop.solid.delegation.Entry;
import no.bouvet.workshop.solid.delegation.ErrorStrategy;
import no.bouvet.workshop.solid.delegation.NamingStrategy;
import no.bouvet.workshop.solid.delegation.OutputConsumer;

import java.io.File;
import java.io.IOException;

public class DelegationCsvReader extends OpenClosedCsvReader {

    private final OutputConsumer outputConsumer;

    private final NamingStrategy namingStrategy;

    private final ErrorStrategy errorStrategy;

    public DelegationCsvReader() {
        outputConsumer = new DefaultVisitorDelegator();
        namingStrategy = new DefaultFormatter();
        errorStrategy = new DefaultErrorHandler();
    }

    public DelegationCsvReader(OutputConsumer outputConsumer,
                               NamingStrategy namingStrategy,
                               ErrorStrategy errorStrategy) {
        this.outputConsumer = outputConsumer;
        this.namingStrategy = namingStrategy;
        this.errorStrategy = errorStrategy;
    }

    @Override
    protected void visitStart(File file) {
        outputConsumer.consumeStart(file);
    }

    @Override
    protected void visitEnd(File file) {
        outputConsumer.consumeEnd(file);
    }

    @Override
    protected void visitLine(String formatted) {
        outputConsumer.consume(formatted);
    }

    @Override
    protected String format(String[] country) {
        return namingStrategy.format(new Entry(
                country[0],
                country[1],
                country[2],
                country[3],
                country[4],
                country[5]
        ));
    }

    @Override
    protected void handle(File file, IOException exception) throws IOException {
        errorStrategy.handle(file, exception);
    }

    private class DefaultVisitorDelegator implements OutputConsumer {

        @Override
        public void consumeStart(File file) {
            DelegationCsvReader.super.visitStart(file);
        }

        @Override
        public void consume(String line) {
            DelegationCsvReader.super.visitLine(line);
        }

        @Override
        public void consumeEnd(File file) {
            DelegationCsvReader.super.visitEnd(file);
        }
    }

    private class DefaultFormatter implements NamingStrategy {

        @Override
        public String format(Entry entry) {
            return DelegationCsvReader.super.format(entry.toArray());
        }
    }

    private class DefaultErrorHandler implements ErrorStrategy {

        @Override
        public void handle(File file, Exception e) throws IOException {
            if (e instanceof IOException) {
                DelegationCsvReader.super.handle(file, (IOException) e);
            } else if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            } else {
                throw new AssertionError(e);
            }
        }
    }

    public static class ConsumerDelegation implements OutputConsumer {

        private final CsvConsumer csvConsumer;

        public ConsumerDelegation(CsvConsumer csvConsumer) {
            this.csvConsumer = csvConsumer;
        }

        @Override
        public void consumeStart(File file) {
            csvConsumer.visitStart(file);
        }

        @Override
        public void consume(String line) {
            csvConsumer.visitLine(line);
        }

        @Override
        public void consumeEnd(File file) {
            csvConsumer.visitEnd(file);
        }
    }

    public static class FormatDelegation implements NamingStrategy {

        @Override
        public String format(Entry entry) {
            return entry.getFromIp() + "-" + entry.getToIp() + ":" + entry.getCountryCode();
        }
    }

    public static class ErrorDelegation implements ErrorStrategy {

        private final CsvConsumer csvConsumer;

        public ErrorDelegation(CsvConsumer csvConsumer) {
            this.csvConsumer = csvConsumer;
        }

        @Override
        public void handle(File file, Exception e) throws IOException {
            csvConsumer.visitLine("Could not read: " + file);
        }
    }
}
