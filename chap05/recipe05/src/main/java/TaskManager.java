import java.util.concurrent.ConcurrentLinkedDeque;

public class TaskManager {
  private final ConcurrentLinkedDeque<SearchNumberTask> tasks;

  public TaskManager() {
    tasks = new ConcurrentLinkedDeque<>();
  }

  public void addTask(SearchNumberTask task) {
    tasks.add(task);
  }

  public void cancelTasks(SearchNumberTask cancelTask) {
    for (var t : tasks) {
      if (t != cancelTask) {
        t.cancel(true);
        t.logCancelMessage();
      }
    }
  }
}
