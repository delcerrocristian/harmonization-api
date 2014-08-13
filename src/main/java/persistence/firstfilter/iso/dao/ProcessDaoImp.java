package persistence.firstfilter.iso.dao;

import persistence.firstfilter.dataBaseConnection.CmmiDataBaseConnection;
import persistence.firstfilter.dataBaseConnection.DataBaseConnection;
import persistence.firstfilter.dataBaseConnection.IsoDataBaseConnection;
import persistence.firstfilter.iso.model.Process;

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
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        int id = -1;

        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
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
            isoDataBaseConnection.close();
        }
        return id;
    }

    @Override
    public Process read(int id) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        Process processFromDB = null;
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
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
            isoDataBaseConnection.close();
        }
        return processFromDB;
    }

    @Override
    public int readByNameAndStandard(String name, int idStandard) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        int idProcess = -1;
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("select * from process where name=? and standard=?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, idStandard);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                idProcess = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select process by Name and Standard");
        }finally{
            isoDataBaseConnection.close();
        }
        return idProcess;
    }

    @Override
    public void update(Process process) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("update process set " +
                            "name=?, standard=? " +
                            "where id=?");

            preparedStatement.setString(1, process.getName());
            preparedStatement.setInt(2, process.getStandard());
            preparedStatement.setInt(3, process.getId());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println("SQLException happened executing updating process iso");
        }finally{
            dataBaseConnection.close();
        }
    }

    @Override
    public void delete(int id) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("delete from process where id=?");
            preparedStatement.setInt(1,id);

            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("SQLException happened executing delete to process");
        }finally{
            isoDataBaseConnection.close();
        }
    }

    @Override
    public ArrayList<Process> readAllByStandard(int id) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        Process processFromDB;
        ArrayList<Process> allProcess = new ArrayList<>();
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("select * from process where standard="+id);

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
            isoDataBaseConnection.close();
        }
        return allProcess;
    }


}
