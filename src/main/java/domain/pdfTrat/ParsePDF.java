package domain.pdfTrat;

import java.io.*;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.util.*;

public class ParsePDF {
	public ParsePDF(){
		
	}

    final String NAME_TXT_OUTPUT = "tempTxtOutput.txt";

	public void pdfToText (File pdfInput, String directoryOutputPath){
		PDDocument pdDocument;
		BufferedWriter bufferedWriter;
		 try {
                 String outputPath = makeOutputPath(directoryOutputPath);
		         File output = new File(makeOutputPath(outputPath));

		         pdDocument = PDDocument.load(pdfInput);

		         PDFTextStripper pdfTextStripper = new PDFTextStripper();
		         bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
		         pdfTextStripper.writeText(pdDocument, bufferedWriter);

		         pdDocument.close();
		         bufferedWriter.close();

		 } catch (Exception e){
		         e.printStackTrace();
		 }
	}

    private String makeOutputPath(String directoryOutputPath){
        return directoryOutputPath+"/"+NAME_TXT_OUTPUT;
    }
}
	
