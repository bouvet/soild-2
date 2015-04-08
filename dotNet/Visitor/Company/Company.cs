using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;

namespace Company
{
    public class Company
    {
        private readonly IList<Worker> _workers;

        public Company()
        {
            _workers = new List<Worker>();
        }

        public void AddWorker(Worker worker)
        {
            _workers.Add(worker);
        }

        public decimal YearlyWorkerCost
        {
            get
            {
                return _workers.Sum(worker => worker.YearlyCost);
            }
        }

        public string WorkerReport
        {
            get
            {
                StringBuilder builder = new StringBuilder();
                foreach (var worker in _workers)
                {
                    if (builder.Length > 0)
                        builder.Append(Environment.NewLine);
                    builder.Append(worker.Report);
                }
                return builder.ToString();
            }
        }
    }
}
