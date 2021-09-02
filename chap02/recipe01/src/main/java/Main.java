public class Main {
  public static void main(String[] args) {
    ParkingCash cash = new ParkingCash();
    ParkingStats stats = new ParkingStats(cash);

    System.out.printf("Parking Simulator\n");
    int numSensors = 2 * Runtime.getRuntime().availableProcessors();
    Thread[] threads = new Thread[numSensors];
    for (int i = 0; i < numSensors; ++i) {
      Sensor sensor = new Sensor(stats);
      Thread thread = new Thread(sensor);
      thread.start();
      threads[i] = thread;
    }

    for (int i = 0; i < numSensors; ++i) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.printf("Number of cars: %d\n", stats.numberOfCars());
    System.out.printf("Number of motors: %d\n", stats.numberOfMotors());
    cash.close();
  }
}
