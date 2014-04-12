package persistence.firstfilter.dao;

import persistence.firstfilter.model.Standard;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public interface StandardDao {

    int create(Standard standard) throws SQLException;

    int create(String name) throws SQLException;

    Standard read(int id) throws SQLException;

    void update(Standard standard) throws SQLException;

    void delete(int id) throws SQLException;

    ArrayList<Standard> readAll() throws SQLException;
}
