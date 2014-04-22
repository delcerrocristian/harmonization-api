package services;

import persistence.firstfilter.model.EnumSentence;
import persistence.firstfilter.model.MainSentence;
import persistence.firstfilter.model.Standard;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public interface FirstFilterService {

    int createStandard (Standard standard) throws SQLException;

    int createStandard (String nameStandard) throws SQLException;

    Standard readStandard(int id) throws SQLException;

    void deleteStandard(int id) throws SQLException;

    ArrayList<Standard> readAllStandards() throws SQLException;

    int addMainSentence (MainSentence mainSentence, boolean procesing) throws SQLException;

    MainSentence readMainSentence (int id, boolean processing) throws SQLException;

    void deleteMainSentence (int id, boolean processing) throws SQLException;

    ArrayList<MainSentence> readAllMainSentencesByStandard(int id, boolean processing) throws SQLException;

    int addEnumSentence(EnumSentence enumSentence, boolean processing) throws SQLException;

    EnumSentence readEnumSentence (int id, boolean processing) throws SQLException;

    void deleteEnumSentence (int id, boolean processing) throws SQLException;

    ArrayList<EnumSentence> readAllEnumSentencesByMainSentence (int id, boolean processing) throws SQLException;

    void insertProcessingMainSentence(int id) throws SQLException;
}
