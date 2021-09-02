import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

public class Main {
  public static void main(String[] args) {
    System.out.printf("Mininum Priority: %s\n", Thread.MIN_PRIORITY);
    System.out.printf("Normal Priority: %s\n", Thread.NORM_PRIORITY);
    System.out.printf("Maximum Priority: %s\n", Thread.MAX_PRIORITY);

    Thread[] threads = new Thread[10];
    Thread.State[] status = new Thread.State[10];
    for (int i = 0; i < 10; ++i) {
      threads[i] = new Thread(new Calculator());
      threads[i].setName("My Thread " + i);
      if (i % 2 == 0) {
        threads[i].setPriority(Thread.MAX_PRIORITY);
      } else {
        threads[i].setPriority(Thread.MIN_PRIORITY);
      }
    }

    try (FileWriter file = new FileWriter("./data/log.txt");
         PrintWriter pw = new PrintWriter(file);) {
      for (int i = 0; i < 10; ++i) {
        pw.println("Status of Thread " + i + ": " + threads[i].getState());
        status[i] = threads[i].getState();
        threads[i].start();
      }

      boolean finish = false;
      while (!finish) {
        for (int i = 0; i < 10; i++) {
          if (threads[i].getState() != status[i]) {
            writeThreadInfo(pw, threads[i], status[i]);
            status[i] = threads[i].getState();
          }
        }

        finish = true;
        for (int i = 0; i < 10; i++) {
          finish = finish && (threads[i].getState() == State.TERMINATED);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private static void writeThreadInfo(PrintWriter pw,
                                      Thread thread,
                                      State state) {
    pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
    pw.printf("Main : Priority: %d\n", thread.getPriority());
    pw.printf("Main : Old State: %s\n", state);
    pw.printf("Main : New State: %s\n", thread.getState());
    pw.printf("Main : ************************************\n");
  }
}
