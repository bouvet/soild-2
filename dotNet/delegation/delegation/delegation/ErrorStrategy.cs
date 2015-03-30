using System;

namespace delegation.delegation
{
    public interface ErrorStrategy
    {
        void handle(Exception e);
    }
}
