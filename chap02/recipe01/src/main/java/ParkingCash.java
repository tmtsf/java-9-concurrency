public class ParkingCash {
  private static final int cost = 2;
  private long cash;

  public ParkingCash() {
    cash = 0;
  }

  public synchronized void vehiclePay() {
    cash += cost;
  }

  public void close() {
    System.out.printf("Closing accounting\n");
    System.out.printf("The total amount is : %d\n", cash);
    cash = 0;
  }
}
