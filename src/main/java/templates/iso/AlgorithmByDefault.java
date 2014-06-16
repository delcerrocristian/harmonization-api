package templates.iso;

import persistence.firstfilter.iso.model.Activity;
import persistence.firstfilter.iso.model.Process;
import persistence.firstfilter.iso.model.Task;
import services.iso.IsoService;

import java.util.ArrayList;

import static templates.iso.FindMethod.*;
import static templates.iso.FindMethod.patternTwoFinalDot;

/**
 * Created by spukyn on 4/05/14.
 */
public class AlgorithmByDefault {

    private ArrayList<String> list;
    private int idStandard;
    private IsoService isoService;
    private IsoSupportFindMethods isoSupportFindMethods;

    AlgorithmByDefault(ArrayList<String> list, int idStandard, IsoService isoService) {
        isoSupportFindMethods = new IsoSupportFindMethods();
        this.list = list;
        this.idStandard = idStandard;
        this.isoService = isoService;
    }

    void find(String currentPattern){
        String pattern = buildPattern(currentPattern);

        int countFullSentence;
        String currentContentTask;

        for(int i=0; i<list.size(); i++){

            String currentSentence = list.get(i);


            if(findSentenceByPattern(pattern, currentSentence)){
                                /*Comprueba que empieza por mayuscula, si no es así retrocede hasta donde
                                 * para coger la frase entera.
                                 */
                countFullSentence = 0;
                currentContentTask = "";
                while(!patternMayusIni().matcher(list.get(i-countFullSentence)).matches()
                        && !patternNumericIni().matcher(list.get(i-countFullSentence)).matches() ){
                    countFullSentence++;

                }
                for(int j=countFullSentence; j>0; j--){
                    try{
                        currentContentTask = currentContentTask + list.get(i - j) + " ";
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }

                                /*
                                 * Comprueba que la frase acaba para coger la frase entera, comprueba
                                 * si acaba en punto o 2 puntos para ello. Puede que no acabe en ninguno
                                 * de estos simbolos, pero en ese caso a continuación siempre hay una
                                 * enumeración.
                                 */
                while(!patternFinalDot().matcher(list.get(i)).matches()
                        && !patternTwoFinalDot().matcher(list.get(i)).matches()
                        && !isoSupportFindMethods.existEnumeration(list.get(i + 1))){
                    try{
                        currentContentTask = currentContentTask + list.get(i) + " ";
                        i++;
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }
                currentContentTask = currentContentTask + list.get(i);

                //TASK
                Task currentTask = new Task();

                //PROCESS
                Process currentProcess = new Process();
                currentProcess.setStandard(idStandard);
                currentProcess.setName(findProcess(list, i-1));
                int idProcess = isoService.readIdProcessByNameAndStandard(currentProcess.getName()
                        , idStandard);
                if(idProcess == -1){
                    idProcess = isoService.addProcess(currentProcess); /*Save process*/
                }

                //ACTIVITY
                Activity currentActivity = new Activity();
                currentActivity.setProcess(idProcess);

                int idActivity = -1;
                if(!currentProcess.getName().equals("Not Found")) {
                    currentActivity.setName(findActivity(list, i-1, currentProcess.getName()));
                    idActivity = isoService.readIdActivityByNameAndProcess(currentActivity.getName()
                            , currentActivity.getProcess());
                    if(idActivity == -1) {
                        idActivity = isoService.addActivity(currentActivity); /*Save Activity*/
                    }
                }
                else {
                    currentActivity.setName("Not Found");
                }

                if(isoSupportFindMethods.existEnumeration(list.get(i + 1))){
                    currentContentTask = isoSupportFindMethods.catEnumerationABC(list, i+1, currentContentTask);
                    i= isoSupportFindMethods.indexEnumerationABC(list, i+1);
                }
                //Complete task object with idProcess and idActivity
                currentTask.setProcess(idProcess);
                currentTask.setActivity(idActivity);
                currentTask.setContent(currentContentTask);
                isoService.addTask(currentTask);  /*Save task*/

            }
        }
    }
}
