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
    ArrayList<String> msg;

	
	public ArmonizeResource() {
        msg = new ArrayList<String>();
    }
	
	@GET
    public ArrayList<String> sayHello(@QueryParam("name") Optional<String> name) {
        msg.add(name.or("anonymous"));
        return msg;
    }

    @POST
    public Response uploadFile(InputStream stream) throws Exception {
        if(stream!=null){
            makeDirectory("temp");
            saveFileOnDirectory(stream, "temp");
            return Response.status(200).build();
        }
        else{
            return Response.status(400).build();
        }
    }

}
