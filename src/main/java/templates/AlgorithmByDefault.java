package templates;

import services.FirstFilterService;

import java.io.IOException;
import java.util.ArrayList;

import static templates.Algorithm.estimateReliability;
import static templates.FindMethod.*;
import static templates.FindMethod.patternTwoFinalDot;

/**
 * Created by spukyn on 4/05/14.
 */
public class AlgorithmByDefault {

    private ArrayList<String> list;
    private int idStandard;
    private FirstFilterService firstFilterService;
    private String pattern;


    IsoSupportFindMethods isoSupportFindMethods;

    AlgorithmByDefault(ArrayList<String> list, int idStandard, FirstFilterService firstFilterService, String pattern) {
        this.list = list;
        this.idStandard = idStandard;
        this.firstFilterService = firstFilterService;
        this.pattern = pattern;
    }

    void find()throws Exception{

        int countFullSentence;
        String contentCurrentMain;

        for(int i=0; i<list.size(); i++){

            String currentSentence = list.get(i);


            if(findSentenceByPattern(pattern, currentSentence)){
                                /*Comprueba que empieza por mayuscula, si no es así retrocede hasta donde
                                 * para coger la frase entera.
                                 */
                countFullSentence = 0;
                contentCurrentMain = "";
                while(!patternMayusIni().matcher(list.get(i-countFullSentence)).matches()
                        && !patternNumericIni().matcher(list.get(i-countFullSentence)).matches() ){
                    countFullSentence++;

                }
                for(int j=countFullSentence; j>0; j--){
                    try{
                        contentCurrentMain = contentCurrentMain + list.get(i - j) + " ";
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
                        contentCurrentMain = contentCurrentMain + list.get(i) + " ";
                        i++;
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }
                contentCurrentMain = contentCurrentMain + list.get(i);
                int idProcess;
                if(!findProcess(list, i-1).equals("Not Found")){

                }
                findActivity(list, i-1);
                MainSentence currentMainSentence = new MainSentence(contentCurrentMain, "A", idStandard);
                currentMainSentence = estimateReliability(currentMainSentence);
                int idCurrentMainSentence = firstFilterService.addMainSentence(currentMainSentence);
                if(isoSupportFindMethods.existEnumeration(list.get(i + 1))){
                    i= isoSupportFindMethods.enumerationABC(list, i + 1, firstFilterService, idCurrentMainSentence);
                }
            }

                        /*
                         * Lo mismo que lo anterior pero con should en vez de shall
                         */

            else if(existShould(currentSentence)
                    && !existShouldBe(currentSentence)
                    && !existShouldInclude(currentSentence)){
                countFullSentence = 0;
                contentCurrentMain = "";
                while(!patternMayusIni().matcher(list.get(i-countFullSentence)).matches()
                        && !patternNumericIni().matcher(list.get(i-countFullSentence)).matches() ){
                    countFullSentence++;
                }
                for(int j=countFullSentence; j>0; j--){
                    try{
                        contentCurrentMain = contentCurrentMain + list.get(i - j) + " ";
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
                while(!patternFinalDot().matcher(list.get(i)).matches()
                        && !patternTwoFinalDot().matcher(list.get(i)).matches()
                        && !isoSupportFindMethods.existEnumeration(list.get(i + 1))){
                    try{
                        contentCurrentMain = contentCurrentMain + list.get(i) + " ";
                        i++;
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }
                contentCurrentMain = contentCurrentMain + list.get(i);
                MainSentence currentMainSentence = new MainSentence(contentCurrentMain, "A", idStandard);
                currentMainSentence = estimateReliability(currentMainSentence);
                int idCurrentMainSentence = firstFilterService.addMainSentence(currentMainSentence);
                if(isoSupportFindMethods.existEnumeration(list.get(i + 1))){
                    i= isoSupportFindMethods.enumerationABC(list, i + 1, firstFilterService, idCurrentMainSentence);
                }
            }
        }
    }
}
