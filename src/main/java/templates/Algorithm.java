package templates;

import persistence.firstfilter.model.MainSentence;

import java.util.Random;

/**
 * Created by spukyn on 29/04/14.
 */
public class Algorithm {

    public static MainSentence estimateReliability(MainSentence mainSentence) {
        Random random = new Random();
        mainSentence.setReliability(random.nextInt(3) + 1);
        return mainSentence;
    }
}
