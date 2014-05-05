package templates;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Cristian del Cerro.
 */
public class FindMethod {


    public static boolean existShall(String sentence){
        return Pattern.compile(".*shall.*").matcher(sentence).matches();
    }

    public static boolean existShallBe(String sentence){
        return Pattern.compile(".*shall be.*").matcher(sentence).matches();
    }

    public static boolean existShallInclude(String sentence){
        return Pattern.compile(".*shall include.*").matcher(sentence).matches();
    }

    public static boolean existShould(String sentence){
        return Pattern.compile(".*should.*").matcher(sentence).matches();
    }

    public static boolean existShouldBe(String sentence){
        return Pattern.compile(".*should be.*").matcher(sentence).matches();
    }
    @juan_rosell_ Pues no se si es bueno o malo que haya tantos partidos distintos de este tipo... espero que bueno.
    public static boolean existShouldInclude(String sentence){
        return Pattern.compile(".*should include.*").matcher(sentence).matches();
    }

    public static boolean existDocumentInputOutput(String sentence){
        return Pattern.compile(".*(documented|input|output).*").matcher(sentence).matches();
    }

    public static boolean existShallVerbByToOn(String sentence){
        return Pattern.compile(".*shall be [a-z]* (by|to|on).*").matcher(sentence).matches();
    }

    public static boolean findSentenceByPattern(String pattern, String sentence) {
        pattern = ".*"+pattern+".*";
        return Pattern.compile(pattern).matcher(sentence).matches();
    }

    public static String findProcess(ArrayList<String> list, int index) {
        String process = "Not Found";
        String patternProcess = "^[0-9].[0-9] [A-Z].*";

        for(int i = index; i>0 && process.equals("Not Found"); i --) {
            if(Pattern.compile(patternProcess).matcher(list.get(i)).matches()){
                process = list.get(i);
            }
        }
        return process;
    }

    public static String findActivity(ArrayList<String> list, int index) {
        String activity = "Not Found";
        String patternActivity = "^[0-9].[0-9].[0-9].*";

        for(int i = index; i>0 && activity.equals("Not Found"); i --) {
            if(Pattern.compile(patternActivity).matcher(list.get(i)).matches()){
                activity = list.get(i);
            }
        }
        return activity;
    }

    public static Pattern patternFinalDot(){
        return Pattern.compile(".*[.]$");
    }

    public static Pattern patternTwoFinalDot(){
        return Pattern.compile(".*[:]$");
    }

    public static Pattern patternMayusIni(){
        return Pattern.compile("^[A-Z].*");
    }

    public static Pattern patternNumericIni(){
        return Pattern.compile("^[0-9].*");
    }
}
