package templates.cmmi;

import persistence.firstfilter.cmmi.model.*;
import persistence.firstfilter.cmmi.model.Process;
import services.cmmi.CmmiService;

import java.util.ArrayList;

import static templates.cmmi.SearchPatterns.*;

/**
 * Created by Cristian del Cerro
 */
public class CmmiDefaultAlgorithm implements CmmiAlgorithm{

    CmmiService cmmiService;
    ArrayList<String> processArea;
    int idStandard;

    public CmmiDefaultAlgorithm(CmmiService cmmiService) {
        this.cmmiService = cmmiService;
        this.processArea = new ArrayList<String>();
    }

    @Override
    public void start(ArrayList<String> listOfText, int idStandard) {
        this.idStandard = idStandard;
        int currentIndex = findProcessArea(listOfText);
        searchInformation(listOfText, currentIndex);

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
            processArea.set(i, sentencesToCleanSplit[0].substring(0, sentencesToCleanSplit[0].length()).toUpperCase());
        }
    }

    private void searchInformation(ArrayList<String> listOfText, int index) {
        for(String currentProcessArea : processArea){
            findAndFillProcess(listOfText,currentProcessArea, index);
        }

    }

    private int findAndFillProcess(ArrayList<String> listOfText, String process, int index) {
        int currentIndex = 0;
        persistence.firstfilter.cmmi.model.Process currentProcess = new Process();
        currentProcess.setStandard(idStandard);

        for(int i = index; i< listOfText.size(); i++) {
            if(searchThisPattern(process, listOfText.get(i))){
                currentProcess.setName(process);
                i++;
                String[] sentenceWithAreaAndMaturityLevel = listOfText.get(i).split("Area");
                currentProcess.setAreaCategory(sentenceWithAreaAndMaturityLevel[0].substring(2,
                        sentenceWithAreaAndMaturityLevel[0].length()-1));
                currentProcess.setMaturityLevel(sentenceWithAreaAndMaturityLevel[1].substring(
                        sentenceWithAreaAndMaturityLevel[1].length()-2,
                        sentenceWithAreaAndMaturityLevel[1].length()-1));

                i = i + 2;
                String purposeStatement = "";
                while(!searchEndPurposeStatement(listOfText.get(i))){
                    purposeStatement = purposeStatement + listOfText.get(i).toString() +" ";
                    i++;
                }
                currentProcess.setPurposeStatement(purposeStatement);
                int idProcess = cmmiService.createProcess(currentProcess);
                currentIndex = findSpecificGoals(process, listOfText, index, idProcess);
                break;
            }


        }
        return currentIndex;
    }

    private int findSpecificGoals(String process, ArrayList<String>  listOfText, int index, int idProcess) {
        ArrayList<String> listGoals = new ArrayList<String>();
        boolean open = false;
        int currentIndex = index;

        while(!searchThisPattern(process, listOfText.get(currentIndex))) {
            currentIndex++;
        }

        for(int i = currentIndex; i<listOfText.size(); i++) {
            if(!open && searchStartListGoals(listOfText.get(i))) {
                open = true;
            }
            else if(open && searchStartSpecificsGoal(listOfText.get(i))) {
                currentIndex = i;
                break;
            }
            else if(open) {
                listGoals.add(listOfText.get(i));
            }
        }

        int idSpecificGoal=-1;

        int indexElementGoal = 0;
        for(String elementGoal : listGoals){
            indexElementGoal=currentIndex;
            if(isGoal(elementGoal)){
                SpecificGoal specificGoal = new SpecificGoal();
                specificGoal.setProcess(idProcess);
                specificGoal.setTitle(elementGoal.substring(5));
                String description= "";
                for (int i = indexElementGoal; i < listOfText.size(); i++) {
                    if(searchThisPattern(elementGoal.substring(0,elementGoal.length()-1), listOfText.get(i))){
                        i++;
                        for(int j=i ;j<listOfText.size();j++) {
                            description = description + listOfText.get(j) + " ";
                            if (searchFinalDot(listOfText.get(j))) {
                                indexElementGoal = j;
                                break;
                            }
                        }
                        break;
                    }
                }
                specificGoal.setDescription(description);
                idSpecificGoal = cmmiService.createSpecificGoal(specificGoal);
            }
            else if(isPractice(elementGoal)){
                int idSpecificPractice = -1;
                SpecificPractice specificPractice = new SpecificPractice();
                specificPractice.setSpecificGoal(idSpecificGoal);
                specificPractice.setTitle(elementGoal.substring(7));
                String description = "";
                for(int i = indexElementGoal; i<listOfText.size(); i++) {
                    if(searchThisPattern(elementGoal.substring(0,elementGoal.length()-1), listOfText.get(i))) {
                        i++;
                        for(int j=i;j<listOfText.size();j++) {
                            description = description + listOfText.get(j) + " ";
                            if (searchFinalDot(listOfText.get(j))) {
                                indexElementGoal = j;
                                break;
                            }
                        }
                        break;
                    }
                }
                specificPractice.setDescription(description);
                idSpecificPractice = cmmiService.createSpecificPractice(specificPractice);

                while(!searchStartWorkProduct(listOfText.get((indexElementGoal)))){
                    indexElementGoal++;
                }
                indexElementGoal++;
                while(!searchEndWorkProduct(listOfText.get(indexElementGoal)) &&
                        !isPractice(listOfText.get(indexElementGoal))) {
                    WorkProduct workProduct = new WorkProduct();
                    workProduct.setSpecificPractice(idSpecificPractice);
                    String descriptionWorkProduct = listOfText.get(indexElementGoal).substring(3);
                    indexElementGoal++;
                    while(!searchEndWorkProduct(listOfText.get(indexElementGoal)) &&
                            !numberStartSentence(listOfText.get(indexElementGoal)) &&
                            !isPractice(listOfText.get(indexElementGoal))){
                        descriptionWorkProduct = " "+descriptionWorkProduct;
                        indexElementGoal++;
                    }
                    workProduct.setDescription(cleanStrings(descriptionWorkProduct));
                    cmmiService.createWorkProduct(workProduct);
                }

            }

        }
        return currentIndex;
    }

    private String cleanStrings(String string) {
        String cleanedString = string;
        while (blankCharacterStartSentence(cleanedString)){
            cleanedString = cleanedString.substring(1);
        }

        return cleanedString;
    }
}
