package pdfTrat;

import utils.PathFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristian del Cerro
 */
public class CmmiFullProcessDocument implements FullProcessDocument, PathFiles {
    @Override
    public void start(File inputFile, int idStandard, List<String> patterns){
        ParsePDF parsePDF = new ParsePDF();
        parsePDF.pdfToText(inputFile, NAME_TXT_OUTPUT, TEMPORAL_DIRECTORY);

        ArrayList<String> textList = treatmentText();

    }

    private ArrayList<String> treatmentText(){
        String outTemporalFileTextPath = TEMPORAL_DIRECTORY+"/"+NAME_TXT_OUTPUT;
        ArrayList<String> treatmentList;

        ParseCmmiText parseText = new ParseCmmiText();
        treatmentList = parseText.listLineText(outTemporalFileTextPath);
        treatmentList = parseText.cleanCmmiText(treatmentList);
        System.out.print("hola");

        return treatmentList;
    }
}
