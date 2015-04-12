using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace test.Solution
{
    public class DecorationCsvReader : OpenClosedCsvReader
    {
        protected override void visitEnd(FileInfo file)
        {
            /* do nothing */
        }

        protected override void visitStart(FileInfo file)
        {
            /* do nothing */
        }

        protected override string format(string[] country)
        {
            return country[0] + "-" + country[1] + ":" + country[4];
        }
    }
}
