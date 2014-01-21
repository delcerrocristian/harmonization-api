package domain.templates;

import utils.PathFiles;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Cristian del Cerro.
 */
public class IsoCategory1 implements PathFiles {

    ArrayList<String> list;

    File fileCategory1;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    PrintWriter printWriter;

    IsoSupportFindMethods isoSupportFindMethods;

    public IsoCategory1(ArrayList<String> list)throws IOException{
        this.list = list;

        fileCategory1 = new File(ISO_CATEGORY_1);
        fileWriter = new FileWriter(fileCategory1);
        bufferedWriter = new BufferedWriter(fileWriter);
        printWriter = new PrintWriter(bufferedWriter);

        isoSupportFindMethods = new IsoSupportFindMethods();
    }

     void find()throws Exception{

         String FinalPunto =".*[.]$";
         Pattern patFinPunto = Pattern.compile(FinalPunto);
         String Final2Puntos = ".*[:]$";
         Pattern patFin2Puntos = Pattern.compile(Final2Puntos);
         String MayusculaIni = "^[A-Z].*";
         Pattern patMayusIni = Pattern.compile(MayusculaIni);

         Matcher matShall, matShallbe, matShallin;
         Matcher matShould, matShouldbe, matShouldin;

         int contFraseEntera = 0;

         for(int i=0; i<list.size(); i++){

             //DOC 1
             matShall = patternShall().matcher(list.get(i));
             matShallbe = patternShallBe().matcher(list.get(i));
             matShallin = patternShallInclude().matcher(list.get(i));

             //DOC2
             matShould = patternShould().matcher(list.get(i));
             matShouldbe = patternShouldBe().matcher(list.get(i));
             matShouldin = patternShouldInclude().matcher(list.get(i));

             if(matShall.matches() && !matShallbe.matches() && !matShallin.matches()){

                                /*Comprueba que empieza por mayuscula, si no es así retrocede hasta donde
                                 * para coger la frase entera.
                                 */

                 contFraseEntera = 1;
                 while(!patMayusIni.matcher(list.get(i-contFraseEntera)).matches()){
                     contFraseEntera++;
                 }
                 for(int j=contFraseEntera; j>0; j--){
                     try{
                         printWriter.append(list.get(i - j) + " ");
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
                 while(!patFinPunto.matcher(list.get(i)).matches()
                         && !patFin2Puntos.matcher(list.get(i)).matches()
                         && !isoSupportFindMethods.existEnumeration(list.get(i + 1))){
                     try{
                         printWriter.append(list.get(i) + " ");
                         i++;
                     }
                     catch(Exception e){
                         e.printStackTrace();
                     }

                 }
                 printWriter.append(list.get(i) + "\n");
                 System.out.println("BOOLEANO "+isoSupportFindMethods.existEnumeration(list.get(i + 1)));
                 if(isoSupportFindMethods.existEnumeration(list.get(i + 1))){
                     System.out.println("2");
                     i= isoSupportFindMethods.enumerationABC(list, i + 1, printWriter);
                 }
             }

                        /*
                         * Lo mismo que lo anterior pero con should en vez de shall
                         */

             else if(matShould.matches() && !matShouldbe.matches() && !matShouldin.matches()){
                 contFraseEntera = 1;
                 while(!patMayusIni.matcher(list.get(i-contFraseEntera)).matches()){
                     contFraseEntera++;
                 }
                 for(int j=contFraseEntera; j>0; j--){
                     try{
                         printWriter.append(list.get(i - j) + " ");
                     }
                     catch(Exception e){
                         e.printStackTrace();
                     }
                 }
                 while(!patFinPunto.matcher(list.get(i)).matches()
                         && !patFin2Puntos.matcher(list.get(i)).matches()
                         && !isoSupportFindMethods.existEnumeration(list.get(i + 1))){
                     try{
                         printWriter.append(list.get(i)+" ");
                         i++;
                     }
                     catch(Exception e){
                         e.printStackTrace();
                     }

                 }
                 printWriter.append(list.get(i) + "\n");
                 System.out.println("BOOLEANO "+isoSupportFindMethods.existEnumeration(list.get(i + 1)));
                 if(isoSupportFindMethods.existEnumeration(list.get(i + 1))){
                     System.out.println("2");
                     i= isoSupportFindMethods.enumerationABC(list, i + 1, printWriter);
                 }
             }
         }
         closeAll();
    }

    //TIPO DOCUMENTO 1
    private Pattern patternShall(){
        return Pattern.compile(".*shall.*");
    }

    private Pattern patternShallBe(){
        return Pattern.compile(".*shall be.*");
    }

    private Pattern patternShallInclude(){
        return Pattern.compile(".*shall include.*");
    }

    //TIPO DOCUMENTO 2
    private Pattern patternShould(){
        return Pattern.compile(".*should.*");
    }

    private Pattern patternShouldBe(){
        return Pattern.compile(".*should be.*");
    }

    private Pattern patternShouldInclude(){
        return Pattern.compile(".*should include.*");
    }

    private void closeAll()throws IOException{
        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();
    }
}
