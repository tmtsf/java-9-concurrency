import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.Deque;

public class Main {
  public static void main(String[] args) {
    Deque<Event> deque = new ConcurrentLinkedDeque<>();
    WriterTask writer = new WriterTask(deque);

    System.out.printf("Main: %d threads are available\n", Runtime.getRuntime().availableProcessors());
    for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
      Thread t = new Thread(writer);
      t.start();
    }

    CleanerTask cleaner = new CleanerTask(deque);
    cleaner.start();
  }
}
