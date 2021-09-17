import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) {
    var vf = new VolatileFlag();
    var f = new Flag();

    var vt = new VolatileTask(vf);
    var t = new Task(f);

    var thread = new Thread(vt);
    thread.start();
    thread = new Thread(t);
    thread.start();

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.printf("Main: Going to stop volatile task: %s\n", new Date());
    vf.flag = false;
    System.out.printf("Main: Volatile task stopped: %s\n", new Date());

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.printf("Main: Going to stop volatile task: %s\n", new Date());
    f.flag = false;
    System.out.printf("Main: Volatile task stopped: %s\n", new Date());

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
