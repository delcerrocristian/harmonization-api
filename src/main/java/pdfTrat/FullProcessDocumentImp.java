package pdfTrat;

import templates.iso.IsoTemplate;
import utils.PathFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristian del Cerro.
 */

public class FullProcessDocumentImp implements PathFiles, FullProcessDocument {

    ArrayList<String> textList;
    IsoTemplate isoTemplate;

    public FullProcessDocumentImp(IsoTemplate isoTemplate){
        this.isoTemplate = isoTemplate;
    }

    public void start(File inputFile, int idStandard, List<String> patterns){
        ParsePDF parsePDF = new ParsePDF();
        parsePDF.pdfToText(inputFile, NAME_TXT_OUTPUT, TEMPORAL_DIRECTORY);

        textList = treatmentText();
        isoTemplate .runTemplate(textList, idStandard, patterns);
    }

    private ArrayList<String> treatmentText(){
        String outTemporalFileTextPath = TEMPORAL_DIRECTORY+"/"+NAME_TXT_OUTPUT;
        ArrayList<String> treatmentList;

        ParseText parseText = new ParseText();
        treatmentList = parseText.listLineText(outTemporalFileTextPath);
        treatmentList = parseText.cleanTextISO(treatmentList);

        return treatmentList;
    }
}

