package persistence.firstfilter.iso.dao;

import persistence.firstfilter.dataBaseConnection.CmmiDataBaseConnection;
import persistence.firstfilter.dataBaseConnection.DataBaseConnection;
import persistence.firstfilter.dataBaseConnection.IsoDataBaseConnection;
import persistence.firstfilter.iso.model.Activity;

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
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        int id = -1;

        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
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
            isoDataBaseConnection.close();
        }
        return id;
    }

    @Override
    public Activity read(int id) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        Activity activityFromDB = null;
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
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
            isoDataBaseConnection.close();
        }
        return activityFromDB;
    }

    @Override
    public int readIdByNameAndProcess(String name, int process) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        int idActivity = -1;
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("select * from activity where name=? and process=?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, process);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                idActivity = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select to activity");

        }finally{
            isoDataBaseConnection.close();
        }
        return idActivity;
    }

    @Override
    public void update(Activity activity) {
        DataBaseConnection dataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("update activity set " +
                            "name=?, process=? " +
                            "where id=?");

            preparedStatement.setString(1, activity.getName());
            preparedStatement.setInt(2, activity.getProcess());
            preparedStatement.setInt(3, activity.getId());

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
                    ("delete from activity where id=?");
            preparedStatement.setInt(1,id);

            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("SQLException happened executing delete to activity");
        }finally{
            isoDataBaseConnection.close();
        }
    }

    @Override
    public ArrayList<Activity> readAllByProcess(int id) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        Activity activityFromDB;
        ArrayList<Activity> allActivities = new ArrayList<>();
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
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
            isoDataBaseConnection.close();
        }
        return allActivities;
    }

    @Override
    public ArrayList<Activity> readAllByStandard(int id) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        Activity activityFromDB;
        ArrayList<Activity> allActivities = new ArrayList<>();
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("select * from activity where process in (select id from process where standard=?)");

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                activityFromDB = new Activity(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("process"));
                allActivities.add(activityFromDB);
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select all activities");
        }finally{
            isoDataBaseConnection.close();
        }
        return allActivities;
    }

    @Override
    public int countByStandard(int id) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        int numActivity = 0;
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("select count(*) as num from activity where process in (select id from process where standard="+id+")");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                numActivity=  resultSet.getInt("num");
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select count activity");
        }finally{
            isoDataBaseConnection.close();
        }
        return numActivity;
    }

    @Override
    public void clean(int id) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("delete from activity where process in (select id from process where standard=?) " +
                            "and name=\"Not Found\"");

            preparedStatement.setInt(1,id);

            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("SQLException happened executing clean to activity");

        }finally{
            isoDataBaseConnection.close();
        }
    }

}
