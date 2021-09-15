import java.util.concurrent.PriorityBlockingQueue;

public class Main {
  public static void main(String[] args) {
    var queue = new PriorityBlockingQueue<Event>();
    var threads = new Thread[5];
    for (int i = 0; i < threads.length; i++) {
      var task = new Task(i, queue);
      threads[i] = new Thread(task);
    }

    for (int i = 0; i < threads.length; i++) {
      threads[i].start();
    }

    for (int i = 0; i < threads.length; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.printf("Main: Queue Size: %d\n", queue.size());
    for (int i = 0; i < threads.length * 1_000; i++) {
      var event = queue.poll();
      System.out.printf("Thread %s: Priority %d\n", event.getThread(), event.getPriority());
    }

    System.out.printf("Main: Queue Size: %d\n", queue.size());
    System.out.printf("Mian: End of the program\n");
  }
}
