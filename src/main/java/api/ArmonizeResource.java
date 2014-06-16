package api;


import pdfTrat.FullProcessDocument;
import persistence.firstfilter.iso.model.Task;
import services.iso.IsoService;
import utils.PathFiles;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static utils.UtilsFile.saveFileOnDirectory;


@Path("/armonize/api")

public class ArmonizeResource implements PathFiles {

    private IsoService isoService;
    private FullProcessDocument fullProcessDocument;

    public ArmonizeResource(IsoService isoService, FullProcessDocument fullProcessDocument) {
        this.isoService = isoService;
        this.fullProcessDocument = fullProcessDocument;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFile(InputStream stream, @QueryParam("name") String name, @QueryParam("patterns") List<String> patterns) {
        if (stream == null || name == null || patterns.isEmpty()) {
            return Response.status(400).build();
        }

        File inputFile;
        try {
            inputFile = saveFileOnDirectory(stream, TEMPORAL_DIRECTORY);
        } catch (IOException e) {
            return Response.status(422).build();
        }
        int idStandard = isoService.createStandard(name);

        fullProcessDocument.start(inputFile, idStandard, patterns);

        return Response.ok(idStandard).build(); //200
    }

    @Path("/standard/response")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponse(@QueryParam("id") int id){
        ArrayList<Task> allTasks = isoService.readAllTaskByStandard(id);
        return Response.ok(allTasks).build();
    }

  /*  @Path("/standard")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setStandard(Standard standard) throws SQLException {
        StandardDao standardDao = new StandardDaoImp();
        standardDao.create(standard);


        return Response.status(Response.Status.CREATED).build();
    } */

   /* @Path("/standard")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStandard(@QueryParam("id") int id) throws SQLException {
        StandardDao standardDao = new StandardDaoImp();
        Standard standard = standardDao.read(id);

        return Response.ok(standard).build();
    } */

 /*   @Path("/standard/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStandards() throws SQLException {
        ArrayList<Standard> allStandards = firstFilterService.readAllStandards();

        return Response.ok(allStandards).build();
    }  */

  /*  @Path("/standard")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStandard(@QueryParam("id") int id) throws SQLException {
        StandardDao standardDao = new StandardDaoImp();
        standardDao.delete(id);

        return Response.noContent().build();
    }  */

    /*@Path("/standard/main/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMainSentenceByStandard(@QueryParam("id") int id) throws SQLException {
        ArrayList<MainSentence> allMainSentencesByStandard = firstFilterService.readAllMainSentencesByStandard(id);

        return Response.ok(allMainSentencesByStandard).build();
    } */

    /*@Path("/standard/main/enum/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEnumSentenceByMainSentence(@QueryParam("id") int id) throws SQLException {
        ArrayList<EnumSentence> allEnumSentenceByMainSentence = firstFilterService.readAllEnumSentencesByMainSentence(id);

        return Response.ok(allEnumSentenceByMainSentence).build();
    }  */

    /*@Path("/standard/response")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponse(@QueryParam("id") int id) throws SQLException {
        ArrayList<ResponseMainSentence> responseMainSentenceArrayList = new ArrayList<ResponseMainSentence>();

        ArrayList<MainSentence> allMainSentenceByMainSentence = firstFilterService.readAllMainSentencesByStandard(id);

        for(MainSentence mainSentence: allMainSentenceByMainSentence){
            ResponseMainSentence responseMainSentence = new ResponseMainSentence(mainSentence);
            ArrayList<EnumSentence> allEnumSentenceByMainSentence = firstFilterService.readAllEnumSentencesByMainSentence(mainSentence.getId());

            responseMainSentence.setEnumSentences(allEnumSentenceByMainSentence);
            responseMainSentenceArrayList.add(responseMainSentence);
        }

        return Response.ok(responseMainSentenceArrayList).build();
    }  */

    /*private boolean correctTypeProcess(String typeProcess) {
        if(typeProcess != null) {
            if (typeProcess.equals("assisted") || typeProcess.equals("mixed") || typeProcess.equals("unassisted")) {
                return true;
            }
        }
        return false;
    }*/

}