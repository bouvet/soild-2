namespace Company
{
    public abstract class Worker
    {
        public Worker(string name)
        {
            Name = name;
        }

        public string Name { get; private set; }
        public abstract string Report { get; }
        public abstract decimal YearlyCost { get; }
    }

    public class Employee : Worker
    {
        public Employee(string name, string position, decimal monthlySalary)
            : base(name)
        {
            Position = position;
            MonthySalary = monthlySalary;
        }

        public string Position { get; private set; }
        public decimal MonthySalary { get; private set; }

        public override string Report
        {
            get
            {
                return string.Format("Employee {0} works as {1} and earns {2} per month.", Name, Position, MonthySalary);
            }
        }

        public override decimal YearlyCost
        {
            get { return MonthySalary * 12; }
        }
    }

    public class Consultant : Worker
    {
        public Consultant(string name, string company, decimal monthlyFee)
            : base(name)
        {
            Company = company;
            MonthlyFee = monthlyFee;
        }

        public string Company { get; private set; }
        public decimal MonthlyFee { get; private set; }

        public override string Report
        {
            get
            {
                return string.Format("Consultant {0} from {1} costs {2} per month.", Name, Company, MonthlyFee);
            }
        }

        public override decimal YearlyCost
        {
            get { return MonthlyFee * 12; }
        }
    }
}
