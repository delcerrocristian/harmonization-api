package utils;

import org.apache.pdfbox.io.IOUtils;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Cristian del Cerro.
 */
public class UtilsFile {

    static public void makeDirectory(String path){
        File directory = new File(path);
        directory.mkdir();
    }

    static public File saveFileOnDirectory(InputStream stream, String pathDirectory) throws Exception{
        byte[] data = IOUtils.toByteArray(stream);
        File temporal = new File(pathDirectory+"/tempFile.pdf" );
        org.apache.commons.io.FileUtils.writeByteArrayToFile(temporal, data);

        return temporal;
    }

}
