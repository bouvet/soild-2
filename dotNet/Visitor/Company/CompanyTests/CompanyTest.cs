using Company;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Linq;

namespace CompanyTests
{
    /* 
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
     * 
     * */
    [TestClass]
    public class CompanyTest
    {
        private Company.Company CreateTestCompany()
        {
            var company = new Company.Company();
            company.AddWorker(new Employee("Erna Solberg", "CEO", 100000));
            company.AddWorker(new Consultant("Bjarne Håkon Hanssen", "First House", 80000));
            company.AddWorker(new Employee("Siv Jensen", "CFO", 70000));
            return company;
        }

        [TestMethod]
        public void YearlyWorkerCost_should_return_total_cost_for_all_employees_and_consultants()
        {
            var company = CreateTestCompany();
            var result = company.YearlyWorkerCost;
            Assert.AreEqual(3000000, result);
        }

        [TestMethod]
        public void WorkerReport_should_return_information_on_all_workers_separated_by_lineBreak()
        {
            var company = CreateTestCompany();
            var result = company.WorkerReport.Split(new[] { Environment.NewLine }, StringSplitOptions.None);
            Assert.AreEqual(3, result.Length);
            Assert.AreEqual("Employee Erna Solberg works as CEO and earns 100000 per month.", result[0]);
            Assert.AreEqual("Consultant Bjarne Håkon Hanssen from First House costs 80000 per month.", result[1]);
            Assert.AreEqual("Employee Siv Jensen works as CFO and earns 70000 per month.", result[2]);
        }

        [TestMethod]
        public void WorkerReportXml_should_return_information_on_all_workers_as_Xml()
        {
            var company = CreateTestCompany();
            var result = company.WorkerReportXml;
            var root = result.Element("Company");
            Assert.IsNotNull(root);
            Assert.AreEqual(3, root.Descendants().Count());
            Assert.AreEqual("<Employee Name=\"Erna Solberg\" Position=\"CEO\" MonthlySalary=\"100000\" />", root.Descendants().ElementAt(0).ToString());
            Assert.AreEqual("<Consultant Name=\"Bjarne Håkon Hanssen\" Company=\"First House\" MonthlyFee=\"80000\" />", root.Descendants().ElementAt(1).ToString());
            Assert.AreEqual("<Employee Name=\"Siv Jensen\" Position=\"CFO\" MonthlySalary=\"70000\" />", root.Descendants().ElementAt(2).ToString());
        }
    }
}
