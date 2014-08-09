package pdfTrat;

import templates.cmmi.CmmiTemplate;
import templates.cmmi.CmmiTemplateImp;
import utils.PathFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristian del Cerro
 */
public class CmmiFullProcessDocumentImp implements CmmiFullProcessDocument, PathFiles {

    CmmiTemplate cmmiTemplate;

    public CmmiFullProcessDocumentImp(CmmiTemplate cmmiTemplate) {
        this.cmmiTemplate = cmmiTemplate;
    }

    @Override
    public void start(File inputFile, int idStandard){
        ParsePDF parsePDF = new ParsePDF();
        parsePDF.pdfToText(inputFile, NAME_TXT_OUTPUT, TEMPORAL_DIRECTORY);

        ArrayList<String> textList = treatmentText();
        cmmiTemplate.runTemplate(textList,idStandard);
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
