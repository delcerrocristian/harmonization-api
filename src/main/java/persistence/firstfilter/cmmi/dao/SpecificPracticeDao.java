package persistence.firstfilter.cmmi.dao;

import persistence.firstfilter.cmmi.model.SpecificPractice;

import java.util.ArrayList;

/**
 * Created by Cristian del Cerro
 */
public interface SpecificPracticeDao {

    int create(SpecificPractice specificPractice);

    SpecificPractice read(int id);

    void update(SpecificPractice specificPractice);

    void delete(int id);

    ArrayList<SpecificPractice> readAllBySpecificGoal(int specificGoal);

    ArrayList<SpecificPractice> readAllByStandard(int standard);
}
