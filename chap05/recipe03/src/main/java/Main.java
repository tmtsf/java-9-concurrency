import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) {
    var pool = new ForkJoinPool();
    var sys = new FolderProcessor("/sys", "log");
    var bin = new FolderProcessor("/bin", "log");
    var opt = new FolderProcessor("/opt", "log");

    pool.execute(sys);
    pool.execute(bin);
    pool.execute(opt);

    do {
      System.out.printf("**************************************\n");
      System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
      System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
      System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
      System.out.printf("**************************************\n");

      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } while (!sys.isDone() || !bin.isDone() || !opt.isDone());

    pool.shutdown();

    var results = sys.join();
    System.out.printf("System: %d files found.\n", results.size());

    results = bin.join();
    System.out.printf("System: %d files found.\n", results.size());

    results = opt.join();
    System.out.printf("System: %d files found.\n", results.size());
  }
}
