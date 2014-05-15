package pdfTrat;

import templates.IsoTemplate;
import templates.IsoTemplateImp;
import utils.PathFiles;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */

public class FullProcessDocumentImp implements PathFiles, FullProcessDocument {

    ArrayList<String> textList;
    IsoTemplate isoTemplate;

    public FullProcessDocumentImp(IsoTemplate isoTemplate){
        this.isoTemplate = isoTemplate;
    }

    public void start(File inputFile, int idStandard){
        ParsePDF parsePDF = new ParsePDF();
        parsePDF.pdfToText(inputFile, NAME_TXT_OUTPUT, TEMPORAL_DIRECTORY);

        textList = treatmentText();
        isoTemplate .runTemplate(treatmentText(), idStandard);
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

