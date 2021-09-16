import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.Random;
import java.util.Date;

public class HashFiller implements Runnable {
  private ConcurrentHashMap<String, ConcurrentLinkedDeque<Operation>> hash;

  public HashFiller(ConcurrentHashMap<String, ConcurrentLinkedDeque<Operation>> hash) {
    this.hash = hash;
  }

  @Override
  public void run() {
    var r = new Random();
    for (int i = 0; i < 100; i++) {
      var operation = new Operation();
      String user = "USER" + r.nextInt(100);
      operation.setUser(user);
      String action = "OP" + r.nextInt(10);
      operation.setOperation(action);
      operation.setTime(new Date());

      addOperationToHash(hash, operation);
    }
  }

  private void addOperationToHash(ConcurrentHashMap<String, ConcurrentLinkedDeque<Operation>> hash,
                                  Operation operation) {
    var list = hash.computeIfAbsent(operation.getUser(), user -> new ConcurrentLinkedDeque<Operation>());
    list.add(operation);
  }
}
