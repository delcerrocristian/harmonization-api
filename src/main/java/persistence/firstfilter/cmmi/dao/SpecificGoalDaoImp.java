package persistence.firstfilter.cmmi.dao;

import persistence.firstfilter.cmmi.model.SpecificGoal;
import persistence.firstfilter.dataBaseConnection.CmmiDataBaseConnection;
import persistence.firstfilter.dataBaseConnection.DataBaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro
 */
public class SpecificGoalDaoImp implements SpecificGoalDao {
    @Override
    public int create(SpecificGoal specificGoal) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        int id = -1;

        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("insert into specific_goal (title, description, process) " +
                            "VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, specificGoal.getTitle());
            preparedStatement.setString(2, specificGoal.getDescription());
            preparedStatement.setInt(3, specificGoal.getProcess());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        }
        catch (SQLException sqlException) {
            System.out.println("SQLException happened executing create to specific goal");
        }
        finally {
            dataBaseConnection.close();
        }
        return id;
    }

    @Override
    public SpecificGoal read(int id) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        SpecificGoal specificGoalFromDB = null;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select * from specific_goal where id=?");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                resultSet.next();
                specificGoalFromDB = new SpecificGoal();
                specificGoalFromDB.setId(resultSet.getInt("id"));
                specificGoalFromDB.setTitle(resultSet.getString("title"));
                specificGoalFromDB.setDescription(resultSet.getString("description"));
                specificGoalFromDB.setProcess(resultSet.getInt("process"));
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select to specific goal");

        }finally{
            dataBaseConnection.close();
        }
        return specificGoalFromDB;
    }

    @Override
    public void update(SpecificGoal specificGoal) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("update specific_goal set " +
                            "title=?, description=?, process=? " +
                            "where id=?");


            preparedStatement.setString(1, specificGoal.getTitle());
            preparedStatement.setString(2, specificGoal.getDescription());
            preparedStatement.setInt(3, specificGoal.getProcess());
            preparedStatement.setInt(4, specificGoal.getId());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println("SQLException happened executing update of specific goal");
        }finally{
            dataBaseConnection.close();
        }
    }

    @Override
    public void delete(int id) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("delete from specific_goal where id=?");
            preparedStatement.setInt(1,id);

            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("SQLException happened executing delete to specific goal");
        }finally{
            dataBaseConnection.close();
        }
    }

    @Override
    public ArrayList<SpecificGoal> readAllByProcess(int process) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        SpecificGoal specificGoal;
        ArrayList<SpecificGoal> allSpecificGoal = new ArrayList<>();
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select * from specific_goal where process="+process);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet != null){
                resultSet.next();

                specificGoal = new SpecificGoal(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("description"), resultSet.getInt("process"));
                allSpecificGoal.add(specificGoal);
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select all specific_goal");
        }finally{
            dataBaseConnection.close();
        }
        return allSpecificGoal;
    }

    @Override
    public ArrayList<SpecificGoal> readAllByStandard(int standard) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        SpecificGoal specificGoal;
        ArrayList<SpecificGoal> allSpecificGoal = new ArrayList<>();
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select * from specific_goal where process in (select id from process where standard=?)");

            preparedStatement.setInt(1,standard);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet != null){
                resultSet.next();

                specificGoal = new SpecificGoal(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("description"), resultSet.getInt("process"));
                allSpecificGoal.add(specificGoal);
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select all specific_goal by standard");
        }finally{
            dataBaseConnection.close();
        }
        return allSpecificGoal;
    }
}
