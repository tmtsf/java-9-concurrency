import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    List<String> buffer1 = new ArrayList<>();
    List<String> buffer2 = new ArrayList<>();
    Exchanger<List<String>> exchanger = new Exchanger<>();

    Producer p = new Producer(buffer1, exchanger);
    Consumer c = new Consumer(buffer2, exchanger);

    Thread producerThread = new Thread(p);
    Thread consumerThread = new Thread(c);

    producerThread.start();
    consumerThread.start();
  }
}
