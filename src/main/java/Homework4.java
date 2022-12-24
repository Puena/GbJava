import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Homework4 {
    public static void Task1(){
        LinkedList<Integer> linkedList1 = new LinkedList<>(List.of(3,12,6,9,1,2,5,90));
        LinkedList<String> linkedList2 = new LinkedList<>(List.of("abcas", "definition", "ggohi", "jbksl"));
        System.out.println("Rotation of " + linkedList1.toString() + " : " + reverseLinkedList(linkedList1));
        System.out.println("Rotation of " + linkedList2.toString() + " : " + reverseLinkedList(linkedList2));
    }

    public static void Task2() {
        HelperClass myQueueFromLL = new HelperClass(List.of(3,4,6,7,1,2,3,90));
        System.out.println("Create MyQueue from List: " + myQueueFromLL);
        myQueueFromLL.enqueue(1000);
        System.out.println("MyQueue after adding: " + myQueueFromLL);
        myQueueFromLL.first();
        myQueueFromLL.dequeue();
        System.out.println("MyQueue after actions: " + myQueueFromLL);

        System.out.println("Create new empty queue");
        HelperClass mQ = new HelperClass<String>();
        System.out.println("Adding two elements in the end");
        mQ.enqueue("First");
        mQ.enqueue("Second");
        System.out.println("MyQueue after adding: " + mQ);
        mQ.dequeue();
        System.out.println("MyQueue after actions: " + mQ);
    }

    public static <T> List<T> reverseLinkedList(LinkedList<T> list){
        LinkedList<T> result = new LinkedList<>();
        T element;
        while ((element = list.pollLast()) != null) result.add(element);
        return result;
    }

    static class HelperClass<T> {
        private LinkedList<T> data;

        public HelperClass() {
            this.data = new LinkedList<>();
        }

        public HelperClass(List<T> data) {
            this.data = new LinkedList<>(data);
        }
        public void enqueue(T element){
            data.addLast(element);
        }
        public T dequeue(){
            return data.pollFirst();
        }
        public T first(){
            return data.getFirst();
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public static void Task3() {

    }

    class AnotherCalc {
        private final Logger logger;
        private final Map<Integer, Function<CalcInput, Double>> operationMap;
        private final FileHandler fh;
        public AnotherCalc() throws IOException {
            logger = Logger.getLogger(AnotherCalc.class.getName());
            fh = new FileHandler("./src/homework4/calcLog.txt");
            SimpleFormatter sFormat = new SimpleFormatter();
            logger.addHandler(fh);
            fh.setFormatter(sFormat);
            operationMap = new HashMap<>();
            operationMap.put(1, (i) -> i.first + i.second);
            operationMap.put(2, (i) -> i.first - i.second);
            operationMap.put(3, (i) -> i.first * i.second);
            operationMap.put(4, (i) -> i.first / i.second);
        }
        public double calculate(double firstNumber, double secondNumber, int action){
            logger.log(Level.INFO, String.format("Action %d, with %.2f, %.2f", action, firstNumber, secondNumber));
            Function<CalcInput, Double> operation = operationMap.get(action);
            double result = operation.apply(new CalcInput(firstNumber, secondNumber));
            logger.log(Level.INFO, String.format("Calculation result %.2f", result));
            return result;
        }

        public static class CalcInput{
            double first;
            double second;

            public CalcInput(Double first, Double second) {
                this.first = first;
                this.second = second;
            }
        }
    }
}
