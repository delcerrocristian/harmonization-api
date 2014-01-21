package domain.pdfTrat;

import domain.templates.IsoTemplate;
import utils.PathFiles;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */

public class FullProcessDocumentImp implements PathFiles {

    ArrayList<String> textList;

    public FullProcessDocumentImp(){

    }

    public void start(File inputFile)throws Exception{
        ParsePDF parsePDF = new ParsePDF();
        parsePDF.pdfToText(inputFile, NAME_TXT_OUTPUT, TEMPORAL_DIRECTORY);

        textList = treatmentText();

        IsoTemplate isoTemplate = new IsoTemplate();
        isoTemplate.runTemplate(treatmentText());
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

