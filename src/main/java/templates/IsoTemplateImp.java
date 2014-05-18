package templates;

/**
 * Created by Cristian del Cerro.
 */

import config.FirstFilterConfiguration;
import services.FirstFilterService;
import utils.PathFiles;

import java.util.ArrayList;
import java.util.List;

public class IsoTemplateImp implements PathFiles, IsoTemplate {

    FirstFilterService firstFilterService;

    public IsoTemplateImp(FirstFilterService firstFilterService) {
        this.firstFilterService = firstFilterService;
    }

    @Override
    public void runTemplate(ArrayList<String> listOfText, int idStandard, List<String> patterns) {
        AlgorithmByDefault algorithmByDefault = new AlgorithmByDefault(listOfText, idStandard,
                firstFilterService);
        for(String pattern : patterns){
            algorithmByDefault.find(pattern);
        }




    }



}