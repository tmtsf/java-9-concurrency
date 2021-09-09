import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) {
    var array = new int[100];
    var t = new Task(array, 0, 100);
    var pool = new ForkJoinPool();
    pool.execute(t);
    pool.shutdown();

    try {
      pool.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    if (t.isCompletedAbnormally()) {
      System.out.printf("Main: An exception has occurred\n");
      System.out.printf("Main: %s\n", t.getException());
    }

    System.out.printf("Main: Result: %d\n", t.join());
  }
}
