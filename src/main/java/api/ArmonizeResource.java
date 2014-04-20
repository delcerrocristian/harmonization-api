package api;


import pdfTrat.FullProcessDocumentImp;
import persistence.firstfilter.dao.StandardDao;
import persistence.firstfilter.dao.StandardDaoImp;
import persistence.firstfilter.model.EnumSentence;
import persistence.firstfilter.model.MainSentence;
import persistence.firstfilter.model.ResponseMainSentence;
import persistence.firstfilter.model.Standard;
import services.FirstFilterService;
import utils.PathFiles;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.File;
import java.io.InputStream;
import java.security.PublicKey;
import java.sql.SQLException;
import java.util.ArrayList;

import static utils.UtilsFile.saveFileOnDirectory;


@Path("/armonize/api")

public class ArmonizeResource implements PathFiles {

    private FirstFilterService firstFilterService;

    public ArmonizeResource(FirstFilterService firstFilterService) {
        this.firstFilterService = firstFilterService;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response uploadFile(InputStream stream, @QueryParam("name") String name) throws Exception {
        if (stream != null && name != null) {
            File inputFile = saveFileOnDirectory(stream, TEMPORAL_DIRECTORY);

            int idStandard = firstFilterService.createStandard(name);

            FullProcessDocumentImp fullProcessDocumentImp = new FullProcessDocumentImp();
            fullProcessDocumentImp.start(inputFile, idStandard);

            return Response.ok(idStandard).build(); //200
        } else {
            return Response.status(400).build();
        }
    }

    @Path("/standard")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setStandard(Standard standard) throws SQLException {
        StandardDao standardDao = new StandardDaoImp();
        standardDao.create(standard);


        return Response.status(Response.Status.CREATED).build();
    }

    @Path("/standard")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStandard(@QueryParam("id") int id) throws SQLException {
        StandardDao standardDao = new StandardDaoImp();
        Standard standard = standardDao.read(id);

        return Response.ok(standard).build();
    }

    @Path("/standard/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStandards() throws SQLException {
        ArrayList<Standard> allStandards = firstFilterService.readAllStandards();

        return Response.ok(allStandards).build();
    }

    @Path("/standard")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStandard(@QueryParam("id") int id) throws SQLException {
        StandardDao standardDao = new StandardDaoImp();
        standardDao.delete(id);

        return Response.noContent().build();
    }

    @Path("/standard/main/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMainSentenceByStandard(@QueryParam("id") int id) throws SQLException {
        ArrayList<MainSentence> allMainSentencesByStandard = firstFilterService.readAllMainSentencesByStandard(id, false);

        return Response.ok(allMainSentencesByStandard).build();
    }

    @Path("/standard/main/enum/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEnumSentenceByMainSentence(@QueryParam("id") int id) throws SQLException {
        ArrayList<EnumSentence> allEnumSentenceByMainSentence = firstFilterService.readAllEnumSentencesByMainSentence(id, false);

        return Response.ok(allEnumSentenceByMainSentence).build();
    }

    @Path("/standard/response")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponse(@QueryParam("id") int id) throws SQLException {
        ArrayList<ResponseMainSentence> responseMainSentenceArrayList = new ArrayList<ResponseMainSentence>();

        ArrayList<MainSentence> allMainSentenceByMainSentence = firstFilterService.readAllMainSentencesByStandard(id,false);

        for(MainSentence mainSentence: allMainSentenceByMainSentence){
            ResponseMainSentence responseMainSentence = new ResponseMainSentence(mainSentence);
            ArrayList<EnumSentence> allEnumSentenceByMainSentence = firstFilterService.readAllEnumSentencesByMainSentence(mainSentence.getId(), false);

            responseMainSentence.setEnumSentences(allEnumSentenceByMainSentence);
            responseMainSentenceArrayList.add(responseMainSentence);
        }

        return Response.ok(responseMainSentenceArrayList).build();
    }

    @Path("/standard/main/process")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setProcessingMain(MainSentence mainSentence) throws SQLException {
        int idMain = firstFilterService.addMainSentence(mainSentence, true);

        return Response.status(201).entity(idMain).build();
    }

    @Path("standard/main/enum/process")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setProcessingEnum(EnumSentence enumSentence) throws SQLException {
        int idEnum = firstFilterService.addEnumSentence(enumSentence, true);

        return Response.status(201).entity(idEnum).build();
    }
}
