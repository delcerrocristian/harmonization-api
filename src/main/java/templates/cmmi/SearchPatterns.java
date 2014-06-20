package templates.cmmi;

import java.util.regex.Pattern;

/**
 * Created by Cristian del Cerro
 */
public class SearchPatterns {

    public static boolean findStartProcessAreas(String sentence){
        return Pattern.compile(".*by acronym: $").matcher(sentence).matches();
    }

    public static boolean findEndProcessAreas(String sentence){
        return Pattern.compile(".*Purpose Statements.*").matcher(sentence).matches();
    }
}
