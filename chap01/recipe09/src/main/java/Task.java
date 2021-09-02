import java.util.Random;

public class Task implements Runnable {
  @Override
  public void run() {
    Random random = new Random(Thread.currentThread().getId());
    while (true) {
      int result = 1000 / ((int)(random.nextDouble() * 1000000000));
      if (Thread.currentThread().isInterrupted()) {
        System.out.printf("%d : Interrupted\n", Thread.currentThread().getId());
        return;
      }
    }
  }
 }
