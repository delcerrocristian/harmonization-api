package domain.templates;

/**
 * Created by Cristian del Cerro.
 */

import domain.PathFiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.UtilsFile.makeDirectory;

public class IsoTemplate implements PathFiles, IsoTemplateInterface {



    private File f, f2, f3, f4;
    FileWriter w, w2, w3, w4;
    BufferedWriter bw, bw2, bw3, bw4;
    PrintWriter wr, wr2, wr3, wr4;

    public IsoTemplate(){
        makeDirectory(ISO_TEMPORAL_DIRECTORY);
    }

    public void runTemplate(ArrayList<String> listOfText)throws Exception{
                IsoCategory1 isoCategory1 = new IsoCategory1(listOfText);
                isoCategory1.find();
    }


    public void findCategory1(File tempCategoryFile, ArrayList<String> Lista)throws Exception{


        //Tipo de documento 1
//        String patronShall = ".*shall.*";
//        Pattern patShall = Pattern.compile(patronShall);
//        String patronShallbe = ".*shall be.*";
//        Pattern patShallbe = Pattern.compile(patronShallbe);
        String patronShallin = ".*shall include.*";
        Pattern patShallin = Pattern.compile(patronShallin);

        //Tipo de documento 2
        String patronShould = ".*should.*";
        Pattern patShould = Pattern.compile(patronShould);
        String patronShouldbe = ".*should be.*";
        Pattern patShouldbe = Pattern.compile(patronShouldbe);
        String patronShouldin = ".*shall include.*";
        Pattern patShouldin = Pattern.compile(patronShouldin);

        String FinalPunto =".*[.]$";
        Pattern patFinPunto = Pattern.compile(FinalPunto);
        String Final2Puntos = ".*[:]$";
        Pattern patFin2Puntos = Pattern.compile(Final2Puntos);
        String MayusculaIni = "^[A-Z].*";
        Pattern patMayusIni = Pattern.compile(MayusculaIni);

        Matcher matShall, matShallbe, matShallin;
        Matcher matShould, matShouldbe, matShouldin;

        int contFraseEntera = 0;

        for(int i=0; i<Lista.size(); i++){

            //DOC 1
            matShall = patternShall().matcher(Lista.get(i));
            matShallbe = patternShallBe().matcher(Lista.get(i));
            matShallin = patShallin.matcher(Lista.get(i));

            //DOC2
            matShould = patShould.matcher(Lista.get(i));
            matShouldbe = patShouldbe.matcher(Lista.get(i));
            matShouldin = patShouldin.matcher(Lista.get(i));

            if(matShall.matches() && !matShallbe.matches() && !matShallin.matches()){

                                /*Comprueba que empieza por mayuscula, si no es así retrocede hasta donde
                                 * para coger la frase entera.
                                 */

                contFraseEntera = 1;
                while(!patMayusIni.matcher(Lista.get(i-contFraseEntera)).matches()){
                    contFraseEntera++;
                }
                for(int j=contFraseEntera; j>0; j--){
                    try{
                        wr.append(Lista.get(i-j)+" ");
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
                while(!patFinPunto.matcher(Lista.get(i)).matches()
                        && !patFin2Puntos.matcher(Lista.get(i)).matches()
                        && !existEnumeration(Lista.get(i+1))){
                    try{
                        wr.append(Lista.get(i)+" ");
                        i++;
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }
                wr.append(Lista.get(i)+"\n");
                System.out.println("BOOLEANO "+existEnumeration(Lista.get(i+1)));
                if(existEnumeration(Lista.get(i+1))){
                    System.out.println("2");
                    i= enumeracionABC(Lista, i+1,wr);
                }
            }

                        /*
                         * Lo mismo que lo anterior pero con should en vez de shall
                         */

            else if(matShould.matches() && !matShouldbe.matches() && !matShouldin.matches()){
                contFraseEntera = 1;
                while(!patMayusIni.matcher(Lista.get(i-contFraseEntera)).matches()){
                    contFraseEntera++;
                }
                for(int j=contFraseEntera; j>0; j--){
                    try{
                        wr.append(Lista.get(i-j)+" ");
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
                while(!patFinPunto.matcher(Lista.get(i)).matches()
                        && !patFin2Puntos.matcher(Lista.get(i)).matches()
                        && !existEnumeration(Lista.get(i+1))){
                    try{
                        wr.append(Lista.get(i)+" ");
                        i++;
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }
                wr.append(Lista.get(i)+"\n");
                System.out.println("BOOLEANO "+existEnumeration(Lista.get(i+1)));
                if(existEnumeration(Lista.get(i+1))){
                    System.out.println("2");
                    i= enumeracionABC(Lista, i+1,wr);
                }
            }
        }
        wr.close();
        bw.close();
    }

    public void findCatagory4 (ArrayList<String> Lista)throws Exception{

        //Tipo de documento 1

        String patronShallbe = ".*shall be.*";
        Pattern patShallbe = Pattern.compile(patronShallbe);

        //Tipo de documento 2
        String patronShouldbe = ".*should be.*";
        Pattern patShouldbe = Pattern.compile(patronShouldbe);

        String FinalPunto =".*[.]$";
        Pattern patFinPunto = Pattern.compile(FinalPunto);
        String Final2Puntos = ".*[:]$";
        Pattern patFin2Puntos = Pattern.compile(Final2Puntos);
        String MayusculaIni = "^[A-Z].*";
        Pattern patMayusIni = Pattern.compile(MayusculaIni);

        Matcher  matShallbe;
        Matcher matShouldbe;

        int contFraseEntera = 0;

        for(int i=0; i<Lista.size(); i++){

            //DOC 1
            matShallbe = patShallbe.matcher(Lista.get(i));

            //DOC2
            matShouldbe = patShouldbe.matcher(Lista.get(i));

            if(matShallbe.matches()){

                                /*Comprueba que empieza por mayuscula, si no es así retrocede hasta donde
                                 * para coger la frase entera.
                                 */

                contFraseEntera = 1;
                while(!patMayusIni.matcher(Lista.get(i-contFraseEntera)).matches()){
                    contFraseEntera++;
                }
                for(int j=contFraseEntera; j>0; j--){
                    try{
                        wr2.append(Lista.get(i-j)+" ");
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
                while(!patFinPunto.matcher(Lista.get(i)).matches()
                        && !patFin2Puntos.matcher(Lista.get(i)).matches()
                        && !existEnumeration(Lista.get(i+1))){
                    try{
                        wr2.append(Lista.get(i)+" ");
                        i++;
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }
                wr2.append(Lista.get(i)+"\n");
                System.out.println("BOOLEANO "+existEnumeration(Lista.get(i+1)));
                if(existEnumeration(Lista.get(i+1))){
                    i= enumeracionABC(Lista, i+1,wr2);
                }
            }

                        /*
                         * Lo mismo que lo anterior pero con should en vez de shall
                         */

            else if(matShouldbe.matches()){
                contFraseEntera = 1;
                while(!patMayusIni.matcher(Lista.get(i-contFraseEntera)).matches()){
                    contFraseEntera++;
                }
                for(int j=contFraseEntera; j>0; j--){
                    try{
                        wr2.append(Lista.get(i-j)+" ");
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
                while(!patFinPunto.matcher(Lista.get(i)).matches()
                        && !patFin2Puntos.matcher(Lista.get(i)).matches()
                        && !existEnumeration(Lista.get(i+1))){
                    try{
                        wr2.append(Lista.get(i)+" ");
                        i++;
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }
                wr2.append(Lista.get(i)+"\n");
                System.out.println("BOOLEANO "+existEnumeration(Lista.get(i+1)));
                if(existEnumeration(Lista.get(i+1))){
                    i= enumeracionABC(Lista, i+1,wr2);
                }
            }
        }
        wr2.close();
        bw2.close();
    }

    public void findCatagory5 (ArrayList<String> Lista)throws Exception{

        //Tipo de documento 1

        String patronShallin = ".*shall include.*";
        Pattern patShallin = Pattern.compile(patronShallin);

        //Tipo de documento 2
        String patronShouldin = ".*should include.*";
        Pattern patShouldin = Pattern.compile(patronShouldin);

        String FinalPunto =".*[.]$";
        Pattern patFinPunto = Pattern.compile(FinalPunto);
        String Final2Puntos = ".*[:]$";
        Pattern patFin2Puntos = Pattern.compile(Final2Puntos);
        String MayusculaIni = "^[A-Z].*";
        Pattern patMayusIni = Pattern.compile(MayusculaIni);

        Matcher  matShallin;
        Matcher matShouldin;

        int contFraseEntera = 0;

        for(int i=0; i<Lista.size(); i++){

            //DOC 1
            matShallin = patShallin.matcher(Lista.get(i));

            //DOC2
            matShouldin = patShouldin.matcher(Lista.get(i));

            if(matShallin.matches()){

                                /*Comprueba que empieza por mayuscula, si no es así retrocede hasta donde
                                 * para coger la frase entera.
                                 */

                contFraseEntera = 1;
                while(!patMayusIni.matcher(Lista.get(i-contFraseEntera)).matches()){
                    contFraseEntera++;
                }
                for(int j=contFraseEntera; j>0; j--){
                    try{
                        wr3.append(Lista.get(i-j)+" ");
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
                while(!patFinPunto.matcher(Lista.get(i)).matches()
                        && !patFin2Puntos.matcher(Lista.get(i)).matches()
                        && !existEnumeration(Lista.get(i+1))){
                    try{
                        wr3.append(Lista.get(i)+" ");
                        i++;
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }
                wr3.append(Lista.get(i)+"\n");
                if(existEnumeration(Lista.get(i+1))){
                    i= enumeracionABC(Lista, i+1,wr3);

                }
            }

                        /*
                         * Lo mismo que lo anterior pero con should en vez de shall
                         */

            else if(matShouldin.matches()){
                contFraseEntera = 1;
                while(!patMayusIni.matcher(Lista.get(i-contFraseEntera)).matches()){
                    contFraseEntera++;
                }
                for(int j=contFraseEntera; j>0; j--){
                    try{
                        wr3.append(Lista.get(i-j)+" ");
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
                while(!patFinPunto.matcher(Lista.get(i)).matches()
                        && !patFin2Puntos.matcher(Lista.get(i)).matches()
                        && !existEnumeration(Lista.get(i+1))){
                    try{
                        wr3.append(Lista.get(i)+" ");
                        i++;
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }
                wr3.append(Lista.get(i)+"\n");
                System.out.println("BOOLEANO "+existEnumeration(Lista.get(i+1)));
                if(existEnumeration(Lista.get(i+1))){
                    i= enumeracionABC(Lista, i+1,wr3);
                }
            }
        }
        wr3.close();
        bw3.close();
    }


    public void findCatagory7 (ArrayList<String> Lista)throws Exception{



        String patron = ".*(documented|input|output).*";
        Pattern pat = Pattern.compile(patron);

        String FinalPunto =".*[.]$";
        Pattern patFinPunto = Pattern.compile(FinalPunto);
        String Final2Puntos = ".*[:]$";
        Pattern patFin2Puntos = Pattern.compile(Final2Puntos);
        String MayusculaIni = "^[A-Z].*";
        Pattern patMayusIni = Pattern.compile(MayusculaIni);

        Matcher  mat;

        int contFraseEntera = 0;

        for(int i=0; i<Lista.size(); i++){

            mat = pat.matcher(Lista.get(i));

            if(mat.matches()){

                                /*Comprueba que empieza por mayuscula, si no es así retrocede hasta donde
                                 * para coger la frase entera.
                                 */

                contFraseEntera = 1;
                while(!patMayusIni.matcher(Lista.get(i-contFraseEntera)).matches()){
                    contFraseEntera++;
                }
                for(int j=contFraseEntera; j>0; j--){
                    try{
                        wr4.append(Lista.get(i-j)+" ");
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
                while(!patFinPunto.matcher(Lista.get(i)).matches()
                        && !patFin2Puntos.matcher(Lista.get(i)).matches()
                        && !existEnumeration(Lista.get(i+1))){
                    try{
                        wr4.append(Lista.get(i)+" ");
                        i++;
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                }
                wr4.append(Lista.get(i)+"\n");
                System.out.println("BOOLEANO "+existEnumeration(Lista.get(i+1)));
                if(existEnumeration(Lista.get(i+1))){
                    i= enumeracionABC(Lista, i+1,wr4);
                }
            }
        }
        wr4.close();
        bw4.close();
    }


    //metodo para obtener todas las sentencias dentro de una enumeracion
    private int enumeracionABC(ArrayList<String> Lista, int indice, PrintWriter wr)throws Exception{
        int contador = 0;

        String patronFinal = ".*(and|[;]|[.]|[:])$";
        Pattern patFinal = Pattern.compile(patronFinal);


        for(int i=indice; (i<Lista.size() && i!=-1); i++){
            System.out.println("HOLA CARACIOLA");
            if(Lista.get(i).charAt(0)== alphabet[contador]){
                System.out.println("PUES RESULTA QUE SI ENTRO AQUI");
                while(!(patFinal.matcher(Lista.get(i))).matches()){
                    System.out.println("Y AQUI TAMBIEN, YO ESKE ME DESPOLLO");
                    try{
                        wr.append(Lista.get(i)+" ");
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    i++;
                }
                try{
                    wr.append(Lista.get(i)+"\n");

                }
                catch(Exception e){
                    e.printStackTrace();
                }

                contador++;
                i=existAnotherEnumeration(Lista, i+1, contador) -1;

            }


        }

        return (indice + contador);
    }

    //Metodo de apoyo a EnumeracionABC
    private int existAnotherEnumeration (ArrayList<String> Lista, int indice, int abc){
        int exist = -1;
        boolean condicion = false;

        String patron = "^[a-z][)].*";
        Pattern pat = Pattern.compile(patron);
        for(int i=indice; (i<Lista.size() && condicion==false); i++){
            if(pat.matcher(Lista.get(i)).matches()){
                if(Lista.get(i).charAt(0)== alphabet[abc]){
                    exist=i;
                    condicion=true;
                }
                else{
                    condicion=true;
                }
            }

        }

        return exist;
    }

    //Metodo de apoyo a findObjetives que informa si la cadena es parte de una enumeracion o no
    private boolean existEnumeration(String cadena){
        boolean retorno=false;

        String patron = "^(a[)]|[-]).*";
        Pattern pat = Pattern.compile(patron);

        if(pat.matcher(cadena).matches()){
            retorno=true;
        }

        return retorno;
    }

    private Pattern patternShall(){
        return Pattern.compile(".*shall.*");
    }

    private Pattern patternShallBe(){
        return Pattern.compile(".*shall be.*");
    }

    private Pattern patternShallInclude(){
        return Pattern.compile(".*shall include.*");
    }

    //TODO
        /*private int enumeracionNUM (ArrayList<String> Lista, int indice){
                int contador = 0;
                boolean condicion = false;

                String patronFinal = ".*(and|[;]|[.]|[:])$";
                Pattern patFinal = Pattern.compile(patronFinal);

                for(int i = indice; (i<Lista.size() && condicion==false); i++){

                }
        }*/
//
//    public static void main(String args[]) {
//        ParseText t = new ParseText();
//        ArrayList<String> ListaLimpia = t.ListLineText("Adjuntos/pdflimpio.txt");
//
//
//        try{
//            IsoTemplate p = new IsoTemplate();
//            p.findObjetives(ListaLimpia);
//            p.findCatagory4(ListaLimpia);
//            p.findCatagory5(ListaLimpia);
//            p.findCatagory7(ListaLimpia);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//
//        }
//    }
}