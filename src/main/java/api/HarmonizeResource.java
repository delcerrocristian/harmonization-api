package api;


import pdfTrat.CmmiFullProcessDocument;
import pdfTrat.FullProcessDocument;
import persistence.firstfilter.cmmi.model.*;
import persistence.firstfilter.cmmi.model.Process;
import persistence.firstfilter.iso.model.Task;
import services.cmmi.CmmiService;
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

public class HarmonizeResource implements PathFiles {

    private IsoService isoService;
    private FullProcessDocument isoFullProcessDocument;
    private CmmiService cmmiService;
    private CmmiFullProcessDocument cmmiFullProcessDocument;

    public HarmonizeResource(IsoService isoService, FullProcessDocument isoFullProcessDocument,
                             CmmiService cmmiService, CmmiFullProcessDocument cmmiFullProcessDocument) {
        this.isoService = isoService;
        this.isoFullProcessDocument = isoFullProcessDocument;
        this.cmmiService = cmmiService;
        this.cmmiFullProcessDocument = cmmiFullProcessDocument;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFile(InputStream stream, @QueryParam("name") String name, @QueryParam("type") String type,
                               @QueryParam("patterns") List<String> patterns) {
      /*  if (!checkParamsUploadFile(stream, type, name)) {
            return Response.status(400).build();
        }*/

        File inputFile;
        try {
            inputFile = saveFileOnDirectory(stream, TEMPORAL_DIRECTORY);
        } catch (IOException e) {
            return Response.status(422).build();
        }

        type="cmmi";
        patterns.add("shall");

        int idStandard;
        if(type.equals("iso")) {
            idStandard = isoService.createStandard(name);
            isoFullProcessDocument.start(inputFile,idStandard, patterns);
        }
        else {
            idStandard = cmmiService.createStandard(name);
            cmmiFullProcessDocument.start(inputFile,idStandard);
        }

        return Response.ok(idStandard).build();
    }

    private boolean checkParamsUploadFile(InputStream stream, String type, String name){
        return stream != null &&
                name != null  &&
                type != null &&
                (type.equals("iso") || type.equals("cmmi"));
    }

    @Path("/standard/response")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponse(@QueryParam("id") int id){
        ArrayList<Task> allTasks = isoService.readAllTaskByStandard(id);
        return Response.ok(allTasks).build();
    }

    @Path("/cmmi/process")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCmmiProcess(persistence.firstfilter.cmmi.model.Process process) {
        if(cmmiService.readStandard(process.getStandard()) != null) {
            cmmiService.createProcess(process);
            return Response.ok().build();
        }
        return Response.status(404).build();
    }

    @Path("/cmmi/process")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCmmiProcess(@QueryParam("id") int id) {
        Process process = cmmiService.readProcess(id);
        if(process != null) {
            return Response.ok(process).build();
        }
        return Response.status(404).build();
    }

    @Path("/cmmi/process")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCmmiProcess(Process process) {
        if(cmmiService.readStandard(process.getStandard()) != null) {
            cmmiService.updateProcess(process);
            return Response.noContent().build();
        }
        return Response.status(404).build();
    }

    @Path("/cmmi/process")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCmmiProcess(@QueryParam("id") int id) {
        cmmiService.deleteProcess(id);
        return Response.noContent().build();
    }

    @Path("/cmmi/process")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCmmiProcessByStandard(@QueryParam("standard") int standard) {
        ArrayList<Process> listOfProcess = cmmiService.readAllProcessByStandard(standard);
        if(!listOfProcess.isEmpty()) {
            return Response.ok(listOfProcess).build();
        }
        return Response.status(404).build();
    }

    @Path("/cmmi/specificgoal")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCmmiSpecificGoal(SpecificGoal specificGoal) {
        if(cmmiService.readProcess(specificGoal.getProcess()) != null) {
            cmmiService.createSpecificGoal(specificGoal);
            return Response.ok().build();
        }
        return Response.status(404).build();
    }

    @Path("/cmmi/specificgoal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCmmiSpecificGoalByStandard(@QueryParam("standard") int standard) {
        ArrayList<SpecificGoal> listOfSpecificGoal = cmmiService.readAllSpecificGoalByStandard(standard);
        if(!listOfSpecificGoal.isEmpty()) {
            return Response.ok(listOfSpecificGoal).build();
        }
        return Response.status(404).build();
    }

    @Path("/cmmi/specificgoal")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCmmiSpecificGoal(SpecificGoal specificGoal) {
        if(cmmiService.readProcess(specificGoal.getProcess()) != null) {
            cmmiService.updateSpecificGoal(specificGoal);
            return Response.noContent().build();
        }
        return Response.status(404).build();
    }

    @Path("/cmmi/specificgoal")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCmmiSpecificGoal(@QueryParam("id") int id) {
        cmmiService.deleteSpecificGoal(id);
        return Response.noContent().build();
    }

    @Path("/cmmi/specificgoal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCmmiSpecificGoal(@QueryParam("id") int id) {
        SpecificGoal specificGoal = cmmiService.readSpecificGoal(id);
        if(specificGoal != null) {
            return Response.ok(specificGoal).build();
        }
        return Response.status(404).build();
    }


    @Path("/cmmi/specificpractice")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCmmiSpecificPractice(SpecificPractice specificPractice) {
        if(cmmiService.readSpecificGoal(specificPractice.getSpecificGoal()) != null) {
            cmmiService.createSpecificPractice(specificPractice);
            return Response.ok().build();
        }
        return Response.status(404).build();
    }

    @Path("/cmmi/specificpractice")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCmmiSpecificPractice(@QueryParam("id") int id) {
        SpecificPractice specificPractice = cmmiService.readSpecificPractice(id);
        if(specificPractice != null) {
            return Response.ok(specificPractice).build();
        }
        return Response.status(404).build();
    }

    @Path("/cmmi/specificpractice")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCmmiSpecificPractice(SpecificPractice specificPractice) {
        if(cmmiService.readSpecificGoal(specificPractice.getSpecificGoal()) != null) {
            cmmiService.updateSpecificPractice(specificPractice);
            return Response.noContent().build();
        }
        return Response.status(404).build();
    }

    @Path("/cmmi/specificpractice")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCmmiSpecificPractice(@QueryParam("id") int id) {
        cmmiService.deleteSpecificPractice(id);
        return Response.noContent().build();
    }

    @Path("/cmmi/workproduct")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCmmiWorkProduct(WorkProduct workProduct) {
        if(cmmiService.readSpecificPractice(workProduct.getSpecificPractice()) != null) {
            cmmiService.createWorkProduct(workProduct);
            return Response.ok().build();
        }
        return Response.status(404).build();
    }

    @Path("/cmmi/workproduct")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCmmiWorkProduct(@QueryParam("id") int id) {
        WorkProduct workProduct = cmmiService.readWorkProduct(id);
        if(workProduct != null) {
            return Response.ok(workProduct).build();
        }
        return Response.status(404).build();
    }

    @Path("/cmmi/workproduct")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCmmiWorkProduct(WorkProduct workProduct) {
        if(cmmiService.readSpecificPractice(workProduct.getSpecificPractice()) != null) {
            cmmiService.updateWorkProduct(workProduct);
            return Response.noContent().build();
        }
        return Response.status(404).build();
    }

    @Path("/cmmi/workproduct")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCmmiWorkProduct(@QueryParam("id") int id) {
        cmmiService.deleteWorkProduct(id);
        return Response.noContent().build();
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