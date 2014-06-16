package persistence.firstfilter.iso.dao;


import persistence.firstfilter.iso.model.Activity;

import java.util.ArrayList;

/**
 * Created by spukyn on 3/05/14.
 */
public interface ActivityDao {

    int create(Activity activity);

    Activity read(int id);

    int readIdByNameAndProcess(String name, int process);

    void update(Activity activity);

    void delete(int id);

    ArrayList<Activity> readAllByProcess(int id);
}
