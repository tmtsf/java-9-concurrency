public class Main {
  public static void main(String[] args) {
    PricesInfo info = new PricesInfo();

    Reader[] readers = new Reader[5];
    Thread[] readerThreads = new Thread[5];
    for (int i = 0; i < 5; ++i) {
      readers[i] = new Reader(info);
      readerThreads[i] = new Thread(readers[i]);
    }

    Writer w = new Writer(info);
    Thread writerThread = new Thread(w);

    for (Thread t : readerThreads) {
      t.start();
    }

    writerThread.start();
  }
}
