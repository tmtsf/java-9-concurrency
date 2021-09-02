import java.util.Date;

public class Reader implements Runnable {
  private PricesInfo info;

  public Reader(PricesInfo info) {
    this.info = info;
  }

  @Override
  public void run() {
    for (int i = 0; i < 20; ++i) {
      System.out.printf("%s: %s: Price 1: %f\n", new Date(), Thread.currentThread().getName(), info.getPrice1());
      System.out.printf("%s: %s: Price 2: %f\n", new Date(), Thread.currentThread().getName(), info.getPrice2());
    }
  }
}
