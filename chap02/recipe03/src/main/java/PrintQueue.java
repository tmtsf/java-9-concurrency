import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class PrintQueue {
  private Lock queueLock;

  public PrintQueue(boolean fairMode) {
    queueLock = new ReentrantLock(fairMode);
  }

  public void printJob(Object document) {
    queueLock.lock();
    try {
      long duration = (long)(Math.random() * 10000);
      System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during "
                            +  (duration / 1000) + " seconds");
      Thread.sleep(duration);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      queueLock.unlock();
    }

    queueLock.lock();
    try {
      long duration = (long)(Math.random() * 10000);
      System.out.println(Thread.currentThread().getName() + ": PrintQueue: Printing a Job during "
                            +  (duration / 1000) + " seconds");
      Thread.sleep(duration);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      queueLock.unlock();
    }
  }
}
