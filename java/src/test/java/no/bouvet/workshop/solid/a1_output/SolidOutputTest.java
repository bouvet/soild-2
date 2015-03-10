package no.bouvet.workshop.solid.a1_output;

import no.bouvet.workshop.solid.CsvReader;
import no.bouvet.workshop.solid.CsvReaderTest;
import org.junit.Test;

import java.io.File;

public class SolidOutputTest {

    /**
     * For this exercise, you are to refactor the CsvReader in order to allow for
     * consuming the output in an alternative manner. For this exercise, you should:
     *
     * 1. Refactor CsvReader without breaking the API of this class for other users.
     * 2. Create a subclass of the refactored CsvReader which directs the output
     *    to the CsvConsumer below.
     */
    @Test
    public void testSolidOutput() throws Exception {
        CsvConsumer consumer = new CsvConsumer();
        CsvReader csvReader = null;

        // You are only allowed to refactor CsvReader and you can insert code here.
        // No other test must break by your changes, the original API of CsvReader
        // must stay binary compatible!

        csvReader.parse(new File(getClass().getResource("/" + CsvReaderTest.FILE_NAME).getFile()));
        consumer.assertState();
    }
}
