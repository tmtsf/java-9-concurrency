import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.util.Date;

public class Main {
  public static void main(String[] args) {
    var queue = new DelayQueue<Event>();
    var threads = new Thread[5];
    for (int i = 0; i < threads.length; i++) {
      var task = new Task(i + 1, queue);
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

    do {
      int counter = 0;
      Event event;
      do {
        event = queue.poll();
        if (event != null)
          counter++;
      } while (event != null);

      System.out.printf("At %s you have read %d events\n", new Date(), counter);
      try {
        TimeUnit.MILLISECONDS.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } while (queue.size() > 0);
  }
}
