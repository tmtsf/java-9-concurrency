import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.List;
import java.util.ArrayList;

public class DocumentTask extends RecursiveTask<Integer> {
  private String[][] document;
  private int start;
  private int end;
  private String word;

  public DocumentTask(String[][] document, int start, int end, String word) {
    this.document = document;
    this.start = start;
    this.end = end;
    this.word = word;
  }

  @Override
  protected Integer compute() {
    Integer result = null;
    if (end - start < 10) {
      result = processLines(document, start, end, word);
    } else {
      int mid = start + (end - start) / 2;
      var t1 = new DocumentTask(document, start, mid, word);
      var t2 = new DocumentTask(document, mid, end, word);
      invokeAll(t1, t2);

      try {
        result = t1.get() + t2.get();
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  private Integer processLines(String[][] document, int start, int end, String word) {
    List<LineTask> tasks = new ArrayList<>();
    for (int i = start; i < end; i++) {
      var task = new LineTask(document[i], 0, document[i].length, word);
      tasks.add(task);
    }

    invokeAll(tasks);
    int result = 0;
    for (int i = 0; i < tasks.size(); i++) {
      var task = tasks.get(i);
      try {
        result += task.get();
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }

    return result;
  }
}
