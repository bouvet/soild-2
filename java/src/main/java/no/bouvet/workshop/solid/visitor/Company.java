package no.bouvet.workshop.solid.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Company {

    private final List<Worker> workers;

    public Company() {
        workers = new ArrayList<>();
    }

    public void addWorker(Worker worker) {
        workers.add(worker);
    }

    public int getYearlyWorkerCost() {
        return workers.stream().mapToInt(Worker::getYearlyCosts).sum();
    }

    public String getWorkerReport() {
        StringBuilder stringBuilder = new StringBuilder();
        workers.stream().findFirst().ifPresent(worker -> stringBuilder.append(worker.getReport()));
        workers.stream().skip(1).forEach(worker -> stringBuilder.append(System.lineSeparator()).append(worker.getReport()));
        return stringBuilder.toString();
    }
}
