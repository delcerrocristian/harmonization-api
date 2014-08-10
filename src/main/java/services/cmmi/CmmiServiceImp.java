package services.cmmi;

import persistence.firstfilter.cmmi.dao.*;
import persistence.firstfilter.cmmi.model.*;
import persistence.firstfilter.cmmi.model.Process;

import java.util.ArrayList;

/**
 * Created by Cristian del Cerro
 */
public class CmmiServiceImp implements CmmiService {

    private StandardDao standardDao;
    private ProcessDao processDao;
    private SpecificGoalDao specificGoalDao;
    private SpecificPracticeDao specificPracticeDao;
    private WorkProductDao workProductDao;

    public CmmiServiceImp(StandardDao standardDao, SpecificGoalDao specificGoalDao, ProcessDao processDao,
                          SpecificPracticeDao specificPracticeDao, WorkProductDao workProductDao) {
        this.standardDao = standardDao;
        this.specificGoalDao = specificGoalDao;
        this.processDao = processDao;
        this.specificPracticeDao = specificPracticeDao;
        this.workProductDao = workProductDao;
    }

    @Override
    public int createStandard(Standard standard) {
        return standardDao.create(standard);
    }

    @Override
    public int createStandard(String name) {
        return standardDao.create(name);
    }

    @Override
    public Standard readStandard(int id) {
        return standardDao.read(id);
    }

    @Override
    public void deleteStandard(int id) {
        standardDao.delete(id);
    }

    @Override
    public ArrayList<Standard> readAllStandards() {
        return standardDao.readAll();
    }

    @Override
    public int createProcess(persistence.firstfilter.cmmi.model.Process process) {
        return processDao.create(process);
    }

    @Override
    public Process readProcess(int id) {
        return processDao.read(id);
    }

    @Override
    public void updateProcess(Process process) {
        processDao.update(process);
    }

    @Override
    public void deleteProcess(int id) {
        processDao.delete(id);
    }

    @Override
    public ArrayList<Process> readAllProcessByStandard(int idStandard) {
        return processDao.readAllByStandard(idStandard);
    }

    @Override
    public int createSpecificGoal(SpecificGoal specificGoal) {
        return specificGoalDao.create(specificGoal);
    }

    @Override
    public SpecificGoal readSpecificGoal(int id) {
        return specificGoalDao.read(id);
    }

    @Override
    public void updateSpecificGoal(SpecificGoal specificGoal) {
        specificGoalDao.update(specificGoal);
    }

    @Override
    public void deleteSpecificGoal(int id) {
        specificGoalDao.delete(id);
    }

    @Override
    public ArrayList<SpecificGoal> readAllSpecificGoalByProcess(int idProcess) {
        return specificGoalDao.readAllByProcess(idProcess);
    }

    @Override
    public ArrayList<SpecificGoal> readAllSpecificGoalByStandard(int standard) {
        return specificGoalDao.readAllByStandard(standard);
    }

    @Override
    public int createSpecificPractice(SpecificPractice specificPractice) {
        return specificPracticeDao.create(specificPractice);
    }

    @Override
    public SpecificPractice readSpecificPractice(int id) {
        return specificPracticeDao.read(id);
    }

    @Override
    public void updateSpecificPractice(SpecificPractice specificPractice) {
        specificPracticeDao.update(specificPractice);
    }

    @Override
    public void deleteSpecificPractice(int id) {
        specificPracticeDao.delete(id);
    }

    @Override
    public ArrayList<SpecificPractice> readAllSpecificPracticeBySpecificGoal(int idSpecificGoal) {
        return specificPracticeDao.readAllBySpecificGoal(idSpecificGoal);
    }

    @Override
    public int createWorkProduct(WorkProduct workProduct) {
        return workProductDao.create(workProduct);
    }

    @Override
    public WorkProduct readWorkProduct(int id) {
        return workProductDao.read(id);
    }

    @Override
    public void updateWorkProduct(WorkProduct workProduct) {
        workProductDao.update(workProduct);
    }

    @Override
    public void deleteWorkProduct(int id) {
        workProductDao.delete(id);
    }

    @Override
    public ArrayList<WorkProduct> readAllWorkProductBySpecificPractice(int idSpecificPractice) {
        return workProductDao.readAllBySpecificPractice(idSpecificPractice);
    }
}
