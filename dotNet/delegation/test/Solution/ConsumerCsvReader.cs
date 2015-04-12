using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using test.a1_output;

namespace test.Solution
{
    public class ConsumerCsvReader : OpenClosedCsvReader
    {
        private readonly CsvConsumer csvConsumer;

        public ConsumerCsvReader(CsvConsumer csvConsumer)
        {
            this.csvConsumer = csvConsumer;
        }

        protected override void visitLine(string formatted)
        {
            csvConsumer.visitLine(formatted);
        }

        protected override void visitEnd(FileInfo file)
        {
            csvConsumer.visitEnd(file);
        }

        protected override void visitStart(FileInfo file)
        {
            csvConsumer.visitStart(file);
        }
    }
}
