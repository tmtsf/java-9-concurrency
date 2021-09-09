import java.util.concurrent.CountedCompleter;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class FolderProcessor extends CountedCompleter<List<String>> {
  private String path;
  private String extension;
  private List<FolderProcessor> tasks;
  private List<String> results;

  protected FolderProcessor(CountedCompleter<?> completer, String path, String extension) {
    super(completer);
    this.path = path;
    this.extension = extension;
  }

  public FolderProcessor(String path, String extension) {
    this.path = path;
    this.extension = extension;
  }

  @Override
  public void compute() {
    results = new ArrayList<>();
    tasks = new ArrayList<>();

    var file = new File(path);
    var content = file.listFiles();
    if (content != null) {
      for (int i = 0; i < content.length; i++) {
        if (content[i].isDirectory()) {
          var task = new FolderProcessor(this, content[i].getAbsolutePath(), extension);
          task.fork();
          addToPendingCount(1);
          tasks.add(task);
        } else {
          if (checkFile(content[i].getName())) {
            results.add(content[i].getAbsolutePath());
          }
        }
      }

      if (tasks.size() > 50) {
        System.out.printf("%s: %d tasks ran.\n", file.getAbsolutePath(), tasks.size());
      }
    }

    tryComplete();
  }

  @Override
  public void onCompletion(CountedCompleter<?> completer) {
    for (var child : tasks) {
      results.addAll(child.getResults());
    }
  }

  private boolean checkFile(String name) {
    return name.endsWith(extension);
  }

  private List<String> getResults() {
    return results;
  }
}
