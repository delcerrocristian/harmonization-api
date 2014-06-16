package persistence.firstfilter.iso.dao;

import persistence.firstfilter.dataBaseConnection.IsoDataBaseConnection;
import persistence.firstfilter.iso.model.Standard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public class StandardDaoImp implements StandardDao {

    @Override
    public int create(Standard standard) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        int id= -1; //If finally return -1 means something bad happened
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("insert into standard (name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, standard.getName());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if( resultSet.next() ) {
                id = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened creating standard");
        }
        finally{
            isoDataBaseConnection.close();
        }
        return id;
    }

    @Override
    public int create(String name) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        int id= -1; //If finally return -1 means something bad happened

        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("insert into standard (name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if( resultSet.next() ) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("SQLException happened creating standard");
        }
        finally{
            isoDataBaseConnection.close();
        }
        return id;
    }

    @Override
    public Standard read(int id) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        Standard standardFromDB = null;
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("select * from standard where id=?");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                resultSet.next();
                standardFromDB = new Standard();
                standardFromDB.setId(resultSet.getInt("id"));
                standardFromDB.setName(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened reading standard");
        }finally{
            isoDataBaseConnection.close();
        }
        return standardFromDB;
    }

    @Override
    public void update(Standard standard)  {
    }

    @Override
    public void delete(int id) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("delete from standard where id=?");
            preparedStatement.setInt(1,id);

            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("SQLException happened deleting standard");
        }finally{
            isoDataBaseConnection.close();
        }
    }

    @Override
    public ArrayList<Standard> readAll() {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        Standard standardFromDB;
        ArrayList<Standard> allStandards = new ArrayList<>();
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("select * from standard");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet != null){
                resultSet.next();

                standardFromDB = new Standard(resultSet.getInt("id"), resultSet.getString("name"));
                allStandards.add(standardFromDB);
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened reading all standard");
        }finally{
            isoDataBaseConnection.close();
        }
        return allStandards;
    }
}
