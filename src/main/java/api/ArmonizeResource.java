package api;


import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;
import org.apache.pdfbox.io.IOUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;


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
    public Response uploadFile(final InputStream stream) throws Exception {

        byte[] data = IOUtils.toByteArray(stream);
        File temporal = new File("/tmp/tempFile.pdf" );
        org.apache.commons.io.FileUtils.writeByteArrayToFile(temporal, data);

        return Response.status(200).build();
    }
}
