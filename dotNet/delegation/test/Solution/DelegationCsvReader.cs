using delegation.delegation;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using test.a4_delegation;

namespace test.Solution
{
    public class DelegationCsvReader : OpenClosedCsvReader
    {
        private readonly OutputConsumer outputConsumer;

        private readonly NamingStrategy namingStrategy;

        private readonly ErrorStrategy errorStrategy;

        public DelegationCsvReader(
            OutputConsumer outputConsumer,
            NamingStrategy namingStrategy,
            ErrorStrategy errorStrategy)
        {
            this.outputConsumer = outputConsumer;
            this.namingStrategy = namingStrategy;
            this.errorStrategy = errorStrategy;
        }

        protected override void visitStart(FileInfo file)
        {
            outputConsumer.consumeStart(file);
        }

        protected override void visitEnd(FileInfo file)
        {
            outputConsumer.consumeEnd(file);
        }

        protected override void visitLine(string formatted)
        {
            outputConsumer.consume(formatted);
        }

        protected override string format(string[] country)
        {
            return namingStrategy.format(new Entry(
                country[0],
                country[1],
                country[2],
                country[3],
                country[4],
                country[5]));
        }

        protected override void handle(FileInfo file, IOException exception)
        {
            errorStrategy.handle(file, exception);
        }
    }

    public class ConsumerDelegation : OutputConsumer
    {
        private readonly CsvConsumer csvConsumer;

        public ConsumerDelegation(CsvConsumer csvConsumer)
        {
            this.csvConsumer = csvConsumer;
        }

        public void consumeStart(FileInfo file)
        {
            csvConsumer.visitStart(file);
        }

        public void consume(string line)
        {
            csvConsumer.visitLine(line);
        }

        public void consumeEnd(FileInfo file)
        {
            csvConsumer.visitEnd(file);
        }
    }

    public class FormatDelegation : NamingStrategy
    {
        public string format(Entry entry)
        {
            return entry.getFromIp() + "-" + entry.getToIp() + ":" + entry.getCountryCode();
        }
    }

    public class ErrorDelegation : ErrorStrategy
    {
        private readonly CsvConsumer csvConsumer;

        public ErrorDelegation(CsvConsumer csvConsumer)
        {
            this.csvConsumer = csvConsumer;
        }

        public void handle(FileInfo file, Exception e)
        {
            csvConsumer.visitLine("Could not read: " + file);
        }
    }

}
