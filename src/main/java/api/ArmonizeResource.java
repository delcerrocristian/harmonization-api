package api;


import pdfTrat.FullProcessDocumentImp;
import utils.PathFiles;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.File;
import java.io.InputStream;

import static utils.UtilsFile.saveFileOnDirectory;


@Path("/armonize/api")
@Produces(MediaType.APPLICATION_JSON)

public class ArmonizeResource implements PathFiles {


	public ArmonizeResource() {

    }

    @POST
    public Response uploadFile(InputStream stream) throws Exception {
        if(stream!=null){
            File inputFile = saveFileOnDirectory(stream, TEMPORAL_DIRECTORY);

            FullProcessDocumentImp fullProcessDocumentImp = new FullProcessDocumentImp();
            fullProcessDocumentImp.start(inputFile);

            return Response.ok().build(); //200
        }
        else{
             return Response.status(400).build();
        }
    }



}
