using System.Text;
using System;
using System.Xml.Linq;

namespace Company
{
    public interface IWorkerVisitor
    {
        void visit(Employee employee);
        void visit(Consultant consultant);
    }

    public class WorkerReportVisitor : IWorkerVisitor
    {
        private readonly StringBuilder _reportBuilder;

        public WorkerReportVisitor()
        {
            _reportBuilder = new StringBuilder();
        }

        public void visit(Employee employee)
        {
            var employeeInfo = string.Format("Employee {0} works as {1} and earns {2} per month.", employee.Name, employee.Position, employee.MonthySalary);
            _reportBuilder.AppendLine(employeeInfo);
        }

        public void visit(Consultant consultant)
        {
            var consultantInfo = string.Format("Consultant {0} from {1} costs {2} per month.", consultant.Name, consultant.Company, consultant.MonthlyFee);
            _reportBuilder.AppendLine(consultantInfo);
        }

        private string RemoveTrailingNewLine(string value)
        {
            if(!value.EndsWith(Environment.NewLine))
               return value;

            return value.Substring(0, value.Length - Environment.NewLine.Length);
        }

        public string Report { get { return RemoveTrailingNewLine(_reportBuilder.ToString()); } }
    }

    public class XmlReportVisitor : IWorkerVisitor
    {
        private readonly XDocument _xmlDocument;
        private readonly XElement _root;
        
        public XmlReportVisitor()
        {
            _xmlDocument = new XDocument();
            _root = new XElement("Company");
            _xmlDocument.Add(_root);
        }

        public void visit(Employee employee)
        {
            _root.Add(new XElement("Employee",
                new XAttribute("Name", employee.Name),
                new XAttribute("Position", employee.Position),
                new XAttribute("MonthlySalary", employee.MonthySalary)));
        }

        public void visit(Consultant consultant)
        {
            _root.Add(new XElement("Consultant",
                new XAttribute("Name", consultant.Name),
                new XAttribute("Company", consultant.Company),
                new XAttribute("MonthlyFee", consultant.MonthlyFee)));
        }

        public XDocument XmlDocument { get { return _xmlDocument; } }
    }


    public class YearlyCostVisitor : IWorkerVisitor
    {
        private decimal _yearlyCost;

        public void visit(Employee employee)
        {
            _yearlyCost += (employee.MonthySalary * 12);
        }

        public void visit(Consultant consultant)
        {
            _yearlyCost += (consultant.MonthlyFee * 12);
        }

        public decimal YearlyCost { get { return _yearlyCost; } }
    }
}
