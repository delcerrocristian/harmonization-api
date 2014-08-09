package test.templateCmmi;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import templates.cmmi.SearchPatterns;

import static templates.cmmi.SearchPatterns.searchStartProcessAreas;

public class SearchPatternsTest{

    @Test
    public void testSearchStartProcessAreasSuccessfully() throws Exception {
        assert searchStartProcessAreas("text by acronym: ");

    }

    @Test
    public void testSearchStartProcessAreasSuccessfully2() throws Exception {
        assert searchStartProcessAreas("text by acronym:");

    }

    @Test
    public void testSearchStartProcessAreasUnSuccessfully() throws Exception {
        assert searchStartProcessAreas("by acronym:");

    }

    @Test
    public void testSearchStartProcessAreasUnSuccessfully2() throws Exception {
        assert !searchStartProcessAreas("text by acronim: ");

    }


    public void testSearchEndProcessAreas() throws Exception {

    }

    public void testSearchThisPattern() throws Exception {

    }

    public void testSearchEndPurposeStatement() throws Exception {

    }

    public void testSearchStartListGoals() throws Exception {

    }

    public void testSearchStartSpecificsGoal() throws Exception {

    }

    public void testIsGoal() throws Exception {

    }

    public void testIsPractice() throws Exception {

    }

    public void testSearchFinalDot() throws Exception {

    }

    public void testSearchEndWorkProduct() throws Exception {

    }

    public void testSearchStartWorkProduct() throws Exception {

    }

    public void testNumberStartSentence() throws Exception {

    }

    public void testBlankCharacterStartSentence() throws Exception {

    }
}