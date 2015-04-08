package no.bouvet.workshop.solid.visitor;

public class Consultant extends Worker {

    private final int monthlyFee;

    private final String company;

    public Consultant(String name, String company, int monthlyFee) {
        super(name);
        this.company = company;
        this.monthlyFee = monthlyFee;
    }

    public int getMonthlyFee() {
        return monthlyFee;
    }

    public String getCompany() {
        return company;
    }

    @Override
    public String getReport() {
        return String.format("Consultant %s from %s costs %d per month.", getName(), getCompany(), getMonthlyFee());
    }

    @Override
    public int getYearlyCosts() {
        return getMonthlyFee() * 12;
    }
}
