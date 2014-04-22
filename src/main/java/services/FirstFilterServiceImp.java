package services;

import persistence.firstfilter.dao.EnumSentenceDao;
import persistence.firstfilter.dao.MainSentenceDao;
import persistence.firstfilter.dao.MethodToDataBase;
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
    MethodToDataBase methodToDataBase;

    public FirstFilterServiceImp(StandardDao standardDao, MainSentenceDao mainSentenceDao, EnumSentenceDao enumSentenceDao,
                                 MethodToDataBase methodToDataBase) {
        this.standardDao = standardDao;
        this.mainSentenceDao = mainSentenceDao;
        this.enumSentenceDao = enumSentenceDao;
        this.methodToDataBase = methodToDataBase;
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
    public int addMainSentence (MainSentence mainSentence, boolean processing) throws SQLException {
        return mainSentenceDao.create(mainSentence, processing);
    }

    @Override
    public MainSentence readMainSentence (int id, boolean processing) throws SQLException {
        return mainSentenceDao.read(id, processing);
    }

    @Override
    public void deleteMainSentence (int id, boolean processing) throws SQLException {
        mainSentenceDao.delete(id, processing);
    }

    @Override
    public ArrayList<MainSentence> readAllMainSentencesByStandard(int id, boolean processing) throws SQLException {
        return mainSentenceDao.readAllByStandard(id, processing);
    }

    @Override
    public int addEnumSentence(EnumSentence enumSentence, boolean processing) throws SQLException{
        return enumSentenceDao.create(enumSentence, processing);
    }

    @Override
    public EnumSentence readEnumSentence (int id, boolean processing) throws SQLException {
        return enumSentenceDao.read(id, processing);
    }

    @Override
    public void deleteEnumSentence (int id, boolean processing) throws SQLException {
        enumSentenceDao.delete(id, processing);
    }

    @Override
    public ArrayList<EnumSentence> readAllEnumSentencesByMainSentence (int id, boolean processing) throws SQLException {
        return enumSentenceDao.readAllByMainSentence(id, processing);
    }

    @Override
    public void insertProcessingMainSentence(int id) throws SQLException {
        methodToDataBase.insertProcessingMainSentence(id);
    }

}
