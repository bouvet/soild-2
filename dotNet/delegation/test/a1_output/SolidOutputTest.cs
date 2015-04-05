using delegation;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.IO;

namespace test.a1_output
{
    [TestClass]
    public class SolidOutputTest
    {
        /**
         * For this exercise, you are to refactor the CsvReader in order to allow for
         * consuming the output in an alternative manner. For this exercise, you should:
         *
         * 1. Refactor CsvReader without breaking the API of this class for other users.
         * 2. Create a subclass of the refactored CsvReader which directs the output
         *    to the CsvConsumer below.
         */
        [TestMethod]
        public void testSolidOutput()
        {
            CsvConsumer consumer = new CsvConsumer();
            CsvReader csvReader = null;

            // You are only allowed to refactor CsvReader and you can insert code here.
            // No other test must break by your changes, the original API of CsvReader
            // must stay binary compatible!

            csvReader.parse(new FileInfo(CsvReaderTest.FILE_NAME));
            consumer.assertState();
        }
    }
}
