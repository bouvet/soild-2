package no.bouvet.workshop.solid.solution;

public class OpenClosedCompany {

    public interface Worker {

        String getName();

        void accept(WorkerVisitor visitor);
    }

    public interface WorkerVisitor {

        void visit(Employee employee);

        void visit(Consultant consultant);
    }

    public static class Employee implements Worker {

        private final String name;

        private final String position;

        private final int monthlySalary;

        public Employee(String name, String position, int monthlySalary) {
            this.name = name;
            this.position = position;
            this.monthlySalary = monthlySalary;
        }

        @Override
        public String getName() {
            return name;
        }

        public String getPosition() {
            return position;
        }

        public int getMonthlySalary() {
            return monthlySalary;
        }

        @Override
        public void accept(WorkerVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class Consultant implements Worker {

        private final String name;

        private final String company;

        private final int monthlyFee;

        public Consultant(String name, String company, int monthlyFee) {
            this.name = name;
            this.company = company;
            this.monthlyFee = monthlyFee;
        }

        @Override
        public String getName() {
            return name;
        }

        public String getCompany() {
            return company;
        }

        public int getMonthlyFee() {
            return monthlyFee;
        }

        public void accept(WorkerVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class WorkerReportVisitor implements WorkerVisitor {

        private final StringBuilder reportBuilder;

        public WorkerReportVisitor() {
            reportBuilder = new StringBuilder();
        }

        @Override
        public void visit(Employee employee) {
            String employeeInfo = String.format("Employee %s works as %s and earns %d per month.",
                    employee.getName(),
                    employee.getPosition(),
                    employee.getMonthlySalary());
            reportBuilder.append(employeeInfo);
        }

        @Override
        public void visit(Consultant consultant) {
            String consultantInfo = String.format("Consultant %s from %s costs %d per month.",
                    consultant.getName(),
                    consultant.getCompany(),
                    consultant.getMonthlyFee());
            reportBuilder.append(consultantInfo);
        }

        private String removeTrailingNewLine(String value) {
            if (!value.endsWith(System.lineSeparator())) {
                return value;
            }
            return value.substring(0, value.length() - System.lineSeparator().length());
        }

        public String getReport() {
            return removeTrailingNewLine(reportBuilder.toString());
        }
    }

    public static class YearlyCostVisitor implements WorkerVisitor {

        private int yearlyCost;

        @Override
        public void visit(Employee employee) {
            yearlyCost += employee.getMonthlySalary() * 12;
        }

        @Override
        public void visit(Consultant consultant) {
            yearlyCost += consultant.getMonthlyFee() * 12;
        }

        public int getYearlyCost() {
            return yearlyCost;
        }
    }
}
