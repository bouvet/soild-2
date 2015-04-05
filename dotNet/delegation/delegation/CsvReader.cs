using System;
using System.IO;

namespace delegation
{
    public class CsvReader
    {
        public void parse(FileInfo file)
        {
            Console.WriteLine("Starting to parse: " + file);
            using (var reader = file.OpenText())
            {
                string line;
                while ((line = reader.ReadLine()) != null)
                {
                    string[] country = line.Split(',');
                    Console.WriteLine("Country [code= " + country[4] + " , name=" + country[5] + "]");
               }
            }
            Console.WriteLine("Done parsing: " + file);
        }
    }
}
