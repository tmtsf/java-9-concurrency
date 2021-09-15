import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.Date;

public class Main {
  public static void main(String[] args) {
    var list = new LinkedBlockingDeque<String>(3);

    var client = new Client(list);
    var thread = new Thread(client);
    thread.start();

    try {
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 3; j++) {
          var request = list.take();
          System.out.printf("Main: Removed: %s at %s. Size: %d\n", request, new Date(), list.size());
        }

        TimeUnit.MILLISECONDS.sleep(300);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.printf("Main: End of the program.\n");
  }
}
