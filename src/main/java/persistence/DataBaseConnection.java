package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Cristian del Cerro.
 */
public class DataBaseConnection {
    private Connection dataBase;
    private int id;

    public DataBaseConnection(int id) throws SQLException {

        String url = "jdbc:mysql://localhost/";
        String dataBaseName = "FirstFilter";
        String userName = "root";
        String password = "root";

        this.id=id;
        this.dataBase = DriverManager.getConnection(url + dataBaseName, userName, password);
    }

    public void close() throws SQLException{
        synchronized (this){
            dataBase.close();
            Broker.get().liberate(this);
        }
    }

    public int getId() {
        return id;
    }

    public PreparedStatement preparedStatement(String sql) throws SQLException {
        return this.dataBase.prepareStatement(sql);
    }
}
