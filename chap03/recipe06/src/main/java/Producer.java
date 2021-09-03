import java.util.concurrent.Exchanger;
import java.util.List;

public class Producer implements Runnable {
  private List<String> buffer;
  private final Exchanger<List<String>> exchanger;

  public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
    this.buffer = buffer;
    this.exchanger = exchanger;
  }

  @Override
  public void run() {
    for (int cycle = 0; cycle < 10; cycle++) {
      System.out.printf("Producer: Cycle %d\n", cycle);

      for (int j = 0; j < 10; j++) {
        String message = "Event " + (cycle * 10 + j);
        System.out.printf("Producer: %s\n", message);
        buffer.add(message);
      }

      try {
        buffer = exchanger.exchange(buffer);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println("Producer: " + buffer.size());
    }
  }
}
