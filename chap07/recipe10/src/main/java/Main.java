public class Main {
  public static void main(String[] args) {
    var account = new Account();
    var tInc = new Thread(new Incrementer(account));
    var dInc = new Thread(new Decrementer(account));

    tInc.start();
    dInc.start();

    try {
      tInc.join();
      dInc.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.printf("Safe amount: %f\n", account.amount);
    System.out.printf("Unsafe amount: %f\n", account.unsafeAmount);
  }
}
