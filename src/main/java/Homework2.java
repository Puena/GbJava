import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Homework2 {

    public static String serialize(String jsonStr) {
        StringBuilder result = new StringBuilder("select * from students where ");
        Pattern pattern = Pattern.compile("\"\\w+\":\"\\w+\"");
        Matcher matcher = pattern.matcher(jsonStr);
        String[] strArr = new String[2];
        String and = "";
        while (matcher.find()){
            if (strArr[0] != null) and = " AND ";
            strArr = jsonStr.substring(matcher.start()+1, matcher.end()-1).split("\":\"");
            if (!strArr[1].equals("null")) {
                result.append(String.format(and));
                result.append(String.format("%s = '%s'", strArr[0], strArr[1]));
            }
        }
        result.append(";");
        return result.toString();
    }

    public static int[] bubbleSort(int[] array) throws IOException {
        Logger logger = Logger.getLogger(Homework2.class.getName());
        FileHandler fh = new FileHandler("./src/homework2/log.txt");
        logger.addHandler(fh);
        SimpleFormatter sFormat = new SimpleFormatter();
        fh.setFormatter(sFormat);
        boolean sorted = false;
        int tmp;
        while (!sorted){
            for (int i = 0; i < array.length-1; i++) {
                sorted = true;
                if (array[i] > array[i+1]){
                    sorted = false;
                    tmp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = tmp;
                }
                logger.log(Level.INFO, Arrays.toString(array));
            }
        }
        return array;
    }
}
