package pdfTrat;

import java.io.File;
import java.util.List;

/**
 * Created by Cristian del Cerro.
 */
public interface FullProcessDocument {

    void start(File inputFile, int idStandard, List<String> patterns);
}
