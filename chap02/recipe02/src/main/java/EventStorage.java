import java.util.Queue;
import java.util.Date;
import java.util.LinkedList;

public class EventStorage {
  private int maxSize;
  private Queue<Date> storage;

  public EventStorage() {
    maxSize = 10;
    storage = new LinkedList<>();
  }

  public synchronized void set() {
    while (storage.size() == maxSize) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    storage.offer(new Date());
    System.out.printf("Set: %d\n", storage.size());
    notify();
  }

  public synchronized void get() {
    while (storage.isEmpty()) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    String element = storage.poll().toString();
    System.out.printf("Get: %d: %s\n", storage.size(), element);
    notify();
  }
}
