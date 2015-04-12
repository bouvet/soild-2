using System;
using System.IO;

namespace test.Solution
{
    public class ErrorHandlingCsvReader : OpenClosedCsvReader
    {
        protected override void handle(FileInfo file, IOException exception)
        {
            Console.WriteLine("Could not read: " + file);
        }
    }
}
