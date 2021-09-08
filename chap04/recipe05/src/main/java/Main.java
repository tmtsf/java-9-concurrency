import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    System.out.printf("Main: Starting at: %s\n", new Date());
    List<Future<String>> resultList = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      Task task = new Task("Task " + i);
      Future<String> future = executor.schedule(task, i + 1, TimeUnit.SECONDS);
      resultList.add(future);
    }

    executor.shutdown();
    try {
      executor.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    for (int i = 0; i < resultList.size(); ++i) {
      try {
        System.out.printf("Main: Result of Task %d: %s\n", i, resultList.get(i).get());
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }

    System.out.printf("Main: Ends at: %s\n", new Date());
  }
}
