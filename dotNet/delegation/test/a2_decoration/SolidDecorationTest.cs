using delegation;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using System;
using System.IO;

namespace test.a2_delegation
{
    [TestClass]
    public class SolidDecorationTest
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
         * For this exercise, you are to refactor the CsvReader in order to allow for
         * writing a custom output to the console.
         *
         * 1. Refactor CsvReader without breaking the API of this class for other users.
         * 2. Create a subclass of the refactored CsvReader which writes each entry as
         *    [from IP]-[to IP]:[country code]
         *    to the standard output. No information about the read file should be
         *    written to the console for this output format.

         */
        [TestMethod]
        public void testCsvReaderDoesNotThrowException()
        {
            CsvReader csvReader = null;

            // You are only allowed to refactor CsvReader and you can insert code here.
            // No other test must break by your changes and you must not change tests
            // that you implemented before. The original API of CsvReader must stay
            // binary compatible!

            csvReader.parse(new FileInfo(CsvReaderTest.FILE_NAME));

            mockOut.Verify(x => x.WriteLine("\"1.0.0.0\"-\"1.0.0.255\":\"AU\""));
            mockOut.Verify(x => x.WriteLine(It.IsAny<string>()), Times.Exactly(1000));
        }
    }
}
