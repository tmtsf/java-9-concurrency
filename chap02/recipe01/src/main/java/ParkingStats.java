public class ParkingStats {
  private long numCars;
  private long numMotors;
  private ParkingCash cash;
  private final Object carsCtrl;
  private final Object motorsCtrl;

  public ParkingStats(ParkingCash cash) {
    numCars = 0;
    numMotors = 0;
    this.cash = cash;
    carsCtrl = new Object();
    motorsCtrl = new Object();
  }

  public void carComeIn() {
    synchronized (carsCtrl) {
      numCars++;
    }
  }

  public void carGoOut() {
    synchronized (carsCtrl) {
      numCars--;
    }
    cash.vehiclePay();
  }

  public void motorComeIn() {
    synchronized (motorsCtrl) {
      numMotors++;
    }
  }

  public void motorGoOut() {
    synchronized (motorsCtrl) {
      numMotors--;
    }
    cash.vehiclePay();
  }

  public long numberOfCars() {
    synchronized (carsCtrl) {
      return numCars;
    }
  }

  public long numberOfMotors() {
    synchronized (motorsCtrl) {
      return numMotors;
    }
  }
}
