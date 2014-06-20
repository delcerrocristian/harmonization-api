package persistence.firstfilter.cmmi.dao;

import persistence.firstfilter.cmmi.model.SpecificPractice;
import persistence.firstfilter.dataBaseConnection.CmmiDataBaseConnection;
import persistence.firstfilter.dataBaseConnection.DataBaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro
 */
public class SpecificPracticeDaoImp implements SpecificPracticeDao {

    @Override
    public int create(SpecificPractice specificPractice) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        int id = -1;

        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("insert into specific_practice (title, description, specific_goal) " +
                            "VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, specificPractice.getTitle());
            preparedStatement.setString(2, specificPractice.getDescription());
            preparedStatement.setInt(3, specificPractice.getSpecificGoal());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        }
        catch (SQLException sqlException) {
            System.out.println("SQLException happened executing create to specific practice");
        }
        finally {
            dataBaseConnection.close();
        }
        return id;
    }

    @Override
    public SpecificPractice read(int id) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        SpecificPractice specificGoalFromDB = null;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select * from specific_practice where id=?");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                resultSet.next();
                specificGoalFromDB = new SpecificPractice();
                specificGoalFromDB.setId(resultSet.getInt("id"));
                specificGoalFromDB.setTitle(resultSet.getString("title"));
                specificGoalFromDB.setDescription(resultSet.getString("description"));
                specificGoalFromDB.setSpecificGoal(resultSet.getInt("specific_goal"));
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select to specific practice");

        }finally{
            dataBaseConnection.close();
        }
        return specificGoalFromDB;
    }

    @Override
    public void update(SpecificPractice specificPractice) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ArrayList<SpecificPractice> readAllBySpecificGoal(int specificGoal) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        SpecificPractice specificPractice;
        ArrayList<SpecificPractice> allSpecificPractice = new ArrayList<>();
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select * from specific_practice where specific_goal="+specificGoal);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet != null){
                resultSet.next();

                specificPractice = new SpecificPractice(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("description"), resultSet.getInt("specific_goal"));
                allSpecificPractice.add(specificPractice);
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select all specific practice");
        }finally{
            dataBaseConnection.close();
        }
        return allSpecificPractice;
    }
}
