package templates;

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

    public static boolean existShouldInclude(String sentence){
        return Pattern.compile(".*should include.*").matcher(sentence).matches();
    }

    public static boolean existDocumentInputOutput(String sentence){
        return Pattern.compile(".*(documented|input|output).*").matcher(sentence).matches();
    }

    public static boolean existShallVerbByToOn(String sentence){
        return Pattern.compile(".*shall be [a-z]* (by|to|on).*").matcher(sentence).matches();
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
