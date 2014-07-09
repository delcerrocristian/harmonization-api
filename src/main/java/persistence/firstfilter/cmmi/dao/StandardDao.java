package persistence.firstfilter.cmmi.dao;

import persistence.firstfilter.cmmi.model.Standard;

import java.util.ArrayList;

/**
 * Created by Cristian del Cerro on 19/06/14.
 */
public interface StandardDao{

    int create(Standard standard) ;

    int create(String name) ;

    Standard read(int id);

    void update(Standard standard);

    void delete(int id);

    ArrayList<Standard> readAll();
}
