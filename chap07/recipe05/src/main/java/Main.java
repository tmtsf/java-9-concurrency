import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Main {
  public static void main(String[] args) {
    var map = new ConcurrentSkipListMap<String, Contact>();
    var threads = new Thread[26];
    int counter = 0;
    for (char i = 'A'; i <= 'Z'; i++) {
      var task = new Task(map, String.valueOf(i));
      threads[counter] = new Thread(task);
      threads[counter].start();
      counter++;
    }

    for (var t : threads) {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.printf("Main: Size of the map: %d\n", map.size());
    var element = map.firstEntry();
    var contact = element.getValue();
    System.out.printf("Main: First Entry: %s: %s, %s\n", contact.getName(), contact.getPhone(), element.getKey());

    element = map.lastEntry();
    contact = element.getValue();
    System.out.printf("Main: Last Entry: %s: %s, %s\n", contact.getName(), contact.getPhone(), element.getKey());

    System.out.printf("Main: Submap from A1996 to B1002: \n");
    ConcurrentNavigableMap<String, Contact> submap = map.subMap("A1996", "B1002");
    do {
      element = submap.pollFirstEntry();
      if (element != null) {
        contact = element.getValue();
        System.out.printf("%s: %s\n", contact.getName(), contact.getPhone());
      }
    } while (element != null);
  }
}
