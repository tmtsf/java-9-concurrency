import java.util.Random;

public class MatrixMock {
  private final int[][] data;

  public MatrixMock(int rows, int cols, int number) {
    int counter = 0;
    data = new int[rows][cols];
    var random = new Random();

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        data[i][j] = random.nextInt(10);
        if (data[i][j] == number) {
          counter++;
        }
      }
    }

    System.out.printf("Mock: There are %d ocurrences of %d in generated data.\n", counter, number);
  }

  public int[] getRow(int row) {
    if (row >= 0 && row < data.length) {
      return data[row];
    }

    return null;
  }
}
