import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
    List<Task> taskList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Task task = new Task("Task-" + i);
      taskList.add(task);
    }

    List<Future<Result>> resultList = null;
    try {
      resultList = executor.invokeAll(taskList);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    executor.shutdown();

    System.out.println("Main: Printing the results");
    for (int i = 0; i < resultList.size(); i++) {
      Future<Result> future = resultList.get(i);
      try {
        Result result = future.get();
        System.out.println(result.getName() + ": " + result.getValue());
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }
  }
}
