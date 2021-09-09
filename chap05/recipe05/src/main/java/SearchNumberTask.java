import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class SearchNumberTask extends RecursiveTask<Integer> {
  private int[] numbers;
  private int start;
  private int end;
  private int number;
  private TaskManager manager;
  private final static int NOT_FOUND = -1;

  public SearchNumberTask(int[] numbers, int start, int end, int number, TaskManager manager) {
    this.numbers = numbers;
    this.start = start;
    this.end = end;
    this.number = number;
    this.manager = manager;
  }

  @Override
  protected Integer compute() {
    System.out.println("Task: " + start + ":" + end);
    int ret;
    if (end - start > 10) {
      ret = lanuchTasks();
    } else {
      ret = lookForNumber();
    }

    return ret;
  }

  private int lookForNumber() {
    for (int i = start; i < end; i++) {
      if (numbers[i] == number) {
        System.out.printf("Task: Number %d found in position %d\n", number, i);
        manager.cancelTasks(this);
        return i;
      }

      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    return NOT_FOUND;
  }

  private int lanuchTasks() {
    int mid = start + (end - start) / 2;
    var t1 = new SearchNumberTask(numbers, start, mid, number, manager);
    var t2 = new SearchNumberTask(numbers, mid, end, number, manager);

    manager.addTask(t1);
    manager.addTask(t2);

    t1.fork();
    t2.fork();

    int ret = t1.join();
    if (ret != NOT_FOUND) {
      return ret;
    }

    ret = t2.join();
    return ret;
  }

  public void logCancelMessage() {
    System.out.printf("Task: Canceled task from %d to %d\n", start, end);
  }
}
