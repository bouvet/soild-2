package no.bouvet.workshop.solid.a3_errorhandling;

import no.bouvet.workshop.solid.CsvReader;
import no.bouvet.workshop.solid.CsvReaderTest;
import no.bouvet.workshop.solid.solution.ErrorHandlingCsvReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.PrintStream;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyZeroInteractions;

public class SolidErrorHandlingTest {

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
     * For this exercise, you are to refactor the CsvReader in order to better handle errors.
     *
     * 1. Refactor CsvReader without breaking the API of this class for other users.
     * 2. When an IOException occurs, an error message should be written to the console.
     *    This message should be: 'Could not read: [file name]
     *    Remember that old users of the class should still receive an IOException
     *    when an error occurs.
     */
    @Test
    public void testCsvReaderDoesNotThrowException() throws Exception {
        CsvReader csvReader = null;

        // You are only allowed to refactor CsvReader and you can insert code here.
        // No other test must break by your changes and you must not change tests
        // that you implemented before. The original API of CsvReader must stay
        // binary compatible!

        csvReader.parse(new File("foo"));

        verify(mockOut).println("Starting to parse: foo");
        verify(mockOut).println("Could not read: foo");
        verifyNoMoreInteractions(mockOut);
        verifyZeroInteractions(mockErr);
    }
}
