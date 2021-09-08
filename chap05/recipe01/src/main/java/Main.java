import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) {
    var gen = new ProductListGenerator();
    var products = gen.generate(10_000);
    var task = new Task(products, 0, products.size(), .2);
    var pool = new ForkJoinPool();
    pool.execute(task);

    do {
      System.out.printf("Main: Thread Count: %d\n", pool.getActiveThreadCount());
      System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());
      System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());

      try {
        TimeUnit.MILLISECONDS.sleep(5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } while (!task.isDone());

    pool.shutdown();
    if (task.isCompletedNormally()) {
      System.out.printf("Main: The process has completed normally.\n");
    }

    for (int i = 0; i < products.size(); i++) {
      var p = products.get(i);
      if (p.getPrice() != 12) {
        System.out.printf("Product %s: %f\n", p.getName(), p.getPrice());
      }
    }

    System.out.println("Main: End of the program.");
  }
}
