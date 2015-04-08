package no.bouvet.workshop.solid;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CsvReaderTest {

    public static final String FILE_NAME = "GeoIPCountryWhois-cut.csv";

    private ByteArrayOutputStream outContent, errContent;

    private PrintStream originalOut, originalErr;

    @Before
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        originalErr = System.err;
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testCsvReaderWorksAsExpected() throws Exception {
        CsvReader csvReader = new CsvReader();
        File file = new File(getClass().getResource("/" + FILE_NAME).getFile());
        csvReader.parse(file);
        assertThat(outContent.toByteArray().length, is(40604));
        assertThat(errContent.toByteArray().length, is(0));
    }

    @Test(expected = IOException.class)
    public void testCsvReaderThrowsException() throws Exception {
        CsvReader csvReader = new CsvReader();
        csvReader.parse(new File("foo"));
    }
}