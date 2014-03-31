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

    void createStandard (Standard standard) throws SQLException;

    Standard readStandard(int id) throws SQLException;

    void deleteStandard(int id) throws SQLException;

    ArrayList<Standard> readAllStandards() throws SQLException;

    void addMainSentence (MainSentence mainSentence) throws SQLException;

    MainSentence readMainSentence (int id) throws SQLException;

    void deleteMainSentence (int id) throws SQLException;

    ArrayList<MainSentence> readAllMainSentencesByStandard(int id) throws SQLException;

    EnumSentence readEnumSentence (int id) throws SQLException;

    void deleteEnumSentence (int id) throws SQLException;

    ArrayList<EnumSentence> readAllEnumSentencesByMainSentence (int id) throws SQLException;
}
