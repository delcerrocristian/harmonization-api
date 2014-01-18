package domain.pdfTrat;

import domain.PathFiles;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */

public class FullProcessDocumentImp implements PathFiles {

    ArrayList<String> textList;

    public void start(File inputFile){
        textList = new ArrayList<String>();

        ParsePDF parsePDF = new ParsePDF();
        parsePDF.pdfToText(inputFile, NAME_TXT_OUTPUT, TEMPORAL_DIRECTORY);
    }

    private ArrayList<String> treatmentText(){
        String outTemporalFileTextPath = TEMPORAL_DIRECTORY+"/"+NAME_TXT_OUTPUT;
        ArrayList<String> treatmentList = new ArrayList<String>();

        ParseText parseText = new ParseText();
        treatmentList = parseText.listLineText(outTemporalFileTextPath);
        treatmentList = parseText.cleanTextISO(treatmentList);

        return treatmentList;
    }
}

