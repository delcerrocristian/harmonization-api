package templates;

/**
 * Created by Cristian del Cerro.
 */

import utils.PathFiles;

import java.util.ArrayList;

import static utils.UtilsFile.makeDirectory;

public class IsoTemplate implements PathFiles, IsoTemplateInterface {


    public IsoTemplate(){
        makeDirectory(ISO_TEMPORAL_DIRECTORY);
    }

    public void runTemplate(ArrayList<String> listOfText)throws Exception{
        IsoCategory1 isoCategory1 = new IsoCategory1(listOfText);
        isoCategory1.find();

        IsoCategory4 isoCategory4 = new IsoCategory4(listOfText);
        isoCategory4.find();

        IsoCategory5 isoCategory5 = new IsoCategory5(listOfText);
        isoCategory5.find();

        IsoCategory6 isoCategory6 = new IsoCategory6(listOfText);
        isoCategory6.find();

        IsoCategory7 isoCategory7 = new IsoCategory7(listOfText);
        isoCategory7.find();

    }

}