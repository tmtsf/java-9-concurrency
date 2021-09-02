public class Main {
  public static void main(String[] args) {
    EventStorage storage = new EventStorage();

    Producer p = new Producer(storage);
    Thread t1 = new Thread(p);

    Consumer c = new Consumer(storage);
    Thread t2 = new Thread(c);

    t1.start();
    t2.start();
  }
}
