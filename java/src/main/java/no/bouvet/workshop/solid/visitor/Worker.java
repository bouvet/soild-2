package no.bouvet.workshop.solid.visitor;

import java.math.BigDecimal;

public abstract class Worker {

    private final String name;

    public Worker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getReport();

    public abstract int getYearlyCosts();
}
