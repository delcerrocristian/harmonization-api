package api;


import com.google.common.base.Optional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.InputStream;
import java.util.ArrayList;

import static utils.UtilsFile.makeDirectory;
import static utils.UtilsFile.saveFileOnDirectory;


@Path("/armonize/api")
@Produces(MediaType.APPLICATION_JSON)

public class ArmonizeResource {

    final String TEMPORAL_DIRECTORY = "temp";
	
	public ArmonizeResource() {
        makeDirectory(TEMPORAL_DIRECTORY);
    }

    @POST
    public Response uploadFile(InputStream stream) throws Exception {
        if(stream!=null){
            saveFileOnDirectory(stream, TEMPORAL_DIRECTORY);
            return Response.ok().build(); //200
        }
        else{
             return Response.status(400).build();
        }
    }

}
