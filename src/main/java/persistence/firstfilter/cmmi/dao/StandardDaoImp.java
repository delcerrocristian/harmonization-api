package persistence.firstfilter.cmmi.dao;

import persistence.firstfilter.cmmi.model.Standard;

import java.util.ArrayList;

/**
 * Created by Cristian del Cerro
 */
public class StandardDaoImp implements StandardDao{
    @Override
    public int create(Standard standard) {
        return 0;
    }

    @Override
    public int create(String name) {
        return 0;
    }

    @Override
    public Standard read(int id) {
        return null;
    }

    @Override
    public void update(Standard standard) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ArrayList<Standard> readAll() {
        return null;
    }
}
