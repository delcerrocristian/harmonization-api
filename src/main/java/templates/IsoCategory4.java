package templates;

import services.FirstFilterService;
import utils.PathFiles;
import utils.UtilsFile;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static templates.FindMethod.*;

/**
 * Created by Cristian del Cerro.
 */
/*public class IsoCategory4 implements PathFiles {}*/

  /*  ArrayList<String> list;
    int idStandard;
    FirstFilterService firstFilterService;

    File fileCategory4;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    PrintWriter printWriter;

    IsoSupportFindMethods isoSupportFindMethods;

    IsoCategory4(ArrayList<String> list, int idStandard, FirstFilterService firstFilterService)throws IOException {
        this.list = list;
        this.idStandard = idStandard;
        this.firstFilterService = firstFilterService;

        fileCategory4 = new File(ISO_CATEGORY_4);
        fileWriter = new FileWriter(fileCategory4);
        bufferedWriter = new BufferedWriter(fileWriter);
        printWriter = new PrintWriter(bufferedWriter);

        isoSupportFindMethods = new IsoSupportFindMethods();
    }

/*    void find()throws Exception{


        int countFulSentence;

        for(int i=0; i<list.size(); i++){

            String currentSentence= list.get(i);

            if(existShallBe(currentSentence)){

                /*Comprueba que empieza por mayuscula, si no es así retrocede hasta donde
                * para coger la frase entera.
                        */

/*                countFulSentence = 0;
                while(!patternMayusIni().matcher(list.get(i-countFulSentence)).matches()
                        && !patternNumericIni().matcher(list.get(i-countFulSentence)).matches() ){
                    countFulSentence++;
                }
                for(int j=countFulSentence; j>0; j--){
                    try{
                        printWriter.append(list.get(i-j)+" ");
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }

                /*
                 Comprueba que la frase acaba para coger la frase entera, comprueba
                 si acaba en punto o 2 puntos para ello. Puede que no acabe en ninguno
                 de estos simbolos, pero en ese caso a continuación siempre hay una
                 enumeración.*/

/*                while(!patternFinalDot().matcher(list.get(i)).matches()
                        && !patternTwoFinalDot().matcher(list.get(i)).matches()
                        && !isoSupportFindMethods.existEnumeration(list.get(i+1))){
                    try{
                        printWriter.append(list.get(i)+" ");
                        i++;
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }
                printWriter.append(list.get(i) + "\n");
                if(isoSupportFindMethods.existEnumeration(list.get(i + 1))){
                    i= isoSupportFindMethods.enumerationABC(list, i + 1, printWriter);
                }
            }


            // Lo mismo que lo anterior pero con should en vez de shall


            else if(existShouldBe(currentSentence)){
                countFulSentence = 0;
                while(!patternMayusIni().matcher(list.get(i-countFulSentence)).matches()
                        && !patternNumericIni().matcher(list.get(i-countFulSentence)).matches() ){
                    countFulSentence++;
                }
                for(int j=countFulSentence; j>0; j--){
                    try{
                        printWriter.append(list.get(i - j) + " ");
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
                while(!patternFinalDot().matcher(list.get(i)).matches()
                        && !patternTwoFinalDot().matcher(list.get(i)).matches()
                        && !isoSupportFindMethods.existEnumeration(list.get(i+1))){
                    try{
                        printWriter.append(list.get(i)+" ");
                        i++;
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }
                printWriter.append(list.get(i) + "\n");
                if(isoSupportFindMethods.existEnumeration(list.get(i+1))){
                    i= isoSupportFindMethods.enumerationABC(list , i+1, printWriter);
                }
            }
        }
        closeAll();
    }

    private void closeAll()throws IOException{
        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();
    }

} */