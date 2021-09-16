import java.util.concurrent.DelayQueue;
import java.util.Date;

public class Task implements Runnable {
  private final int id;
  private final DelayQueue<Event> queue;

  public Task(int id, DelayQueue<Event> queue) {
    this.id = id;
    this.queue = queue;
  }

  @Override
  public void run() {
    var now = new Date();
    var delay = new Date();
    delay.setTime(now.getTime() + id * 1_000);
    System.out.printf("Thread %s: %s\n", id, delay);

    for (int i = 0; i < 100; i++) {
      var event = new Event(delay);
      queue.add(event);
    }
  }
}
