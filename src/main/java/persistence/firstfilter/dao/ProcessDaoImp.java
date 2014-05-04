package persistence.firstfilter.dao;

import persistence.firstfilter.DataBaseConnection;
import persistence.firstfilter.model.*;
import persistence.firstfilter.model.Process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by spukyn on 3/05/14.
 */
public class ProcessDaoImp implements ProcessDao {


    @Override
    public int create(Process process) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        int id = -1;

        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("insert into process (name, standard) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, process.getName());
            preparedStatement.setInt(2, process.getStandard());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        }
        catch (SQLException sqlException) {
            System.out.println("SQLException happened executing create to process");
        }
        finally {
            dataBaseConnection.close();
        }
        return id;
    }

    @Override
    public Process read(int id) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        Process processFromDB = null;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select * from process where id=?");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                resultSet.next();
                processFromDB = new Process();
                processFromDB.setId(resultSet.getInt("id"));
                processFromDB.setName(resultSet.getString("name"));
                processFromDB.setStandard(resultSet.getInt("standard"));
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select to process");

        }finally{
            dataBaseConnection.close();
        }
        return processFromDB;
    }

    @Override
    public void update(Process process) {

    }

    @Override
    public void delete(int id) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("delete from process where id=?");
            preparedStatement.setInt(1,id);

            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("SQLException happened executing delete to process");
        }finally{
            dataBaseConnection.close();
        }
    }

    @Override
    public ArrayList<Process> readAll() {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        Process processFromDB;
        ArrayList<Process> allProcess = new ArrayList<>();
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select * from process");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet != null){
                resultSet.next();

                processFromDB = new Process(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("standard"));
                allProcess.add(processFromDB);
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select all process");
        }finally{
            dataBaseConnection.close();
        }
        return allProcess;
    }
}
