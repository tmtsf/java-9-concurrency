public class Main {
  public static void main(String[] args) {
   System.out.printf("Running example with fair mode = false\n");
   testPrintQueue(false);

   System.out.printf("Running example with fair mode = true\n");
   testPrintQueue(true);
  }

  private static void testPrintQueue(boolean fair) {
    PrintQueue q = new PrintQueue(fair);

    Thread[] threads = new Thread[10];
    for (int i = 0; i < 10; ++i) {
      threads[i] = new Thread(new Job(q), "Thread " + i);
      threads[i].start();

      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
