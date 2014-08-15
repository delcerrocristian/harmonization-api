package persistence.firstfilter.iso.dao;

import persistence.firstfilter.iso.model.Task;

import java.util.ArrayList;

/**
 * Created by spukyn on 3/05/14.
 */
public interface TaskDao {

    int create(Task task);

    Task read(int id);

    void update(Task task);

    void delete(int id);

    ArrayList<Task> readAllByProcess(int id);

    ArrayList<Task> readAllByActivity(int id);

    ArrayList<Task> readAllByStandard(int id);

    int countByStandard(int id);
}
