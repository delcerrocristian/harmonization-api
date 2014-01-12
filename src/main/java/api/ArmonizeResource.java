package api;


import com.google.common.base.Optional;
import domain.pdfTrat.ParsePDF;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import static utils.UtilsFile.makeDirectory;
import static utils.UtilsFile.saveFileOnDirectory;


@Path("/armonize/api")
@Produces(MediaType.APPLICATION_JSON)

public class ArmonizeResource {

    final String TEMPORAL_DIRECTORY = "temp";
    final String NAME_TXT_OUTPUT = "tempTxtOutput.txt";

    ParsePDF parsePDF;
	
	public ArmonizeResource() {
        makeDirectory(TEMPORAL_DIRECTORY);
        parsePDF = new ParsePDF();
    }

    @POST
    public Response uploadFile(InputStream stream) throws Exception {
        if(stream!=null){
            File inputFile = saveFileOnDirectory(stream, TEMPORAL_DIRECTORY);
            parsePDF.pdfToText(inputFile,NAME_TXT_OUTPUT, TEMPORAL_DIRECTORY);

            return Response.ok().build(); //200
        }
        else{
             return Response.status(400).build();
        }
    }



}
