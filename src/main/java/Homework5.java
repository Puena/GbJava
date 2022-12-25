import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Homework5 {
    public static void task1(){
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addPhone("Иван", "+79619841752");
        phoneBook.addPhone("Данил", "+79813225313");
        phoneBook.addPhone("Анатолий", "+79212325990");
        phoneBook.addPhone("Алла", "+79030932002");
        System.out.println("Поиск по имени");
        System.out.println(phoneBook.getPhone("Иван"));
        System.out.println("Все телефоны");
        phoneBook.showAll();
    }

    public static void task2(){
        List<String> employees = new ArrayList<>();
        employees.add("Иван Иванов");
        employees.add("Светлана Петрова");
        employees.add("Кристина Белова");
        employees.add("Анна Мусина");
        employees.add("Анна Крутова");
        employees.add("Иван Юрин");
        employees.add("Петр Лыков");
        employees.add("Павел Чернов");
        employees.add("Петр Чернышов");
        employees.add("Мария Федорова");
        employees.add("Марина Светлова");
        employees.add("Мария Савина");
        employees.add("Мария Рыкова");
        employees.add("Марина Лугова");
        employees.add("Анна Владимирова");
        employees.add("Иван Мечников");
        employees.add("Петр Петин");
        employees.add("Иван Ежов");
        employees.stream().map(name -> name.split("\\s")[0])
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(System.out::println);
    }

    public static void task3(){
        Random random = new Random();
        int n = random.nextInt(5,15);
        int[] array = IntStream.range(-100, 101).toArray();
        System.out.println("Создать массив случайных значений");
        System.out.println(Arrays.toString(array));

        System.out.println("Выполнить сортировку");
        array = HeapSort.sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void task4(){
        ChessBoard chessBoard = new ChessBoard(8);
        while(!chessBoard.checkQueensOnBoard(8)){
            if(!chessBoard.queenRandomPut())
                chessBoard.clearChessBoard();
        }
        System.out.println("Поставить ферзя в случайное место");
        System.out.println(chessBoard);
    }

    public static class PhoneBook{
        Map<String, ArrayList<String>> data;

        public PhoneBook() {
            data = new HashMap<>();
        }
        public void addPhone(String name, String number){
            if (data.containsKey(name))
                data.get(name).add(number);
            else
                data.put(name, new ArrayList<>(List.of(number)));
        }
        public ArrayList<String> getPhone(String name){
            return data.get(name);
        }
        public void showAll(){
            for(var item: data.entrySet()){
                System.out.println(item.getKey() + " : " + item.getValue());
            }
        }
    }

    public static class HeapSort{
        public static int[] sort(int[] arr){
            int arrayLength = arr.length - 1;
            for (int i = (arrayLength-1)/2; i >= 0; i --){
                rootSort(arr, i, arrayLength);
            }
            for (int i = arrayLength; i > 0; i--){
                swapElement(arr, 0, i);
                rootSort(arr, 0, i-1);
            }
            return arr;
        }
        private static void rootSort(int[] arr, int root, int max){
            if (root == max) return;
            int left = root * 2 + 1;
            int right = (root * 2 + 2)>max? left: root * 2 + 2;
            int next;
            if (arr[root] < arr[left] || arr[root] < arr[right]){
                if (arr[left] < arr[right]) {
                    swapElement(arr, root, right);
                    next = right;
                }
                else{
                    swapElement(arr, root, left);
                    next = left;
                }
                if (next <= (max-1)/2)
                    rootSort(arr, next, max);
            }
        }
        private static void swapElement(int[] arr, int first, int second){
            int temp = arr[first];
            arr[first] = arr[second];
            arr[second] = temp;
        }
    }

    public static class ChessBoard{
        private char[][] board;
        private int numberOfQueens;
        private final int boardSize;


        public ChessBoard(int size) {
            board = new char[size][size];
            boardSize = size;
            numberOfQueens = 0;
        }

        public boolean putQueen(int x, int y){
            if (board[x][y] != 0){
                return false;
            }
            markBoard(x, y);
            board[x][y] = 'Q';
            numberOfQueens++;
            return true;
        }
        private void markBoard(int x, int y){
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (x == i || y == j || (x-y) == (i-j) || (x+y) == (i+j))
                        board[i][j] = 'x';
                }
            }
        }
        public boolean checkQueensOnBoard(int number){
            return numberOfQueens == number;
        }
        public boolean queenRandomPut(){
            Random random = new Random();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < boardSize; i++){
                for (int j = 0; j < boardSize; j++) {
                    if (board[i][j] == 0){
                        stringBuilder.append(String.format("%d:%d ", i, j));
                    }
                }
            }
            if(stringBuilder.isEmpty()) return false;
            String[] freeCells = stringBuilder.toString().strip().split("\\s");
            int x = Integer.parseInt(freeCells[random.nextInt(freeCells.length)].split(":")[0]);
            int y = Integer.parseInt(freeCells[random.nextInt(freeCells.length)].split(":")[1]);
            return putQueen(x,y);
        }
        public void clearChessBoard(){
            board = new char[boardSize][boardSize];
            numberOfQueens = 0;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (char[] line:board) {
                stringBuilder.append(Arrays.toString(line));
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }
}
