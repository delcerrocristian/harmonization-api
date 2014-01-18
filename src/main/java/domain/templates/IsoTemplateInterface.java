package domain.templates;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public interface IsoTemplateInterface {

    char alphabet[] = {'a','b','c','d','e','f','g','h','i','j','k'
            ,'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    void runTemplate(ArrayList<String> listOfText)throws Exception;
}
