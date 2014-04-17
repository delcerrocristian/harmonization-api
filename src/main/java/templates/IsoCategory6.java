package templates;

import services.FirstFilterService;
import utils.PathFiles;

import java.io.*;
import java.util.ArrayList;

import static templates.FindMethod.*;
import static templates.FindMethod.patternTwoFinalDot;

/**
 * Created by Cristian del Cerro.
 */
public class IsoCategory6 implements PathFiles {

    ArrayList<String> list;
    int idStandard;
    FirstFilterService firstFilterService;

    File fileCategory6;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    PrintWriter printWriter;

    IsoSupportFindMethods isoSupportFindMethods;

    IsoCategory6(ArrayList<String> list, int idStandard,  FirstFilterService firstFilterService)throws IOException {
        this.list = list;
        this.idStandard = idStandard;
        this.firstFilterService = firstFilterService;

        fileCategory6 = new File(ISO_CATEGORY_6);
        fileWriter = new FileWriter(fileCategory6);
        bufferedWriter = new BufferedWriter(fileWriter);
        printWriter = new PrintWriter(bufferedWriter);

        isoSupportFindMethods = new IsoSupportFindMethods();
    }

    public void find() throws Exception{

        int countFullSentence;

        for(int i=0; i<list.size(); i++){

            String currentSentence = list.get(i);

            if(existShallVerbByToOn(currentSentence)){

                /*Comprueba que empieza por mayuscula, si no es así retrocede hasta donde
                * para coger la frase entera.
                        */

                countFullSentence = 0;
                while(!patternMayusIni().matcher(list.get(i-countFullSentence)).matches()
                        && !patternNumericIni().matcher(list.get(i-countFullSentence)).matches()){
                    countFullSentence++;
                }
                for(int j=countFullSentence; j>0; j--){

                    try{
                        printWriter.append(list.get(i-j)+" ");
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
                        printWriter.append(list.get(i)+" ");
                        i++;
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }
                printWriter.append(list.get(i)+"\n");
                if(isoSupportFindMethods.existEnumeration(list.get(i+1))){
                    i= isoSupportFindMethods.enumerationABC(list, i+1, printWriter);
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

}
