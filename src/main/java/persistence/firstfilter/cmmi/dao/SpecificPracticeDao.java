package persistence.firstfilter.cmmi.dao;

import java.util.ArrayList;

/**
 * Created by Cristian del Cerro
 */
public interface SpecificPracticeDao {

    void create(SpecificPracticeDao specificPracticeDao);

    SpecificPracticeDao read(int id);

    void update(SpecificPracticeDao specificPracticeDao);

    void delete(int id);

    ArrayList<SpecificPracticeDao> readAllBySpecificGoal(int specificGoal);
}
