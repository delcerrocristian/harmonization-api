package persistence.firstfilter.dao;

import persistence.firstfilter.model.Process;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by spukyn on 3/05/14.
 */
public interface ProcessDao {

    int create(Process process);

    Process read(int id);

    void update(Process process);

    void delete(int id);

    ArrayList<Process> readAll();
}
