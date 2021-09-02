import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) {
    FileSearch searcher = new FileSearch("/", "javac");
    Thread t = new Thread(searcher);
    t.start();

    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    t.interrupt();
  }
}
