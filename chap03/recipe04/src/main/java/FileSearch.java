import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class FileSearch implements Runnable {
  private final String initPath;
  private final String fileExtension;
  private List<String> results;
  private Phaser phaser;

  public FileSearch(String initPath, String fileExtension, Phaser phaser) {
    this.initPath = initPath;
    this.fileExtension = fileExtension;
    this.phaser = phaser;
    results = new ArrayList<>();
  }

  private void directoryProcess(File file) {
    var list = file.listFiles();
    if (list != null) {
      for (var l : list) {
        if (l.isDirectory()) {
          directoryProcess(l);
        } else {
          fileProcess(l);
        }
      }
    }
  }

  private void fileProcess(File file) {
    if (file.getName().endsWith(fileExtension)) {
      results.add(file.getAbsolutePath());
    }
  }

  private void filterResults() {
    List<String> newResults = new ArrayList<>();
    long actualDate = new Date().getTime();
    for (var path : results) {
      var file = new File(path);
      long fileDate = file.lastModified();
      if (actualDate - fileDate < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
        newResults.add(path);
      }
    }

    results = newResults;
  }

  private boolean checkResults() {
    if (results.isEmpty()) {
      System.out.printf("%s: Phase %d: 0 results.\n", Thread.currentThread().getName(), phaser.getPhase());
      System.out.printf("%s: Phase %d: End.\n", Thread.currentThread().getName(), phaser.getPhase());
      phaser.arriveAndDeregister();
      return false;
    } else {
      System.out.printf("%s: Phase %d: %d results.\n",
                        Thread.currentThread().getName(),
                        phaser.getPhase(),
                        results.size());
      phaser.arriveAndAwaitAdvance();
      return true;
    }
  }

  private void showInfo() {
    for (var path : results) {
      var file = new File(path);
      System.out.printf("%s: %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
    }
    phaser.arriveAndAwaitAdvance();
  }

  @Override
  public void run() {
    phaser.arriveAndAwaitAdvance();
    System.out.printf("%s: Starting.\n", Thread.currentThread().getName());

    var file = new File(initPath);
    if (file.isDirectory()) {
      directoryProcess(file);
    }

    if (!checkResults()) {
      return;
    }

    filterResults();
    if (!checkResults()) {
      return;
    }

    showInfo();
    phaser.arriveAndDeregister();
    System.out.printf("%s: Work completed.\n", Thread.currentThread().getName());
  }
}
