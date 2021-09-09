import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class Task extends RecursiveTask<Integer> {
  private int[] array;
  private int start;
  private int end;

  public Task(int[] array, int start, int end) {
    this.array = array;
    this.start = start;
    this.end = end;
  }

  @Override
  protected Integer compute() {
    System.out.printf("Task: Start from %d to %d\n", start, end);
    if (end - start < 10) {
      if (start < 3 && end > 3) {
        throw new RuntimeException("This task throws and Excetion: Task from " + start + " to " + end);
      }

      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } else {
      int mid = start + (end - start) / 2;
      var t1 = new Task(array, start, mid);
      var t2 = new Task(array, mid, end);
      invokeAll(t1, t2);
      System.out.printf("Task: Result from %d to %d: %d\n", start, mid, t1.join());
      System.out.printf("Task: Result from %d to %d: %d\n", mid, end, t2.join());
    }

    System.out.printf("Task: End from %d to %d\n", start, end);
    return 0;
  }
}
