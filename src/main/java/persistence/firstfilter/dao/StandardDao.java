package persistence.firstfilter.dao;

import persistence.firstfilter.model.Standard;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public interface StandardDao {

    int create(Standard standard) ;

    int create(String name) ;

    Standard read(int id);

    void update(Standard standard);

    void delete(int id);

    ArrayList<Standard> readAll();
}
