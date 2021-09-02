import java.util.concurrent.CyclicBarrier;

public class Main {
  public static void main(String[] args) {
    final int ROWS = 10000;
    final int COLS = 1000;
    final int SEARCH = 5;
    final int PARTICIPANTS = 5;
    final int LINES_PARTICIPANTS = 2000;

    var mock = new MatrixMock(ROWS, COLS, SEARCH);
    var results = new Results(ROWS);
    var grouper = new Grouper(results);
    var barrier = new CyclicBarrier(PARTICIPANTS, grouper);
    var searchers = new Searcher[PARTICIPANTS];
    for (int i = 0; i < PARTICIPANTS; i++) {
      searchers[i] = new Searcher(i * LINES_PARTICIPANTS,
                                  (i + 1) * LINES_PARTICIPANTS,
                                  mock,
                                  results,
                                  SEARCH,
                                  barrier);
      var t = new Thread(searchers[i]);
      t.start();
    }

    System.out.printf("Main: The main thread has finished.\n");
  }
}
