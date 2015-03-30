namespace delegation.delegation
{
    public interface OutputConsumer
    {
        void consumeStart(string file);

        void consume(string line);

        void consumeEnd(string file);
    }
}
