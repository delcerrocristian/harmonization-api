package persistence.firstfilter.dao;


import persistence.firstfilter.model.Activity;

import java.util.ArrayList;

/**
 * Created by spukyn on 3/05/14.
 */
public interface ActivityDao {

    int create(Activity activity);

    Activity read(int id);

    void update(Activity activity);

    void delete(int id);

    ArrayList<Activity> readAllByProcess(int id);
}
