package services;

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
}
