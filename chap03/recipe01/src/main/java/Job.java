public class Job implements Runnable {
  private PrintQueue q;

  public Job(PrintQueue q) {
    this.q = q;
  }

  @Override
  public void run() {
    System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());
    q.printJob(new Object());
    System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
  }
}
