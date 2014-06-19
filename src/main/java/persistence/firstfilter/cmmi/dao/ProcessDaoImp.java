package persistence.firstfilter.cmmi.dao;

import persistence.firstfilter.cmmi.model.*;
import persistence.firstfilter.cmmi.model.Process;

import java.util.ArrayList;

/**
 * Created by Cristian del Cerro
 */
public class ProcessDaoImp implements ProcessDao{
    @Override
    public int create(persistence.firstfilter.cmmi.model.Process process) {
        return 0;
    }

    @Override
    public Process read(int id) {
        return null;
    }

    @Override
    public int readByNameAndStandard(String name, int idStandard) {
        return 0;
    }

    @Override
    public void update(Process process) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ArrayList<Process> readAllByStandard(int id) {
        return null;
    }
}
