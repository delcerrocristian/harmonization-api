package templates;

import persistence.firstfilter.model.EnumSentence;
import services.FirstFilterService;
import utils.UtilsTemplates;

import java.io.PrintWriter;
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

    public int enumerationABC(ArrayList<String> list, int index, FirstFilterService firstFilterService, int idMain)throws Exception{
        int count = 0;

        String stringFinalPattern = ".*(and|[;]|[.]|[:])$";
        Pattern finalPattern = Pattern.compile(stringFinalPattern);
        String currentEnumSentence;

        for(int i=index; (i<list.size() && i!=-1); i++){
            currentEnumSentence="";
            if(list.get(i).charAt(0)== UtilsTemplates.alphabet[count]){
                while(!(finalPattern.matcher(list.get(i))).matches()){
                    try{
                        currentEnumSentence = currentEnumSentence + list.get(i) + " ";
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    i++;
                }
                try{
                    currentEnumSentence = currentEnumSentence + list.get(i);
                    EnumSentence enumCurrentSentence = new EnumSentence(count + 1, currentEnumSentence, idMain);
                    firstFilterService.addEnumSentence(enumCurrentSentence);
                }
                catch(Exception e){
                    e.printStackTrace();
                }

                count++;
                i=existAnotherEnumeration(list, i+1, count) -1;
            }
        }
        return (index + count);
    }
}
