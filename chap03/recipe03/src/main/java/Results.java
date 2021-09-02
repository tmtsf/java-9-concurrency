public class Results {
  private final int[] data;

  public Results(int size) {
    data = new int[size];
  }

  public void setData(int pos, int val) {
    data[pos] = val;
  }

  public int[] getData() {
    return data;
  }
}
