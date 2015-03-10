package no.bouvet.workshop.solid.a4_delegation;

import no.bouvet.workshop.solid.CsvReader;
import no.bouvet.workshop.solid.CsvReaderTest;
import no.bouvet.workshop.solid.delegation.ErrorStrategy;
import no.bouvet.workshop.solid.delegation.NamingStrategy;
import no.bouvet.workshop.solid.delegation.OutputConsumer;
import org.junit.Test;

import java.io.File;

import static org.mockito.Mockito.verify;

public class SolidDelegationTest {

    /**
     * For this exercise, you are to create a subclass of the CsvReader that allows
     * the delegation of
     *
     * 1. Create a subclass of the refactored CsvReader which allows the delegation of
     *    the responsibility to individual tasks. The delegation interfaces are given in
     *    the delegation package.
     * 2. The output must be redirected to the given CsvConsumer.
     * 3. The output must read like in 'a2'.
     * 4. The exception strategy must be like in 'a3'.
     */
    @Test
    public void testSolidOutput() throws Exception {
        CsvConsumer consumer = new CsvConsumer();
        NamingStrategy namingStrategy = null;
        ErrorStrategy errorStrategy = null;
        OutputConsumer outputConsumer = null;
        CsvReader csvReader = null;

        // You are only allowed to refactor CsvReader and you can insert code here.
        // No other test must break by your changes, the original API of CsvReader
        // must stay binary compatible!

        csvReader.parse(new File(getClass().getResource("/" + CsvReaderTest.FILE_NAME).getFile()));
        consumer.assertState();

        consumer.reset();
        csvReader.parse(new File("foo"));
        consumer.assertError("foo");
    }
}
