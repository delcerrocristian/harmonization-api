package services.iso;

import persistence.firstfilter.iso.model.Activity;
import persistence.firstfilter.iso.model.Process;
import persistence.firstfilter.iso.model.Standard;
import persistence.firstfilter.iso.model.Task;

import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public interface IsoService {

    int createStandard (Standard standard);

    int createStandard (String nameStandard);

    Standard readStandard(int id);

    void deleteStandard(int id);

    ArrayList<Standard> readAllStandards();

    int addProcess (Process process);

    Process readProcess (int id);

    ArrayList<Process> readAllProcess(int standard);

    void updateProcess(Process process);

    void deleteProcess (int id);

    ArrayList<Process> readAllProcessByStandard(int id);

    int readIdProcessByNameAndStandard(String name, int idStandard);

    int readCountProcessByStandard(int idStandard);

    int addActivity(Activity activity);

    Activity readActivity (int id);

    void updateActivity(Activity activity);

    void deleteActivity (int id);

    ArrayList<Activity> readAllActivityByProcess(int id);

    ArrayList<Activity> readAllActivityByStandard(int standard);

    int readIdActivityByNameAndProcess(String name, int process);

    int readCountActivityByStandard(int idStandard);

    void cleanActivity(int idStandard);

    int addTask(Task task);

    void updateTask (Task task);

    Task readTask (int id);

    void deleteTask (int id);

    ArrayList<Task> readAllTaskByProcess(int id);

    ArrayList<Task> readAllTaskByActivity(int id);

    ArrayList<Task> readAllTaskByStandard(int id);

    int readCountTaskByStandard(int idStandard);
}
