using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace test.Solution
{
    public class OpenClosedCsvReader
    {
        public void parse(FileInfo file)
        {
            visitStart(file);
            try
            {
                using (var reader = file.OpenText())
                {
                    string line;
                    while ((line = reader.ReadLine()) != null)
                    {
                        string[] country = line.Split(',');
                        visitLine(format(country));
                    }
                }
            }
            catch(IOException e)
            {
                handle(file, e);
            }
            visitEnd(file);
        }

        protected virtual void visitStart(FileInfo file)
        {
            Console.WriteLine("Starting to parse: " + file);
        }

        protected virtual void visitEnd(FileInfo file)
        {
            Console.WriteLine("Done parsing: " + file);
        }

        protected virtual void visitLine(String formatted)
        {
            Console.WriteLine(formatted);
        }

        protected virtual string format(string[] country)
        {
            return "Country [code= " + country[4] + " , name=" + country[5] + "]";
        }

        protected virtual void handle(FileInfo file, IOException exception)
        {
            throw exception;
        }
    }
}
