package domain.templates;

import utils.UtilsFile;
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

    public int enumerationABC(ArrayList<String> list, int index, PrintWriter printWriter)throws Exception{
        int count = 0;

        String stringFinalPattern = ".*(and|[;]|[.]|[:])$";
        Pattern finalPattern = Pattern.compile(stringFinalPattern);


        for(int i=index; (i<list.size() && i!=-1); i++){
            System.out.println("HOLA CARACIOLA");
            if(list.get(i).charAt(0)== UtilsTemplates.alphabet[count]){
                System.out.println("PUES RESULTA QUE SI ENTRO AQUI");
                while(!(finalPattern.matcher(list.get(i))).matches()){
                    System.out.println("Y AQUI TAMBIEN, YO ESKE ME DESPOLLO");
                    try{
                        printWriter.append(list.get(i)+" ");
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    i++;
                }
                try{
                    printWriter.append(list.get(i)+"\n");

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
