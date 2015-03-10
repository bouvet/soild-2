package no.bouvet.workshop.solid.visitor;

import java.math.BigDecimal;

public class Employee extends Worker {

    private final String position;

    private final int monthlySalary;

    public Employee(String name, String position, int monthlySalary) {
        super(name);
        this.position = position;
        this.monthlySalary = monthlySalary;
    }

    @Override
    public String getReport() {
        return String.format("Employee %s works as %s and earns %s per month.",
                getName(), getPosition(), getMonthlySalary());
    }

    @Override
    public int getYearlyCosts() {
        return getMonthlySalary() * 12;
    }

    public String getPosition() {
        return position;
    }

    public int getMonthlySalary() {
        return monthlySalary;
    }
}
