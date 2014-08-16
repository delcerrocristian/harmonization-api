package persistence.firstfilter.iso.dao;


import persistence.firstfilter.iso.model.Activity;

import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public interface ActivityDao {

    int create(Activity activity);

    Activity read(int id);

    int readIdByNameAndProcess(String name, int process);

    void update(Activity activity);

    void delete(int id);

    ArrayList<Activity> readAllByProcess(int id);

    ArrayList<Activity> readAllByStandard(int id);

    int countByStandard(int id);

    void clean(int id);
}
