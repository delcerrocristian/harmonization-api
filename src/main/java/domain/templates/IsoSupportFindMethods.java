package domain.templates;

import java.util.regex.Pattern;

/**
 * Created by Cristian del Cerro.
 */
public class IsoSupportFindMethods {

    public IsoSupportFindMethods(){

    }

    public boolean existEnumeration(String text){
        String patron = "^(a[)]|[-]).*";
        Pattern pat = Pattern.compile(patron);

        return pat.matcher(text).matches();

    }
}
