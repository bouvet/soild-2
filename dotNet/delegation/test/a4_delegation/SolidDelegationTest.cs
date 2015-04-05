using delegation;
using delegation.delegation;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.IO;

namespace test.a4_delegation
{
    [TestClass]
    public class SolidDelegationTest
    {
        /**
         * For this exercise, you are to create a subclass of the CsvReader that allows
         * the delegation of
         *
         * 1. Create a subclass of the refactored CsvReader which allows the delegation of
         *    the responsibility to individual tasks. The delegation interfaces are given in
         *    the delegation package.
         * 2. The output must be redirected to the given CsvConsumer.
         * 3. The output must read like in 'a2'.
         * 4. The exception strategy must be like in 'a3'.
         */
        [TestMethod]
        public void testSolidOutput()
        {
            CsvConsumer consumer = new CsvConsumer();
            NamingStrategy namingStrategy = null;
            ErrorStrategy errorStrategy = null;
            OutputConsumer outputConsumer = null;
            CsvReader csvReader = null;

            // You are only allowed to refactor CsvReader and you can insert code here.
            // No other test must break by your changes, the original API of CsvReader
            // must stay binary compatible!

            csvReader.parse(new FileInfo(CsvReaderTest.FILE_NAME));
            consumer.assertState();

            consumer.reset();
            csvReader.parse(new FileInfo("foo"));
            consumer.assertError("foo");
        }
    }
}
