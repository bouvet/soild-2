using System;
using System.IO;

namespace delegation.delegation
{
    public interface ErrorStrategy
    {
        void handle(FileInfo file, Exception e);
    }
}
