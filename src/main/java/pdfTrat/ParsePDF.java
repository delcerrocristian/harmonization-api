package pdfTrat;

import java.io.*;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.util.*;

/**
 * Created by Cristian del Cerro.
 */

public class ParsePDF {
	public ParsePDF(){
		
	}

	public void pdfToText (File pdfInput,String txtOutput, String directoryOutputPath){
		PDDocument pdDocument;
		BufferedWriter bufferedWriter;
		 try {
                 String outputPath =  directoryOutputPath+"/"+txtOutput;
		         File output = new File(outputPath);

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

}
	
