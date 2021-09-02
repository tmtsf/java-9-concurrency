public class Main {
  public static void main(String[] args) {
    MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
    Task task = new Task();

    Thread t;
    System.out.printf("Starting the Threads\n");
    for (int i = 0; i < 10; i++) {
      t = factory.newThread(task);
      t.start();
    }

    System.out.printf("Factory stats:\n");
    System.out.printf("%s\n", factory.getStats());
  }
}
