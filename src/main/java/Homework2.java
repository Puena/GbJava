import java.util.Map;
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
}
