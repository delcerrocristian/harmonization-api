package test.templateCmmi;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import services.cmmi.CmmiService;
import templates.cmmi.CmmiDefaultAlgorithm;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CmmiDefaultAlgorithmTest extends TestCase {

    private CmmiDefaultAlgorithm cmmiDefaultAlgorithm;
    private CmmiService cmmiService;
    private ArrayList<String> processAreaList;

    @Before
    public void setUp() {
        cmmiDefaultAlgorithm = new CmmiDefaultAlgorithm(cmmiService);
        processAreaList = mock(ArrayList.class);
        cmmiDefaultAlgorithm.initProcessAreaList(processAreaList);
    }

    @Test
    public void testFindProcessArea() {
        ArrayList<String> text = new ArrayList<>();
        text.add("text0");
        text.add("text1");
        text.add("text2");
        text.add("text3");
        text.add("text4 by acronym: ");
        text.add(" text5");
        text.add(" text6");
        text.add(" text7");
        text.add(" text8");
        text.add(" text9");
        text.add(" text10");
        text.add("text11");
        text.add("text12 Purpose Statements");
        text.add("text13");
        text.add("text14");
        text.add("text15");
        text.add("text16");

        int index = cmmiDefaultAlgorithm.findProcessArea(text);
        verify(processAreaList).add("text5");
        verify(processAreaList).add("text6");
        verify(processAreaList).add("text7");
        verify(processAreaList).add("text8");
        verify(processAreaList).add("text9");
        verify(processAreaList).add("text10");
        verify(processAreaList).add("ext11");

        assert index == 12;
    }
}