package persistence.firstfilter.dao;

import persistence.firstfilter.model.Task;

import java.util.ArrayList;

/**
 * Created by spukyn on 3/05/14.
 */
public interface TaskDao {

    int create(Task task);

    Task read(int id);

    void update(Task task);

    void delete(int id);

    ArrayList<Task> readAll();

}
