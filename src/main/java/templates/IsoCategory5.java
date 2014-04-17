package templates;

import persistence.firstfilter.model.MainSentence;
import services.FirstFilterService;
import utils.PathFiles;

import java.io.*;
import java.util.ArrayList;

import static templates.FindMethod.*;

/**
 * Created by Cristian del Cerro.
 */
public class IsoCategory5 implements PathFiles {

    ArrayList<String> list;
    int idStandard;
    FirstFilterService firstFilterService;
    IsoSupportFindMethods isoSupportFindMethods;

    IsoCategory5(ArrayList<String> list, int idStandard,  FirstFilterService firstFilterService)throws IOException {
        this.list = list;
        this.idStandard = idStandard;
        this.firstFilterService = firstFilterService;
        isoSupportFindMethods = new IsoSupportFindMethods();
    }

    void find() throws Exception{

        int countFullSentence;
        String contentCurrentMain;

        for(int i=0; i<list.size(); i++){

            String currentSentence = list.get(i);

            if(existShallInclude(currentSentence)){

                /*Comprueba que empieza por mayuscula, si no es así retrocede hasta donde
                 para coger la frase entera.
                        */

                countFullSentence  = 0;
                contentCurrentMain = "";
                while(!patternMayusIni().matcher(list.get(i - countFullSentence )).matches()
                        && !patternNumericIni().matcher(list.get(i-countFullSentence)).matches()){
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
                        && !isoSupportFindMethods.existEnumeration(list.get(i+1))){
                    try{
                        contentCurrentMain = contentCurrentMain + list.get(i) + " ";
                        i++;
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }
                contentCurrentMain = contentCurrentMain + list.get(i);
                MainSentence currentMainSentence = new MainSentence(contentCurrentMain, "E", idStandard);
                int idCurrentMainSentence = firstFilterService.addMainSentence(currentMainSentence);
                if(isoSupportFindMethods.existEnumeration(list.get(i+1))){
                    i= isoSupportFindMethods.enumerationABC(list, i+1, firstFilterService, idCurrentMainSentence);

                }
            }

            /*
            * Lo mismo que lo anterior pero con should en vez de shall
            */

           else if(existShouldInclude(currentSentence)){
                countFullSentence = 0;
                contentCurrentMain = "";
                while(!patternMayusIni().matcher(list.get(i - countFullSentence)).matches()
                        && !patternNumericIni().matcher(list.get(i-countFullSentence)).matches()){
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
                        && !isoSupportFindMethods.existEnumeration(list.get(i+1))){
                    try{
                        contentCurrentMain = contentCurrentMain + list.get(i) + " ";
                        i++;
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }
                contentCurrentMain = contentCurrentMain + list.get(i);
                MainSentence currentMainSentence = new MainSentence(contentCurrentMain, "E", idStandard);
                int idCurrentMainSentence = firstFilterService.addMainSentence(currentMainSentence);
                if(isoSupportFindMethods.existEnumeration(list.get(i+1))){
                    i= isoSupportFindMethods.enumerationABC(list, i+1, firstFilterService, idCurrentMainSentence);
                }
            }
        }
    }

}
