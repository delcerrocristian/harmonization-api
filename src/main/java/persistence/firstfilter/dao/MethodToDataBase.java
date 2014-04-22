package persistence.firstfilter.dao;

import java.sql.SQLException;

/**
 * Created by spukyn on 22/04/14.
 */
public interface MethodToDataBase {

    public void insertProcessingMainSentence(int id) throws SQLException;
}
