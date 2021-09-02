import java.util.concurrent.Phaser;

public class Main {
  public static void main(String[] args) {
    var phaser = new Phaser(3);

    var homeDir = System.getProperty("user.home");
    var chap01 = new FileSearch(homeDir + "/Documents/Program/java-9-concurrency/chap01", "java", phaser);
    var t1 = new Thread(chap01, "Chapter-01");
    t1.start();

    var chap02 = new FileSearch(homeDir + "/Documents/Program/java-9-concurrency/chap02", "java", phaser);
    var t2 = new Thread(chap02, "Chapter-02");
    t2.start();

    var chap03 = new FileSearch(homeDir + "/Documents/Program/java-9-concurrency/chap03", "java", phaser);
    var t3 = new Thread(chap03, "Chapter-03");
    t3.start();

    try {
      t1.join();
      t2.join();
      t3.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Terminated: " + phaser.isTerminated());
  }
}
