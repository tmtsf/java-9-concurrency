import java.util.concurrent.RecursiveAction;
import java.util.List;

public class Task extends RecursiveAction {
  private List<Product> products;
  private int first;
  private int last;
  private double increment;

  public Task(List<Product> products, int first, int last, double increment) {
    this.products = products;
    this.first = first;
    this.last = last;
    this.increment = increment;
  }

  @Override
  protected void compute() {
    if (last - first < 10) {
      updatePrices();
    } else {
      int mid = first + (last - first) / 2;
      System.out.printf("Task: Pending tasks: %s\n", getQueuedTaskCount());
      Task t1 = new Task(products, first, mid + 1, increment);
      Task t2 = new Task(products, mid + 1, last, increment);
      invokeAll(t1, t2);
    }
  }

  private void updatePrices() {
    for (int i = first; i < last; i++) {
      var product = products.get(i);
      product.setPrice(product.getPrice() * (1 + increment));
    }
  }
}
