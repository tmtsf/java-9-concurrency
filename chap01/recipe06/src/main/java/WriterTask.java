import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.Date;

public class WriterTask implements Runnable {
  private Deque<Event> deque;
  public WriterTask(Deque<Event> deque) {
    this.deque = deque;
  }

  @Override
  public void run() {
    for (int i = 1; i < 100; i++) {
      Event e = new Event();
      e.setDate(new Date());
      e.setEvent(String.format("The thread %s has generated an event", Thread.currentThread().getId()));
      deque.addFirst(e);

      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
  }
}
