package persistence.firstfilter.dao;

import persistence.firstfilter.Broker;
import persistence.firstfilter.DataBaseConnection;
import persistence.firstfilter.NotFreeConnectionsException;
import persistence.firstfilter.model.Standard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public class StandardDaoImp implements StandardDao {

    @Override
    public void create(Standard standard) throws SQLException {
        DataBaseConnection dataBaseConnection = null;
        PreparedStatement preparedStatement;
        try {
            dataBaseConnection = Broker.get().getDataBase();
            preparedStatement = dataBaseConnection.preparedStatement
                    ("insert into standard (name) VALUES (?)");
            preparedStatement.setString(1, standard.getName());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        } catch (NotFreeConnectionsException e) {
        }
        finally{
            dataBaseConnection.close();
        }

    }

    @Override
    public Standard read(int id) throws SQLException {
        DataBaseConnection dataBaseConnection = null;
        PreparedStatement preparedStatement;
        Standard standardFromDB = null;
        try {
            dataBaseConnection = Broker.get().getDataBase();
            preparedStatement = dataBaseConnection.preparedStatement
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
        } catch (NotFreeConnectionsException e) {
        }finally{
            dataBaseConnection.close();
        }
        return standardFromDB;
    }

    @Override
    public void update(Standard standard) throws SQLException {
    }

    @Override
    public void delete(int id) throws SQLException {
        DataBaseConnection dataBaseConnection = null;
        PreparedStatement preparedStatement;
        try {
            dataBaseConnection = Broker.get().getDataBase();
            preparedStatement = dataBaseConnection.preparedStatement
                    ("delete from standard where id=?");
            preparedStatement.setInt(1,id);

            preparedStatement.execute();

        } catch (SQLException e) {
        } catch (NotFreeConnectionsException e) {
        }finally{
            dataBaseConnection.close();
        }
    }

    @Override
    public ArrayList<Standard> readAll() throws SQLException {
        DataBaseConnection dataBaseConnection = null;
        PreparedStatement preparedStatement;
        Standard standardFromDB;
        ArrayList<Standard> allStandards = new ArrayList<>();
        try {
            dataBaseConnection = Broker.get().getDataBase();
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select * from standard");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet != null){
                resultSet.next();

                standardFromDB = new Standard(resultSet.getInt("id"), resultSet.getString("name"));
                allStandards.add(standardFromDB);
            }

        } catch (SQLException e) {
        } catch (NotFreeConnectionsException e) {
        }finally{
            dataBaseConnection.close();
        }
        return allStandards;
    }
}
