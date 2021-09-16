public class Main {
  public static void main(String[] args) {
    var account = new Account();
    account.setBalance(1_000);

    var company = new Company(account);
    var c = new Thread(company);

    var bank = new Bank(account);
    var b = new Thread(bank);

    System.out.printf("Account: Initial Balance: %d\n", account.getBalance());
    c.start();
    b.start();

    try {
      c.join();
      b.join();
      System.out.printf("Account: Final Balance: %d\n", account.getBalance());
      System.out.printf("Account: Number of Operations: %d\n", account.getOperations());
      System.out.printf("Account: Accumulated commisions: %f\n", account.getCommission());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
