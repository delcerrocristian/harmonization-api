package persistence.firstfilter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Cristian del Cerro.
 */
public class DataBaseConnection {
    private Connection dataBase;

    public DataBaseConnection() throws SQLException {

        String url = "jdbc:mysql://localhost/";
        String dataBaseName = "FirstFilter";
        String userName = "root";
        String password = "root";

        this.dataBase = DriverManager.getConnection(url + dataBaseName, userName, password);
    }

    public void close() throws SQLException{
        synchronized (this){
            dataBase.close();
        }
    }

    public PreparedStatement preparedStatement(String sql) throws SQLException {
        return this.dataBase.prepareStatement(sql);
    }

    public PreparedStatement preparedStatement(String sql, int option) throws SQLException{
        return this.dataBase.prepareStatement(sql,option);
    }
}
