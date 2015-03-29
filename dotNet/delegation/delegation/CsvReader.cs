using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace delegation
{
    public class CsvReader
    {
        public void parse(string fileName)
        {
            Console.WriteLine("Starting to parse: " + fileName);
            using (var reader = new StreamReader(fileName))
            {
                string line;
                while ((line = reader.ReadLine()) != null)
                {
                    string[] country = line.Split(',');
                    Console.WriteLine("Country [code= " + country[4] + " , name=" + country[5] + "]");
               }
            }
            Console.WriteLine("Done parsing: " + fileName);
        }
    }
}
