package no.bouvet.workshop.solid.a5_visitor;

import no.bouvet.workshop.solid.visitor.Company;
import no.bouvet.workshop.solid.visitor.Consultant;
import no.bouvet.workshop.solid.visitor.Employee;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * The abstract base-class Worker forces sub-classes to calculate YearlyCost and Report. Now there is a demand to also make a XmlReport for workers.
 * The requirement for the XmlReport is defined in the failing test.
 * We could follow the current pattern in the code-base, and add a XmlReport property to the Worker class and sub-classes.
 * This however, violates the Open-Closed principle, as we need to change the Worker class and sub-classes when functionality like this is added.
 *
 * So, instead we would like to rewrite the Worker-class to use the visitor-pattern so that it is open for changes like this in the future.
 * 
 * 1. Make the Worker-classes accept a visitor.
 * 2. Rewrite the existing yearly-cost and report functionality as visitors.
 * 3. Write the new XmlReport as a visitor. All test should be green after this.
 */
public class CompanyTest {

    private static Company createTestCompany() {
        Company company = new Company();
        company.addWorker(new Employee("Erna Solberg", "CEO", 100000));
        company.addWorker(new Consultant("Bjarne Håkon Hanssen", "First House", 80000));
        company.addWorker(new Employee("Siv Jensen", "CFO", 70000));
        return company;
    }

    @Test
    public void YearlyWorkerCost_should_return_total_cost_for_all_employees_and_consultants() {
        Company company = createTestCompany();
        int result = company.getYearlyWorkerCost();
        assertThat(result, is(3000000));
    }

    @Test
    public void WorkerReport_should_return_information_on_all_workers_separated_by_lineBreak() {
        Company company = createTestCompany();
        String[] result = company.getWorkerReport().split(System.lineSeparator());
        assertThat(result.length, is(3));
        assertThat(result[0], is("Employee Erna Solberg works as CEO and earns 100000 per month."));
        assertThat(result[1], is("Consultant Bjarne Håkon Hanssen from First House costs 80000 per month."));
        assertThat(result[2], is("Employee Siv Jensen works as CFO and earns 70000 per month."));
    }
}
