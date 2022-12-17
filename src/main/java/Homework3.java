import java.util.List;

public class Homework3 {
    public static List<Integer> Task1(List<Integer> listArray) {
        return listArray.stream().filter((a) -> a % 2 != 0).toList();
    }
}
