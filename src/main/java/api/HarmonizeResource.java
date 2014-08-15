package api;


import pdfTrat.CmmiFullProcessDocument;
import pdfTrat.FullProcessDocument;
import persistence.firstfilter.model.ResponseStatsCmmi;
import persistence.firstfilter.model.ResponseStatsIso;
import persistence.firstfilter.cmmi.model.*;
import persistence.firstfilter.cmmi.model.Process;
import persistence.firstfilter.cmmi.model.Standard;
import persistence.firstfilter.iso.model.*;
import persistence.firstfilter.model.ResponseStandards;
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
        if (!checkParamsUploadFile(stream, type, name)) {
            return Response.status(400).build();
        }

        File inputFile;
        try {
            inputFile = saveFileOnDirectory(stream, TEMPORAL_DIRECTORY);
        } catch (IOException e) {
            return Response.status(422).build();
        }

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
                !name.isEmpty()  &&
                !type.isEmpty() &&
                (type.equals("iso") || type.equals("cmmi"));
    }

    @Path("/standard/response")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponse(@QueryParam("id") int id){
        ArrayList<Task> allTasks = isoService.readAllTaskByStandard(id);
        return Response.ok(allTasks).build();
    }

    @Path("standard/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStandards() {
        ArrayList<Standard> standardsCmmi = cmmiService.readAllStandards();
        ArrayList<persistence.firstfilter.iso.model.Standard> standardsIso = isoService.readAllStandards();

        ArrayList<ResponseStandards> responseStandards = new ArrayList<>();

        for(Standard cmmiStandard : standardsCmmi) {
            responseStandards.add(new ResponseStandards(cmmiStandard.getId(), cmmiStandard.getName(), "cmmi"));
        }
        for(persistence.firstfilter.iso.model.Standard isoStandard : standardsIso) {
            responseStandards.add(new ResponseStandards(isoStandard.getId(), isoStandard.getName(), "iso"));
        }

        return Response.ok(responseStandards).build();
    }


    @Path("/cmmi/process")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCmmiProcess(persistence.firstfilter.cmmi.model.Process process) {
        if(cmmiService.readStandard(process.getStandard()) != null) {
            int id = cmmiService.createProcess(process);
            return Response.ok(id).build();
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

    @Path("/cmmi/allprocess")
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
            int id = cmmiService.createSpecificGoal(specificGoal);
            return Response.ok(id).build();
        }
        return Response.status(404).build();
    }

    @Path("/cmmi/allspecificgoal")
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
            int id = cmmiService.createSpecificPractice(specificPractice);
            return Response.ok(id).build();
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

    @Path("/cmmi/allspecificpractice")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCmmiAllSpecificPractice(@QueryParam("standard") int id) {
        ArrayList<SpecificPractice> listOfSpecificPractice = cmmiService.readAllSpecificPracticeByStandard(id);
        if(!listOfSpecificPractice.isEmpty()) {
            return Response.ok(listOfSpecificPractice).build();
        }
        return Response.status(404).build();
    }

    @Path("/cmmi/workproduct")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCmmiWorkProduct(WorkProduct workProduct) {
        if(cmmiService.readSpecificPractice(workProduct.getSpecificPractice()) != null) {
            int id = cmmiService.createWorkProduct(workProduct);
            return Response.ok(id).build();
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

    @Path("/cmmi/allworkproduct")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCmmiAllWorkProduct(@QueryParam("standard") int id) {
        ArrayList<WorkProduct> listOfWorkProduct = cmmiService.readAllWorkProductByStandard(id);
        if(!listOfWorkProduct.isEmpty()) {
            return Response.ok(listOfWorkProduct).build();
        }
        return Response.status(404).build();
    }

    @Path("cmmi/stats")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCmmiStats(@QueryParam("id") int id) {
        int countProcess = cmmiService.readCountProcessByStandard(id);
        int countSpecificGoal = cmmiService.readCountSpecificGoalByStandard(id);
        int countSpecificPractice = cmmiService.readCountSpecificPracticeByStandard(id);
        int countWorkProduct = cmmiService.readCountWorkProductByStandard(id);

        ResponseStatsCmmi responseStatsCmmi = new ResponseStatsCmmi(countProcess,countSpecificGoal,countSpecificPractice,
                countWorkProduct);

        return Response.ok(responseStatsCmmi).build();
    }

    //----------------------------------------------ISO-------------------------------------------

    @Path("/iso/process")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addIsoProcess(persistence.firstfilter.iso.model.Process process) {
        if(isoService.readStandard(process.getStandard()) != null) {
            int id =isoService.addProcess(process);
            return Response.ok(id).build();
        }
        return Response.status(404).build();
    }

    @Path("/iso/process")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIsoProcess(@QueryParam("id") int id) {
        persistence.firstfilter.iso.model.Process process = isoService.readProcess(id);
        if(process != null) {
            return Response.ok(process).build();
        }
        return Response.status(404).build();
    }

    @Path("/iso/process")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateIsoProcess(persistence.firstfilter.iso.model.Process process) {
        if(isoService.readStandard(process.getStandard()) != null) {
            isoService.updateProcess(process);
            return Response.noContent().build();
        }
        return Response.status(404).build();
    }

    @Path("/iso/process")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteIsoProcess(@QueryParam("id") int id) {
        isoService.deleteProcess(id);
        return Response.noContent().build();
    }

    @Path("/iso/allprocess")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllIsoProcess(@QueryParam("standard") int id) {
        ArrayList<persistence.firstfilter.iso.model.Process> listOfProcess = isoService.readAllProcessByStandard(id);
        if(listOfProcess != null) {
            return Response.ok(listOfProcess).build();
        }
        return Response.status(404).build();
    }

    @Path("/iso/activity")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addIsoActivity(Activity activity) {
        if(isoService.readProcess(activity.getProcess()) != null) {
            int id =isoService.addActivity(activity);
            return Response.ok(id).build();
        }
        return Response.status(404).build();
    }

    @Path("/iso/activity")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIsoActivity(@QueryParam("id") int id) {
        Activity activity = isoService.readActivity(id);
        if(activity != null) {
            return Response.ok(activity).build();
        }
        return Response.status(404).build();
    }

    @Path("/iso/activity")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateIsoActivity(Activity activity) {
        if(isoService.readProcess(activity.getProcess()) != null) {
            isoService.updateActivity(activity);
            return Response.noContent().build();
        }
        return Response.status(404).build();
    }

    @Path("/iso/activity")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteIsoActivity(@QueryParam("id") int id) {
        isoService.deleteActivity(id);
        return Response.noContent().build();
    }

    @Path("/iso/allactivity")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllIsoActivity(@QueryParam("standard") int id) {
        ArrayList<Activity> listOfActivity = isoService.readAllActivityByStandard(id);
        if(listOfActivity != null) {
            return Response.ok(listOfActivity).build();
        }
        return Response.status(404).build();
    }

    @Path("/iso/task")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addIsoTask(Task task) {
        if(isoService.readProcess(task.getProcess()) != null) {
            int id = isoService.addTask(task);
            return Response.ok(id).build();
        }
        return Response.status(404).build();
    }

    @Path("/iso/task")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIsoTask(@QueryParam("id") int id) {
        Task task = isoService.readTask(id);
        if(task != null) {
            return Response.ok(task).build();
        }
        return Response.status(404).build();
    }

    @Path("/iso/task")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateIsoTask(Task task) {
        if(isoService.readProcess(task.getProcess()) != null) {
            isoService.updateTask(task);
            return Response.noContent().build();
        }
        return Response.status(404).build();
    }

    @Path("/iso/task")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteIsoTask(@QueryParam("id") int id) {
        isoService.deleteTask(id);
        return Response.noContent().build();
    }

    @Path("/iso/alltask")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllIsoTask(@QueryParam("standard") int id) {
        ArrayList<Task> listOfTask = isoService.readAllTaskByStandard(id);
        if(listOfTask != null) {
            return Response.ok(listOfTask).build();
        }
        return Response.status(404).build();
    }

    @Path("iso/stats")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIsoStats(@QueryParam("id") int id) {
        int countProcess = isoService.readCountProcessByStandard(id);
        int countActivity = isoService.readCountActivityByStandard(id);
        int countTask = isoService.readCountTaskByStandard(id);

        ResponseStatsIso responseStatsIso = new ResponseStatsIso(countProcess,countActivity,countTask);

        return Response.ok(responseStatsIso).build();
    }

}