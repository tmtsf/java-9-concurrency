import java.util.Date;

public class Main {
  public static void main(String[] args) {
    DataSourcesLoader dsLoader = new DataSourcesLoader();
    Thread t1 = new Thread(dsLoader, "DataSourceThread");

    NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
    Thread t2 = new Thread(ncLoader, "NetworkConnectionThread");

    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.printf("Main: Configuration has been loaded: %s\n", new Date());
  }
}
