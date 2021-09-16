import java.util.concurrent.atomic.AtomicIntegerArray;

public class Main {
  public static void main(String[] args) {
    final int THREADS = 100;
    var vector = new AtomicIntegerArray(1_000);

    var incr = new Incrementer(vector);
    var decr = new Decrementer(vector);

    var incrs = new Thread[THREADS];
    var decrs = new Thread[THREADS];

    for (int i = 0; i < THREADS; i++) {
      incrs[i] = new Thread(incr);
      decrs[i] = new Thread(decr);

      incrs[i].start();
      decrs[i].start();
    }

    for (int i = 0; i < THREADS; i++) {
      try {
        incrs[i].join();
        decrs[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    int errors = 0;
    for (int i = 0; i < vector.length(); i++) {
      if (vector.get(i) != 0) {
        System.out.printf("Vector[" + i + "] : " + vector.get(i));
        errors++;
      }
    }

    if (errors == 0) {
      System.out.printf("No errors found.\n");
    }

    System.out.println("Main: End of the example");
  }
}
