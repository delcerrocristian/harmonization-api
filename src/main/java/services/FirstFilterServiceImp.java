package services;

import persistence.firstfilter.dao.EnumSentenceDao;
import persistence.firstfilter.dao.MainSentenceDao;
import persistence.firstfilter.dao.StandardDao;
import persistence.firstfilter.model.MainSentence;
import persistence.firstfilter.model.Standard;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public class FirstFilterServiceImp implements FirstFilterService{

    StandardDao standardDao;
    MainSentenceDao mainSentenceDao;
    EnumSentenceDao enumSentenceDao;

    public FirstFilterServiceImp(StandardDao standardDao, MainSentenceDao mainSentenceDao, EnumSentenceDao enumSentenceDao) {
        this.standardDao = standardDao;
        this.mainSentenceDao = mainSentenceDao;
        this.enumSentenceDao = enumSentenceDao;
    }

    @Override
    public void createStandard (Standard standard) throws SQLException {
        standardDao.create(standard);
    }

    @Override
    public Standard readStandard(int id) throws SQLException {
        return standardDao.read(id);
    }

    @Override
    public void deleteStandard(int id) throws SQLException {
        standardDao.delete(id);
    }

    @Override
    public ArrayList<Standard> readAllStandards() throws SQLException {
        return  standardDao.readAll();
    }

    @Override
    public void addMainSentence (MainSentence mainSentence) throws SQLException {
        mainSentenceDao.create(mainSentence);
    }

}
