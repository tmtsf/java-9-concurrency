import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) {
    ConsoleClock clock = new ConsoleClock();
    Thread t = new Thread(clock);
    t.start();

    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    t.interrupt();
  }
}
