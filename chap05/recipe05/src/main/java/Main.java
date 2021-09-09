import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) {
    var array = ArrayGenerator.generateArray(1_000);
    var manager = new TaskManager();
    var pool = new ForkJoinPool();
    var task = new SearchNumberTask(array, 0, array.length, 5, manager);

    pool.execute(task);
    pool.shutdown();
    try {
      pool.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.printf("Main: The program has finished.\n");
  }
}
