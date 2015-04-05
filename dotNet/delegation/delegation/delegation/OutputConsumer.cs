using System.IO;
namespace delegation.delegation
{
    public interface OutputConsumer
    {
        void consumeStart(FileInfo file);

        void consume(string line);

        void consumeEnd(FileInfo file);
    }
}
