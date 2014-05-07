package templates;

/**
 * Created by Cristian del Cerro.
 */

import config.FirstFilterConfiguration;
import services.FirstFilterService;
import utils.PathFiles;

import java.util.ArrayList;

import static utils.UtilsFile.makeDirectory;

public class IsoTemplate implements PathFiles, IsoTemplateInterface {

    FirstFilterService firstFilterService;

    public IsoTemplate() {
        FirstFilterConfiguration firstFilterConfiguration = new FirstFilterConfiguration();
        firstFilterService = firstFilterConfiguration.getFirstFilterService() ;
    }

    @Override
    public void runTemplate(ArrayList<String> listOfText, int idStandard) {
        AlgorithmByDefault algorithmByDefault = new AlgorithmByDefault(listOfText, idStandard,
                firstFilterService, "shall");
        algorithmByDefault.find();



    }



}