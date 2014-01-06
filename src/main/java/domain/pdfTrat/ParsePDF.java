package domain.pdfTrat;

import java.io.*;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.util.*;

public class ParsePDF {
	public ParsePDF(){
		
	}
	
	public void pdftotext (String pdf, String txt){
		PDDocument pd;
		BufferedWriter wr;
		 try {
		         File input = new File(pdf);  // The PDF file from where you would like to extract
		         File output = new File(txt); // The text file where you are going to store the extracted data
		         pd = PDDocument.load(input);
		         System.out.println(pd.getNumberOfPages());
		         System.out.println(pd.isEncrypted());
		         pd.save("CopyOfInvoice.pdf"); // Creates a copy called "CopyOfInvoice.pdf"
		         PDFTextStripper stripper = new PDFTextStripper();
		         wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
		         stripper.writeText(pd, wr);
		         if (pd != null) {
		             pd.close();
		         }
		        // I use close() to flush the stream.
		        wr.close();
		 } catch (Exception e){
		         e.printStackTrace();
		 } 
	}
}
	
