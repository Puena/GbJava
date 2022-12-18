import java.util.Arrays;
import java.util.List;

public class Homework3 {
    public static int[] Task1(int[] array) {
        return mergeSort(array);
    }
    public static List<Integer> task2(List<Integer> listArray) {
        return listArray.stream().filter((a) -> a % 2 != 0).toList();
    }

    public static void task3(List<Integer> list) {
        System.out.printf("Minimum value: %d\n", list.stream().min(Integer::compare).get());
        System.out.printf("Maximum value: %d\n", list.stream().max(Integer::compare).get());
        System.out.printf("Average value: %.2f", list.stream().mapToDouble(i -> i).average().getAsDouble());
    }

    private static int[] mergeSort(int[] array) {
        if (array.length != 1 && array.length != 0) {
            int[] right = mergeSort(Arrays.copyOf(array, array.length / 2));
            int[] left = mergeSort(Arrays.copyOfRange(array, array.length / 2, array.length));
            int n = 0;
            int m = 0;
            int k = 0;
            int[] newArr = new int[right.length + left.length];

            while(n < right.length && m < left.length) {
                if (right[n] <= left[m]) {
                    newArr[k++] = right[n++];
                } else {
                    newArr[k++] = left[m++];
                }
            }

            while(n < right.length) {
                newArr[k++] = right[n++];
            }

            while(m < left.length) {
                newArr[k++] = left[m++];
            }

            return newArr;
        } else {
            return array;
        }
    }
}
