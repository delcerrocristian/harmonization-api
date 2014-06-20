package templates.cmmi;

import java.util.ArrayList;
import java.util.List;

import static templates.cmmi.SearchPatterns.findEndProcessAreas;
import static templates.cmmi.SearchPatterns.findStartProcessAreas;

/**
 * Created by Cristian del Cerro.
 */
public class CmmiTemplateImp implements CmmiTemplate {

    ArrayList<String> processArea;

    public CmmiTemplateImp() {
        processArea = new ArrayList<String>();
    }

    @Override
    public void runTemplate(ArrayList<String> listOfText, int idStandard) {
        int currentIndex = findProcessArea(listOfText);
        System.out.print("hola");

    }

    private int findProcessArea(ArrayList<String> listOfText) {
        boolean open = false;
        boolean close = false;

        int index = 0;
        for(int i = 0; i<listOfText.size() && !close; i++) {
            if(!open && findStartProcessAreas(listOfText.get(i))) {
                open = true;
            }
            else if(open && findEndProcessAreas(listOfText.get(i))) {
                close = true;
            }
            else if(open && !close){
                processArea.add(listOfText.get(i).substring(1));
            }
            index = i;
        }
        cleanProcessAreas();
        return index;
    }

    private void cleanProcessAreas(){
        String[] sentencesToCleanSplit;
        for(int i=0; i<processArea.size(); i++){
            sentencesToCleanSplit = processArea.get(i).split("\\(");
            processArea.set(i, sentencesToCleanSplit[0].substring(0, sentencesToCleanSplit[0].length()-1).toUpperCase());
        }
    }
}
