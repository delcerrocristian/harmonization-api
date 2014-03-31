package persistence.firstfilter.dao;

import persistence.firstfilter.model.MainSentence;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public interface MainSentenceDao {

    void create(MainSentence mainSentence) throws SQLException;

    MainSentence read(int id) throws SQLException;

    void update(MainSentence mainSentence);

    void delete(int id) throws SQLException;

    ArrayList<MainSentence> readAllByStandard(int id) throws SQLException;
}
