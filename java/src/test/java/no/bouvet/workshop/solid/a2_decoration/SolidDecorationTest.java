package no.bouvet.workshop.solid.a2_decoration;

import no.bouvet.workshop.solid.CsvReader;
import no.bouvet.workshop.solid.CsvReaderTest;
import no.bouvet.workshop.solid.solution.DecoratingCsvReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;

import java.io.File;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class SolidDecorationTest {

    @Mock
    private PrintStream mockOut, mockErr;

    private PrintStream originalOut, originalErr;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        originalOut = System.out;
        System.setOut(mockOut);
        originalErr = System.err;
        System.setErr(mockErr);
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    /**
     * For this exercise, you are to refactor the CsvReader in order to allow for
     * writing a custom output to the console.
     *
     * 1. Refactor CsvReader without breaking the API of this class for other users.
     * 2. Create a subclass of the refactored CsvReader which writes each entry as
     *    [from IP]-[to IP]:[country code]
     *    to the standard output. No information about the read file should be
     *    written to the console for this output format.

     */
    @Test
    public void testCsvReaderDoesNotThrowException() throws Exception {
        CsvReader csvReader = null;

        // You are only allowed to refactor CsvReader and you can insert code here.
        // No other test must break by your changes and you must not change tests
        // that you implemented before. The original API of CsvReader must stay
        // binary compatible!

        csvReader.parse(new File(getClass().getResource("/" + CsvReaderTest.FILE_NAME).getFile()));

        InOrder inOrder = inOrder(mockOut);
        inOrder.verify(mockOut).println("\"1.0.0.0\"-\"1.0.0.255\":\"AU\"");
        inOrder.verify(mockOut, times(999)).println(anyString());
        inOrder.verifyNoMoreInteractions();
        verifyZeroInteractions(mockErr);
    }
}
