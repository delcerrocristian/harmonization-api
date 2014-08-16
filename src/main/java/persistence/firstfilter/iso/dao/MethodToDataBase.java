package persistence.firstfilter.iso.dao;

import java.sql.SQLException;

/**
 * Created by Cristian del Cerro.
 */
public interface MethodToDataBase {

    void allMainSentenceAsProcessed(int standard) throws SQLException;
}
