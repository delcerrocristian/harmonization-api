package domain.templates;

/**
 * Created by Cristian del Cerro.
 */

import utils.PathFiles;

import java.util.ArrayList;

import static utils.UtilsFile.makeDirectory;

public class IsoTemplate implements PathFiles, IsoTemplateInterface {


    public IsoTemplate(){
        makeDirectory(ISO_TEMPORAL_DIRECTORY);
    }

    public void runTemplate(ArrayList<String> listOfText)throws Exception{
                IsoCategory1 isoCategory1 = new IsoCategory1(listOfText);
                isoCategory1.find();
    }

    /*public void findCatagory4 (ArrayList<String> Lista)throws Exception{

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

                                *//*Comprueba que empieza por mayuscula, si no es así retrocede hasta donde
                                 * para coger la frase entera.
                                 *//*

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

                                *//*
                                 * Comprueba que la frase acaba para coger la frase entera, comprueba
                                 * si acaba en punto o 2 puntos para ello. Puede que no acabe en ninguno
                                 * de estos simbolos, pero en ese caso a continuación siempre hay una
                                 * enumeración.
                                 *//*
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

                        *//*
                         * Lo mismo que lo anterior pero con should en vez de shall
                         *//*

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
*/
    /*public void findCatagory5 (ArrayList<String> Lista)throws Exception{

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

                                *//*Comprueba que empieza por mayuscula, si no es así retrocede hasta donde
                                 * para coger la frase entera.
                                 *//*

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

                                *//*
                                 * Comprueba que la frase acaba para coger la frase entera, comprueba
                                 * si acaba en punto o 2 puntos para ello. Puede que no acabe en ninguno
                                 * de estos simbolos, pero en ese caso a continuación siempre hay una
                                 * enumeración.
                                 *//*
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

                        *//*
                         * Lo mismo que lo anterior pero con should en vez de shall
                         *//*

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
    }*/


    /*public void findCatagory7 (ArrayList<String> Lista)throws Exception{



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

                                *//*Comprueba que empieza por mayuscula, si no es así retrocede hasta donde
                                 * para coger la frase entera.
                                 *//*

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

                                *//*
                                 * Comprueba que la frase acaba para coger la frase entera, comprueba
                                 * si acaba en punto o 2 puntos para ello. Puede que no acabe en ninguno
                                 * de estos simbolos, pero en ese caso a continuación siempre hay una
                                 * enumeración.
                                 *//*
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
    }*/




}