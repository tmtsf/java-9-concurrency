public class Main {
  public static void main(String[] args) {
    PrintQueue q = new PrintQueue();

    Thread[] threads = new Thread[12];
    for (int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(new Job(q), "Thread " + i);
    }

    for (int i = 0; i < threads.length; i++) {
      threads[i].start();
    }
  }
}
