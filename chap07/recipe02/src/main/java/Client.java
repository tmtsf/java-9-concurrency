import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.Date;

public class Client implements Runnable {
  private final LinkedBlockingDeque<String> list;

  public Client(LinkedBlockingDeque<String> list) {
    this.list = list;
  }

  @Override
  public void run() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 5; j++) {
        var request = new StringBuilder();
        request.append(i);
        request.append(":");
        request.append(j);

        try {
          list.put(request.toString());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        System.out.printf("Client added: %s at %s.\n", request, new Date());
      }

      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.printf("Client: End.\n");
  }
}
