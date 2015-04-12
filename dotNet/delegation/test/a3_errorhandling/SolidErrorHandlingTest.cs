using delegation;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using System;
using System.IO;

namespace test.a3_errorhandling
{
    [TestClass]
    public class SolidErrorHandlingTest
    {
        private Mock<TextWriter> mockOut, mockErr;
        private TextWriter originalOut, originalErr;

        [TestInitialize]
        public void setUp()
        {
            mockOut = new Mock<TextWriter>();
            mockErr = new Mock<TextWriter>(MockBehavior.Strict);
            mockErr.Setup(x => x.FormatProvider).Returns<IFormatProvider>(null);
            originalOut = Console.Out;
            Console.SetOut(mockOut.Object);
            originalErr = Console.Error;
            Console.SetError(mockErr.Object);
        }

        [TestCleanup]
        public void tearDown()
        {
            Console.SetOut(originalOut);
            Console.SetError(originalErr);
        }

        /**
         * For this exercise, you are to refactor the CsvReader in order to better handle errors.
         *
         * 1. Refactor CsvReader without breaking the API of this class for other users.
         * 2. When an IOException occurs, an error message should be written to the console.
         *    This message should be: 'Could not read: [file name]
         *    Remember that old users of the class should still receive an IOException
         *    when an error occurs.
         */
        [TestMethod]
        public void testCsvReaderDoesNotThrowException()
        {
            CsvReader csvReader = null;

            // You are only allowed to refactor CsvReader and you can insert code here.
            // No other test must break by your changes and you must not change tests
            // that you implemented before. The original API of CsvReader must stay
            // binary compatible!

            csvReader.parse(new FileInfo("foo"));

            mockOut.Verify(x => x.WriteLine("Starting to parse: foo"));
            mockOut.Verify(x => x.WriteLine("Could not read: foo"));
        }
    }
}
