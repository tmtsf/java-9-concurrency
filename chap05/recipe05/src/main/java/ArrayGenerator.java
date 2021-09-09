import java.util.Random;

public class ArrayGenerator {
  public static int[] generateArray(int size) {
    var array = new int[size];
    var random = new Random();
    for (int i = 0; i < size; i++) {
      array[i] = random.nextInt(10);
    }

    return array;
  }
}
