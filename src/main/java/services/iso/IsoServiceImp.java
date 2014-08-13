package services.iso;

import persistence.firstfilter.iso.dao.*;
import persistence.firstfilter.iso.model.Activity;
import persistence.firstfilter.iso.model.Process;
import persistence.firstfilter.iso.model.Standard;
import persistence.firstfilter.iso.model.Task;

import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public class IsoServiceImp implements IsoService {

    StandardDao standardDao;
    ProcessDao processDao;
    ActivityDao activityDao;
    TaskDao taskDao;

    public IsoServiceImp(StandardDao standardDao, ProcessDao processDao, ActivityDao activityDao,
                         TaskDao taskDao) {
        this.standardDao = standardDao;
        this.processDao = processDao;
        this.activityDao = activityDao;
        this.taskDao = taskDao;
    }

    @Override
    public int createStandard (Standard standard) {
        return standardDao.create(standard);
    }

    @Override
    public int createStandard (String nameStandard) {
        return standardDao.create(nameStandard);
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
        return  standardDao.readAll();
    }

    @Override
    public int addProcess (Process process) {
        return processDao.create(process);
    }

    @Override
    public Process readProcess (int id) {
        return processDao.read(id);
    }

    @Override
    public void updateProcess(Process process) {
        processDao.update(process);
    }

    @Override
    public void deleteProcess (int id) {
        processDao.delete(id);
    }

    @Override
    public ArrayList<Process> readAllProcessByStandard(int id) {
        return processDao.readAllByStandard(id);
    }

    @Override
    public int readIdProcessByNameAndStandard(String name, int idStandard) {
        return processDao.readByNameAndStandard(name, idStandard);
    }

    @Override
    public int addActivity(Activity activity) {
        return activityDao.create(activity);
    }

    @Override
    public Activity readActivity (int id) {
        return activityDao.read(id);
    }

    @Override
    public void updateActivity (Activity activity) {
        activityDao.update(activity);
    }

    @Override
    public void deleteActivity (int id) {
        activityDao.delete(id);
    }

    @Override
    public ArrayList<Activity> readAllActivityByProcess(int id) {
        return activityDao.readAllByProcess(id);
    }

    @Override
    public int readIdActivityByNameAndProcess(String name, int process) {
        return activityDao.readIdByNameAndProcess(name, process);
    }

    @Override
    public int addTask(Task task) {
        return taskDao.create(task);
    }

    @Override
    public void updateTask (Task task) {
        taskDao.update(task);
    }

    @Override
    public Task readTask (int id) {
        return taskDao.read(id);
    }

    @Override
    public void deleteTask (int id) {
        taskDao.delete(id);
    }

    @Override
    public ArrayList<Task> readAllTaskByProcess(int id) {
        return taskDao.readAllByProcess(id);
    }

    @Override
    public ArrayList<Task> readAllTaskByActivity(int id) {
        return taskDao.readAllByActivity(id);
    }

    @Override
    public ArrayList<Task> readAllTaskByStandard(int id) {
        return taskDao.readAllByStandard(id);
    }

}
