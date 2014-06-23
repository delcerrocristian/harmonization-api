package templates.cmmi;

import java.util.regex.Pattern;

/**
 * Created by Cristian del Cerro
 */
public class SearchPatterns {

    public static boolean searchStartProcessAreas(String sentence){
        return Pattern.compile(".*by acronym: $").matcher(sentence).matches();
    }

    public static boolean searchEndProcessAreas(String sentence){
        return Pattern.compile(".*Purpose Statements.*").matcher(sentence).matches();
    }

    public static boolean searchThisPattern(String pattern, String sentence){
        String currentSentence = ".*"+pattern+".*";
        return Pattern.compile(currentSentence).matcher(sentence).matches();
    }

    public static boolean searchEndPurposeStatement(String sentence){
        return Pattern.compile(".*Introductory Notes.*").matcher(sentence).matches();
    }

    public static boolean searchStartListGoals(String sentence) {
        return Pattern.compile(".*Specific Goal and Practice Summary.*").matcher(sentence).matches();
    }

    public static boolean searchStartSpecificsGoal(String sentence) {
        return Pattern.compile(".*Specific Practices by Goal.*").matcher(sentence).matches();
    }

    public static boolean isGoal(String sentence) {
        return Pattern.compile("^SG.*").matcher(sentence).matches();
    }

    public static boolean isPractice(String sentence) {
        return Pattern.compile("^SP.*").matcher(sentence).matches();
    }

    public static boolean searchFinalDot(String sentence) {
        return Pattern.compile(".*[.] $").matcher(sentence).matches() ||
                Pattern.compile(".*[.]$").matcher(sentence).matches();
    }

    public static boolean searchEndWorkProduct(String sentence) {
        return Pattern.compile(".*Subpractices.*").matcher(sentence).matches();
    }
    public static boolean searchStartWorkProduct(String sentence) {
        return Pattern.compile(".*Example Work Products.*").matcher(sentence).matches();
    }

    public static boolean numberStartSentence(String sentence) {
        return Pattern.compile("^[0-9].*").matcher(sentence).matches();
    }

    public static boolean blankCharacterStartSentence(String sentence) {
        return Pattern.compile("^ .*").matcher(sentence).matches();
    }
}
