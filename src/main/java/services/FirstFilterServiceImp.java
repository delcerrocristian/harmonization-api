package services;

import persistence.firstfilter.dao.EnumSentenceDao;
import persistence.firstfilter.dao.MainSentenceDao;
import persistence.firstfilter.dao.StandardDao;
import persistence.firstfilter.model.EnumSentence;
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
    public int createStandard (Standard standard) throws SQLException {
        return standardDao.create(standard);
    }

    @Override
    public int createStandard (String nameStandard) throws SQLException {
        return standardDao.create(nameStandard);
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
    public int addMainSentence (MainSentence mainSentence) throws SQLException {
        return mainSentenceDao.create(mainSentence);
    }

    @Override
    public MainSentence readMainSentence (int id) throws SQLException {
        return mainSentenceDao.read(id);
    }

    @Override
    public void deleteMainSentence (int id) throws SQLException {
        mainSentenceDao.delete(id);
    }

    @Override
    public ArrayList<MainSentence> readAllMainSentencesByStandard(int id) throws SQLException {
        return mainSentenceDao.readAllByStandard(id);
    }

    public int addEnumSentence(EnumSentence enumSentence) throws SQLException{
        return enumSentenceDao.create(enumSentence);
    }

    @Override
    public EnumSentence readEnumSentence (int id) throws SQLException {
        return enumSentenceDao.read(id);
    }

    @Override
    public void deleteEnumSentence (int id) throws SQLException {
        enumSentenceDao.delete(id);
    }

    @Override
    public ArrayList<EnumSentence> readAllEnumSentencesByMainSentence (int id) throws SQLException {
        return enumSentenceDao.readAllByMainSentence(id);
    }

}
