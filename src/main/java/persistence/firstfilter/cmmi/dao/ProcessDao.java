package persistence.firstfilter.cmmi.dao;


import persistence.firstfilter.cmmi.model.Process;

import java.util.ArrayList;

/**
 * Created by Cristian del Cerro
 */
public interface ProcessDao {

    int create(persistence.firstfilter.cmmi.model.Process process);

    persistence.firstfilter.cmmi.model.Process read(int id);

    int readByNameAndStandard(String name, int idStandard);

    void update(persistence.firstfilter.cmmi.model.Process process);

    void delete(int id);

    ArrayList<persistence.firstfilter.cmmi.model.Process> readAllByStandard(int id);
}
