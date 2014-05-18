package services;

import persistence.firstfilter.model.*;
import persistence.firstfilter.model.Process;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public interface FirstFilterService {

    int createStandard (Standard standard);

    int createStandard (String nameStandard);

    Standard readStandard(int id);

    void deleteStandard(int id);

    ArrayList<Standard> readAllStandards();

    int addProcess (Process process);

    Process readProcess (int id);

    void deleteProcess (int id);

    ArrayList<Process> readAllProcessByStandard(int id);

    int readIdProcessByNameAndStandard(String name, int idStandard);

    int addActivity(Activity activity);

    Activity readActivity (int id);

    void deleteActivity (int id);

    ArrayList<Activity> readAllActivityByProcess(int id);

    int readIdActivityByNameAndProcess(String name, int process);

    int addTask(Task task);

    void updateTask (Task task);

    Task readTask (int id);

    void deleteTask (int id);

    ArrayList<Task> readAllTaskByProcess(int id);

    ArrayList<Task> readAllTaskByActivity(int id);

    ArrayList<Task> readAllTaskByStandard(int id);
}
