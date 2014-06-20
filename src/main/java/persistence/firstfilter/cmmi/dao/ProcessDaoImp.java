package persistence.firstfilter.cmmi.dao;

import persistence.firstfilter.cmmi.model.*;
import persistence.firstfilter.cmmi.model.Process;
import persistence.firstfilter.dataBaseConnection.CmmiDataBaseConnection;
import persistence.firstfilter.dataBaseConnection.DataBaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro
 */
public class ProcessDaoImp implements ProcessDao{
    @Override
    public int create(persistence.firstfilter.cmmi.model.Process process) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        int id = -1;

        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("insert into process (name, area_category, maturity_level, purpose_statement, standard) " +
                            "VALUES (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, process.getName());
            preparedStatement.setString(2, process.getAreaCategory());
            preparedStatement.setString(3, process.getMaturityLevel());
            preparedStatement.setString(4, process.getPurposeStatement());
            preparedStatement.setInt(5, process.getStandard());

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
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
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
                processFromDB.setAreaCategory(resultSet.getString("area_category"));
                processFromDB.setMaturityLevel(resultSet.getString("maturity_level"));
                processFromDB.setPurposeStatement(resultSet.getString("purpose_statement"));
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
    public int readByNameAndStandard(String name, int idStandard) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        int idProcess = -1;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
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
            dataBaseConnection.close();
        }
        return idProcess;
    }

    @Override
    public void update(Process process) {

    }

    @Override
    public void delete(int id) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
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
    public ArrayList<Process> readAllByStandard(int id) {
        DataBaseConnection isoDataBaseConnection = new CmmiDataBaseConnection();
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
                        resultSet.getString("area_category"), resultSet.getString("maturity_level"),
                        resultSet.getString("purpose_statement"), resultSet.getInt("standard"));
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
