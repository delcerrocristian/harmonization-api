package persistence.firstfilter.dataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Cristian del Cerro.
 */
public class IsoDataBaseConnection implements DataBaseConnection{
    private Connection dataBase;

    public IsoDataBaseConnection() {

        try {
            String url = "jdbc:mysql://localhost/";
            String dataBaseName = "FirstFilter4.0";
            String userName = "root";
            String password = "root";

            this.dataBase = DriverManager.getConnection(url + dataBaseName, userName, password);
        }
        catch (SQLException sqlException) {
            System.out.println("SQLException happened creating DB");
        }
    }

    @Override
    public void close(){
        try {
            synchronized (this) {
                dataBase.close();
            }
        }
        catch (SQLException sqlException) {
            System.out.println("SQLException happened closing DB");
        }
    }

    @Override
    public PreparedStatement preparedStatement(String sql){
        try {
            return this.dataBase.prepareStatement(sql);
        }
        catch (SQLException sqlException) {
            System.out.println("SQLException happened preparing statement");
        }
        return null;
    }

    @Override
    public PreparedStatement preparedStatement(String sql, int option){
        try {
            return this.dataBase.prepareStatement(sql, option);

        }
        catch (SQLException sqlException) {
            System.out.println("SQLException happened preparing statement");
        }
        return null;
    }
}
