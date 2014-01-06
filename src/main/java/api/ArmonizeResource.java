package api;


import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
}
