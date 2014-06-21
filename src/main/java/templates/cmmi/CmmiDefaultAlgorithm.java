package templates.cmmi;

import services.cmmi.CmmiService;

import java.util.ArrayList;

import static templates.cmmi.SearchPatterns.*;

/**
 * Created by Cristian del Cerro
 */
public class CmmiDefaultAlgorithm implements CmmiAlgorithm{

    CmmiService cmmiService;
    ArrayList<String> processArea;

    public CmmiDefaultAlgorithm(CmmiService cmmiService) {
        this.cmmiService = cmmiService;
        this.processArea = new ArrayList<String>();
    }

    @Override
    public void start(ArrayList<String> listOfText, int idStandard) {
        int currentIndex = findProcessArea(listOfText);

    }

    private int findProcessArea(ArrayList<String> listOfText) {
        boolean open = false;
        boolean close = false;

        int index = 0;
        for(int i = 0; i<listOfText.size() && !close; i++) {
            if(!open && searchStartProcessAreas(listOfText.get(i))) {
                open = true;
            }
            else if(open && searchEndProcessAreas(listOfText.get(i))) {
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

    private int findAndFillProcess(ArrayList<String> listOfText, String process, String endProcess, int index) {
        for(int i = index; i< listOfText.size(); i++) {

        }
        return 0;
    }
}
