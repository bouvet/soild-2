package no.bouvet.workshop.solid.visitor;

public class Consultant extends Worker {

    private final int monthlyFee;

    private final String company;

    public Consultant(String name, int monthlyFee, String company) {
        super(name);
        this.monthlyFee = monthlyFee;
        this.company = company;
    }

    public int getMonthlyFee() {
        return monthlyFee;
    }

    public String getCompany() {
        return company;
    }

    @Override
    public String getReport() {
        return String.format("Consultant %s from %s costs %s per month.", getName(), getCompany(), getMonthlyFee());
    }

    @Override
    public int getYearlyCosts() {
        return getMonthlyFee() * 12;
    }
}
