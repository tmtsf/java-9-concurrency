import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) {
    var executor = Executors.newCachedThreadPool();
    var tasks = new ResultTask[5];
    for (int i = 0; i < 5; i++) {
      var e = new ExecutableTask("Task " + i);
      tasks[i] = new ResultTask(e);
      executor.submit(tasks[i]);
    }

    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    for (int i = 0; i < 5; i++) {
      tasks[i].cancel(true);
    }

    for (int i = 0; i < 5; i++) {
      try {
        if (!tasks[i].isCancelled()) {
          System.out.printf("%s\n", tasks[i].get());
        }
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }

    executor.shutdown();
  }
}
