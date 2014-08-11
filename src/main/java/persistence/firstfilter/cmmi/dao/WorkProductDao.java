package persistence.firstfilter.cmmi.dao;

import persistence.firstfilter.cmmi.model.WorkProduct;

import java.util.ArrayList;

/**
 * Created by Cristian del Cerro
 */
public interface WorkProductDao {

    int create(WorkProduct workProduct);

    WorkProduct read(int id);

    void update(WorkProduct workProduct);

    void delete(int id);

    ArrayList<WorkProduct> readAllBySpecificPractice(int specificPractice);

    ArrayList<WorkProduct> readAllByStandard(int standard);
}
