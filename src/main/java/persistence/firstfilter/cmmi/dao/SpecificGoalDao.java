package persistence.firstfilter.cmmi.dao;

import persistence.firstfilter.cmmi.model.SpecificGoal;

import java.util.ArrayList;

/**
 * Created by Cristian del Cerro
 */
public interface SpecificGoalDao {

    int create(SpecificGoal specificGoal);

    SpecificGoal read(int id);

    void update(SpecificGoal specificGoal);

    void delete(int id);

    ArrayList<SpecificGoal> readAllByProcess(int process);

    ArrayList<SpecificGoal> readAllByStandard(int standard);
}
