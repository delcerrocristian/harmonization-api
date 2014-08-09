package services.cmmi;

import persistence.firstfilter.cmmi.model.*;
import persistence.firstfilter.cmmi.model.Process;

import java.util.ArrayList;

/**
 * Created by Cristian del Cerro
 */
public interface CmmiService {

    int createStandard(Standard standard);

    int createStandard(String name);

    Standard readStandard(int id);

    void deleteStandard(int id);

    ArrayList<Standard> readAllStandards();

    int createProcess(persistence.firstfilter.cmmi.model.Process process);

    Process readProcess(int id);

    void updateProcess(Process process);

    void deleteProcess(int id);

    ArrayList<persistence.firstfilter.cmmi.model.Process> readAllProcessByStandard(int idStandard);

    int createSpecificGoal(SpecificGoal specificGoal);

    SpecificGoal readSpecificGoal(int id);

    ArrayList<SpecificGoal> readAllSpecificGoalByProcess(int idProcess);

    int createSpecificPractice(SpecificPractice specificPractice);

    SpecificPractice readSpecificPractice(int id);

    ArrayList<SpecificPractice> readAllSpecificPracticeBySpecificGoal(int idSpecificGoal);

    int createWorkProduct(WorkProduct workProduct);

    WorkProduct readWorkProduct(int id);

    ArrayList<WorkProduct> readAllWorkProductBySpecificPractice(int idSpecificPractice);

}

