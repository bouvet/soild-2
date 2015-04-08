package no.bouvet.workshop.solid.solution;

import java.io.File;

public class DecoratingCsvReader extends OpenClosedCsvReader {

    @Override
    protected void visitEnd(File file) {
        /* do nothing */
    }

    @Override
    protected void visitStart(File file) {
        /* do nothing */
    }

    @Override
    protected String format(String[] country) {
        return country[0] + "-" + country[1] + ":" + country[4];
    }
}
