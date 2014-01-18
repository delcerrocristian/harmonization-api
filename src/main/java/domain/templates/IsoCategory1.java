package domain.templates;

import domain.PathFiles;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public class IsoCategory1 implements PathFiles {

    ArrayList<String> list;

    File fileCategory1;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    PrintWriter printWriter;

    public IsoCategory1(ArrayList<String> list)throws IOException{
        this.list = list;

        fileCategory1 = new File(ISO_CATEGORY_1);
        fileWriter = new FileWriter(fileCategory1);
        bufferedWriter = new BufferedWriter(fileWriter);
        printWriter = new PrintWriter(bufferedWriter);
    }

     void find()throws Exception{


    }
}
