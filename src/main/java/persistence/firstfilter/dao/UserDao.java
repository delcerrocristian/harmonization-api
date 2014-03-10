package persistence.firstfilter.dao;

import persistence.firstfilter.model.Standard;

import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public interface UserDao {

    void create(Standard standard);

    Standard read(int id);

    void update(Standard standard);

    void delete(int id);

    ArrayList<Standard> readAll();
}
