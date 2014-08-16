package persistence.firstfilter.iso.dao;

import persistence.firstfilter.iso.model.Process;

import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public interface ProcessDao {

    int create(Process process);

    Process read(int id);

    int readByNameAndStandard(String name, int idStandard);

    void update(Process process);

    void delete(int id);

    ArrayList<Process> readAllByStandard(int id);

    int countByStandard(int id);
}
