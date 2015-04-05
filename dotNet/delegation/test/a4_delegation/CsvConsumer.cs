using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Collections.Generic;
using System.IO;

namespace test.a4_delegation
{
    class CsvConsumer
    {
        private readonly List<string> lines;

        private FileInfo startCalled, endCalled;

        public CsvConsumer()
        {
            this.lines = new List<string>();
        }

        public void visitStart(FileInfo file)
        {
            Assert.IsNull(startCalled, "visitStart was visited before");
            startCalled = file;
        }

        public void visitLine(string line)
        {
            lines.Add(line);
        }

        public void visitEnd(FileInfo file)
        {
            Assert.IsNull(endCalled, "visitEnd was visited before");
            endCalled = file;
        }

        public void assertState()
        {
            Assert.AreEqual(1000, lines.Count, "There were not 1000 lines visited as expected");
            Assert.AreEqual("1.0.0.0-1.0.0.255:AU", lines[0], "Sample line one is not matching");
            Assert.AreEqual("5.135.113.23-5.135.113.16:ES", lines[999], "Sample line 1000 is not matching");
            Assert.IsNotNull(startCalled, "visitStart was not called");
            Assert.IsNotNull(endCalled, "visitEnd was not called");
        }

        public void assertError(string value)
        {
            Assert.AreEqual(1, lines.Count, "There were not 1 line visited as expected");
            Assert.AreEqual("Could not read: " + value, lines[0], "Sample line one is not matching");
            Assert.IsNotNull(startCalled, "visitStart was not called");
            Assert.IsNotNull(endCalled, "visitEnd was not called");
        }

        public void reset()
        {
            startCalled = null;
            endCalled = null;
            lines.Clear();
        }
    }
}
