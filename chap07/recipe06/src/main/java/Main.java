import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {
  public static void main(String[] args) {
    var hash = new ConcurrentHashMap<String, ConcurrentLinkedDeque<Operation>>();
    var filler = new HashFiller(hash);

    var threads = new Thread[10];
    for (int i = 0; i < 10; i++) {
      threads[i] = new Thread(filler);
      threads[i].start();
    }

    for (var t : threads) {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.printf("Size: %d\n", hash.size());
    hash.forEach(10, (user, list) -> System.out.printf("%s: %s: %d\n",
                                                       Thread.currentThread().getName(),
                                                       user,
                                                       list.size()));
    hash.forEachEntry(10, entry -> System.out.printf("%s: %s: %d\n",
                                                     Thread.currentThread().getName(),
                                                     entry.getKey(),
                                                     entry.getValue().size()));

    var operation = hash.search(10, (user, list) -> {
                                for (var op : list) {
                                  if (op.getOperation().endsWith("1")) {
                                    return op;
                                  }
                                }
                                return null;
                             });
    System.out.printf("The operation we have found is: %s, %s, %s.\n",
                      operation.getUser(),
                      operation.getOperation(),
                      operation.getTime());

    var operations = hash.search(10, (user, list) -> {
                                        if (list.size() > 10) {
                                          return list;
                                        }
                                        return null;
                                     });
    System.out.printf("The user we have found is: %s: %d operations.\n",
                      operations.getFirst().getUser(),
                      operations.size());

    int totalSize = hash.reduce(10, (user, list) -> list.size(), (n1, n2) -> n1 + n2);
    System.out.printf("The total size is: %d\n", totalSize);
  }
}
