package pdfTrat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Cristian del Cerro
 */
public class ParseCmmiText {

    public ArrayList<String> listLineText(String FilePath) {
        ArrayList<String> Line = new ArrayList<String>();
        try{
            FileInputStream fstream = new FileInputStream(FilePath);
            DataInputStream entrada = new DataInputStream(fstream);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String strLinea;
            while ((strLinea = buffer.readLine()) != null)   {
                Line.add(strLinea);
            }
            entrada.close();

        }catch (Exception e){
            System.err.println("Ocurrio un error: " + e.getMessage());
        }
        return Line;
    }

    public ArrayList<String> cleanCmmiText(ArrayList<String> list){
        ArrayList<String> listWithoutBlankLines = cleanBlankLines(list);
        String patternTitle = getPattern(listWithoutBlankLines.get(0));

        ArrayList<String> listCleaned = cleanPages(patternTitle, listWithoutBlankLines);
        return listCleaned;
    }

    private String getPattern(String sentence) {
        return sentence.substring(0,4) + sentence.substring(5);
    }

    public ArrayList<String> cleanPages(String pattern, ArrayList<String> list) {
        ArrayList<String> listWithCleanPages = new ArrayList<String>();
        for(int i = 0; i<list.size(); i++) {
            if (list.get(i).equals(pattern)) {
                i =+ i + 2;
            }
            if (i < list.size()) {
                listWithCleanPages.add(list.get(i));
            }
        }
        return listWithCleanPages;
    }

    public ArrayList<String> cleanBlankLines(ArrayList<String> Lista){
        ArrayList<String> Limpia = new ArrayList<String>();

        String patron = "^ *";
        Pattern pat = Pattern.compile(patron);

        String patron2 = " *$";
        Pattern pat2 = Pattern.compile(patron2);

        String patron3 = ".*[a-zA-Z0-9].*";
        Pattern pat3 = Pattern.compile(patron3);

        for(int i=0; i<Lista.size();i++){
            if(!pat.matcher(Lista.get(i)).matches() &&
                    !pat2.matcher(Lista.get(i)).matches() &&
                    pat3.matcher(Lista.get(i)).matches()){
                Limpia.add(Lista.get(i));
            }
        }
        return Limpia;

    }
}
