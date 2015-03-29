using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using delegation;
using System.IO;

namespace test
{
    [TestClass]
    public class CsvReaderTest
    {
        public static readonly string FILE_NAME = "resources\\GeoIPCountryWhois-cut.csv";

        private StringWriter outContent, errContent;
        private TextWriter originalOut, originalErr;

        [TestInitialize]
        public void Setup()
        {
            outContent = new StringWriter();
            errContent = new StringWriter();
            originalOut = Console.Out;
            Console.SetOut(outContent);
            originalErr = Console.Error;
            Console.SetError(errContent);
        }

        [TestCleanup]
        public void TearDown()
        {
            Console.SetOut(originalOut);
            Console.SetError(originalErr);
        }

        [TestMethod]
        public void testCsvReaderWorksAsExpected()
        {
            var csvReader = new CsvReader();
            csvReader.parse(FILE_NAME);
            Assert.AreEqual(40448, outContent.ToString().Length);
            Assert.AreEqual(0, errContent.ToString().Length);
        }

        [ExpectedException(typeof(FileNotFoundException))]
        [TestMethod]
        public void testCsvReaderThrowsException()
        {
            CsvReader csvReader = new CsvReader();
            csvReader.parse("foo");
        }
    }
}
