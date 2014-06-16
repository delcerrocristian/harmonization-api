package persistence.firstfilter.iso.dao;

import persistence.firstfilter.iso.model.Process;

import java.util.ArrayList;

/**
 * Created by spukyn on 3/05/14.
 */
public interface ProcessDao {

    int create(Process process);

    Process read(int id);

    int readByNameAndStandard(String name, int idStandard);

    void update(Process process);

    void delete(int id);

    ArrayList<Process> readAllByStandard(int id);
}
