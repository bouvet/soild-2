package no.bouvet.workshop.solid.visitor;

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
