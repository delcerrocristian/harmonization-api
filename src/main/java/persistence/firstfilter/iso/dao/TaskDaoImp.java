package persistence.firstfilter.iso.dao;

import persistence.firstfilter.dataBaseConnection.IsoDataBaseConnection;
import persistence.firstfilter.iso.model.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public class TaskDaoImp implements TaskDao {
    @Override
    public int create(Task task) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        int id = -1;

        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("insert into task (content, process, activity) VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, task.getContent());
            preparedStatement.setInt(2, task.getProcess());
            if(task.getActivity()!=null) {
                preparedStatement.setInt(3, task.getActivity());
            }
            else {
                preparedStatement.setString(3, null);
            }

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
            isoDataBaseConnection.close();
        }
        return id;
    }

    @Override
    public Task read(int id) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        Task taskFromDB = null;
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("select * from task where id=?");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                resultSet.next();
                taskFromDB = new Task();
                taskFromDB.setId(resultSet.getInt("id"));
                taskFromDB.setContent(resultSet.getString("content"));
                taskFromDB.setProcess(resultSet.getInt("process"));
                taskFromDB.setActivity(resultSet.getInt("activity"));
                taskFromDB.setIsProcessed(resultSet.getInt("is_processed"));
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select to task");

        }finally{
            isoDataBaseConnection.close();
        }
        return taskFromDB;
    }

    @Override
    public void update(Task task) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("update task set " +
                            "content=?, process=?, activity=? " +
                            "where id=?");

            preparedStatement.setString(1,task.getContent());
            preparedStatement.setInt(2,task.getProcess());

            if(task.getActivity()!=null && task.getActivity()!=0 ) {
                preparedStatement.setInt(3, task.getActivity());
            }
            else {
                preparedStatement.setString(3, null);
            }

            preparedStatement.setInt(4,task.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQLException happened executing updating task");
        }
        finally{
            isoDataBaseConnection.close();
        }
    }

    @Override
    public void delete(int id) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("delete from task where id=?");
            preparedStatement.setInt(1,id);

            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("SQLException happened executing delete to task");
        }finally{
            isoDataBaseConnection.close();
        }
    }

    @Override
    public ArrayList<Task> readAllByProcess(int id) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        Task taskFromDB;
        ArrayList<Task> allTasks = new ArrayList<>();
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("select * from task where process="+id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                taskFromDB = new Task(resultSet.getInt("id"), resultSet.getString("content"),
                        resultSet.getInt("process"), resultSet.getInt("activity"),
                        resultSet.getInt("is_processed"));
                allTasks.add(taskFromDB);
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select all tasks");
        }finally{
            isoDataBaseConnection.close();
        }
        return allTasks;
    }

    @Override
    public ArrayList<Task> readAllByActivity(int id) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        Task taskFromDB;
        ArrayList<Task> allTasks = new ArrayList<>();
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("select * from task where activity="+id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                taskFromDB = new Task(resultSet.getInt("id"), resultSet.getString("content"),
                        resultSet.getInt("process"), resultSet.getInt("activity"),
                        resultSet.getInt("is_processed"));
                allTasks.add(taskFromDB);
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select all tasks");
        }finally{
            isoDataBaseConnection.close();
        }
        return allTasks;
    }

    @Override
    public ArrayList<Task> readAllByStandard(int id) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        Task taskFromDB;
        ArrayList<Task> allTasks = new ArrayList<>();
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("select * from task where process in (select id from process where standard="+id+")");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                taskFromDB = new Task(resultSet.getInt("id"), resultSet.getString("content"),
                        resultSet.getInt("process"), resultSet.getInt("activity"),
                        resultSet.getInt("is_processed"));
                allTasks.add(taskFromDB);
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select all tasks");
        }finally{
            isoDataBaseConnection.close();
        }
        return allTasks;
    }

    @Override
    public int countByStandard(int id) {
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        int numTask = 0;
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("select count(*) as num from task where activity in (select id from activity where process in " +
                            "(select id from process where standard="+id+"))");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                numTask=  resultSet.getInt("num");
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select count task");
        }finally{
            isoDataBaseConnection.close();
        }
        return numTask;
    }
}
