import java.util.concurrent.ConcurrentSkipListMap;

public class Task implements Runnable {
  private final ConcurrentSkipListMap<String, Contact> map;
  private final String id;

  public Task(ConcurrentSkipListMap<String, Contact> map, String id) {
    this.id = id;
    this.map = map;
  }

  @Override
  public void run() {
    for (int i = 0; i < 1_000; i++) {
      var contact = new Contact(id, String.valueOf(i + 1_000));
      map.put(id + contact.getPhone(), contact);
    }
  }
}
