public class Main {
  public static void main(String[] args) {
    int numThreads = 2 * Runtime.getRuntime().availableProcessors();
    MyThreadGroup tg = new MyThreadGroup("MyThreadGroup");
    Task task =new Task();
    for (int i = 0; i < numThreads; i++) {
      Thread t = new Thread(tg, task);
      t.start();
    }

    System.out.printf("Number of Threads: %d\n", tg.activeCount());
    System.out.printf("Information about the Thread Group\n");
    tg.list();

    Thread[] threads = new Thread[tg.activeCount()];
    tg.enumerate(threads);
    for (int i = 0; i < tg.activeCount(); i++) {
      System.out.printf("Thread %s: %s\n", threads[i].getName(), threads[i].getState());
    }
  }
}
