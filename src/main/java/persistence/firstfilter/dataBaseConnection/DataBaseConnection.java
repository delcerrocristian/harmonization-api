package persistence.firstfilter.dataBaseConnection;

import java.sql.PreparedStatement;

/**
 * Created by Cristian del Cerro.
 */
public interface DataBaseConnection {

    void close();

    PreparedStatement preparedStatement(String sql);

    PreparedStatement preparedStatement(String sql, int option);
}
