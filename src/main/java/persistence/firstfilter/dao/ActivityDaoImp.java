package persistence.firstfilter.dao;

import persistence.firstfilter.DataBaseConnection;
import persistence.firstfilter.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by spukyn on 3/05/14.
 */
public class ActivityDaoImp implements ActivityDao{


    @Override
    public int create(Activity activity) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        int id = -1;

        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("insert into activity (name, process) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, activity.getName());
            preparedStatement.setInt(2, activity.getProcess());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        }
        catch (SQLException sqlException) {
            System.out.println("SQLException happened executing create to activity");
        }
        finally {
            dataBaseConnection.close();
        }
        return id;
    }

    @Override
    public Activity read(int id) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        Activity activityFromDB = null;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select * from activity where id=?");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                resultSet.next();
                activityFromDB = new Activity();
                activityFromDB.setId(resultSet.getInt("id"));
                activityFromDB.setName(resultSet.getString("name"));
                activityFromDB.setProcess(resultSet.getInt("process"));
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select to activity");

        }finally{
            dataBaseConnection.close();
        }
        return activityFromDB;
    }

    @Override
    public void update(Activity activity) {

    }

    @Override
    public void delete(int id) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("delete from activity where id=?");
            preparedStatement.setInt(1,id);

            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("SQLException happened executing delete to activity");
        }finally{
            dataBaseConnection.close();
        }
    }

    @Override
    public ArrayList<Activity> readAllByProcess(int id) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        Activity activityFromDB;
        ArrayList<Activity> allActivities = new ArrayList<>();
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select * from activity where process="+id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet != null){
                resultSet.next();

                activityFromDB = new Activity(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("process"));
                allActivities.add(activityFromDB);
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select all activities");
        }finally{
            dataBaseConnection.close();
        }
        return allActivities;
    }
}
