public class Main {
  public static void main(String[] args) {
    FileMock mock = new FileMock(100, 10);
    Buffer buffer = new Buffer(20);

    Producer producer = new Producer(mock, buffer);
    Thread pThread = new Thread(producer, "Producer");

    Consumer[] consumers = new Consumer[3];
    Thread[] cThreads = new Thread[3];

    for (int i = 0; i < 3; i++) {
      consumers[i] = new Consumer(buffer);
      cThreads[i] = new Thread(consumers[i], "Consumer " + i);
    }

    pThread.start();
    for (int i = 0; i < 3; i++) {
      cThreads[i].start();
    }
  }
}
