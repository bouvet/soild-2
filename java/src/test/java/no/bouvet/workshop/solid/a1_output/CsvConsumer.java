package no.bouvet.workshop.solid.a1_output;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class CsvConsumer {

    private final List<String> lines;

    private File startCalled, endCalled;

    public CsvConsumer() {
        this.lines = new LinkedList<>();
    }

    public void visitStart(File file) {
        assertThat("visitStart was visited before", startCalled, nullValue(File.class));
        startCalled = file;
    }

    public void visitLine(String line) {
        lines.add(line);
    }

    public void visitEnd(File file) {
        assertThat("visitEnd was visited before", endCalled, nullValue(File.class));
        endCalled = file;
    }

    public void assertState() {
        assertThat("There were not 1000 lines visited as expected", lines.size(), is(1000));
        assertThat("Sample line one is not matching", lines.get(0), is("Country [code= \"AU\" , name=\"Australia\"]"));
        assertThat("Sample line 1000 is not matching", lines.get(999), is("Country [code= \"ES\" , name=\"Spain\"]"));
        assertThat("visitStart was not called", startCalled, notNullValue(File.class));
        assertThat("visitEnd was not called", endCalled, notNullValue(File.class));
    }
}
