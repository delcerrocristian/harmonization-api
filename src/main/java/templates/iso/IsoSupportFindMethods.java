package templates.iso;

import utils.UtilsTemplates;


import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Cristian del Cerro.
 */
public class IsoSupportFindMethods{

    public IsoSupportFindMethods(){

    }

    public boolean existEnumeration(String text){
        String patron = "^(a[)]|[-]).*";
        Pattern pat = Pattern.compile(patron);

        return pat.matcher(text).matches();

    }

    //Metodo de apoyo a EnumeracionABC
    private int existAnotherEnumeration (ArrayList<String> list, int index, int abc){
        int line = -1;
        boolean exist = false;

        String stringPattern = "^[a-z][)].*";
        Pattern pattern = Pattern.compile(stringPattern);
        for(int i=index; (i<list.size() && exist==false); i++){
            if(pattern.matcher(list.get(i)).matches()){
                if(list.get(i).charAt(0)== UtilsTemplates.alphabet[abc]){
                    line=i;
                    exist=true;
                }
                else{
                    exist=true;
                }
            }

        }

        return line;
    }

    public String catEnumerationABC(ArrayList<String> list, int index, String sentence){
        int count = 0;

        String stringFinalPattern = ".*(and|[;]|[.]|[:])$";
        Pattern finalPattern = Pattern.compile(stringFinalPattern);
        String currentSentence;

        for(int i=index; (i<list.size() && i!=-1); i++){
            currentSentence="";
            if(list.get(i).charAt(0)== UtilsTemplates.alphabet[count]){
                while(!(finalPattern.matcher(list.get(i))).matches()){
                    currentSentence = currentSentence + list.get(i) + " ";
                    i++;
                }
                currentSentence = currentSentence + list.get(i);
                sentence += "\n"+currentSentence;
                count++;
                i=existAnotherEnumeration(list, i+1, count) -1;
            }
        }
        return sentence;

    }

    public int indexEnumerationABC(ArrayList<String> list, int index){
        int countAlphabet = 0;
        int line = index;

        String stringFinalPattern = ".*(and|[;]|[.]|[:])$";
        Pattern finalPattern = Pattern.compile(stringFinalPattern);
        for(int i=index; (i<list.size() && i!=-1); i++){
            if(list.get(i).charAt(0)== UtilsTemplates.alphabet[countAlphabet]){
                while(!(finalPattern.matcher(list.get(i))).matches()){
                    i++;
                }
                line = i;
                countAlphabet++;
                i=existAnotherEnumeration(list, i+1, countAlphabet) -1;
            }
        }
        return line;
    }
}
