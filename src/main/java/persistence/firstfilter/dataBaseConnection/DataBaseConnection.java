package persistence.firstfilter.dataBaseConnection;

import java.sql.PreparedStatement;

/**
 * Created by evan on 16/06/14.
 */
public interface DataBaseConnection {

    void close();

    PreparedStatement preparedStatement(String sql);

    PreparedStatement preparedStatement(String sql, int option);
}
