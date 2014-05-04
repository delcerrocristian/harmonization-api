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
public class TaskDaoImp implements TaskDao {
    @Override
    public int create(Task task) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        int id = -1;

        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("insert into task (content, activity) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, task.getContent());
            preparedStatement.setInt(2, task.getActivity());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        }
        catch (SQLException sqlException) {
            System.out.println("SQLException happened executing create to task");
        }
        finally {
            dataBaseConnection.close();
        }
        return id;
    }

    @Override
    public Task read(int id) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        Task taskFromDB = null;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select * from task where id=?");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                resultSet.next();
                taskFromDB = new Task();
                taskFromDB.setId(resultSet.getInt("id"));
                taskFromDB.setContent(resultSet.getString("content"));
                taskFromDB.setActivity(resultSet.getInt("activity"));
                taskFromDB.setIsProcessed(resultSet.getInt("is_processed"));
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select to task");

        }finally{
            dataBaseConnection.close();
        }
        return taskFromDB;
    }

    @Override
    public void update(Task task) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    (buildSqlUpdateStatement(task));


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException happened executing updating task");
        }
        finally{
            dataBaseConnection.close();
        }
    }

    @Override
    public void delete(int id) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("delete from task where id=?");
            preparedStatement.setInt(1,id);

            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("SQLException happened executing delete to task");
        }finally{
            dataBaseConnection.close();
        }
    }

    @Override
    public ArrayList<Task> readAll() {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        Task taskFromDB;
        ArrayList<Task> allTasks = new ArrayList<>();
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select * from task");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet != null){
                resultSet.next();

                taskFromDB = new Task(resultSet.getInt("id"), resultSet.getString("content"),
                        resultSet.getInt("activity"), resultSet.getInt("is_processed"));
                allTasks.add(taskFromDB);
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select all tasks");
        }finally{
            dataBaseConnection.close();
        }
        return allTasks;
    }

    private String buildSqlUpdateStatement(Task task) {
        boolean anyFieldUpdate = false;
        String statement = "update task set ";
        if(task.getContent() != null){
            statement += "content="+task.getContent();
            anyFieldUpdate = true;
        }
        if (task.getActivity() != null) {
            if(anyFieldUpdate){
                statement += ",";
            }
            statement += "activity="+task.getActivity();
            anyFieldUpdate = true;
        }
        if(anyFieldUpdate) {
            statement += ",";
        }
        statement += "is_processed=1";
        statement +=" where id="+task.getId();

        return statement;
    }
}
