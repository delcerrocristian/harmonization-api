package persistence.firstfilter.dao;

import persistence.firstfilter.model.MainSentence;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public interface MainSentenceDao {

    int create(MainSentence mainSentence, boolean processing) throws SQLException;

    MainSentence read(int id, boolean processing) throws SQLException;

    void update(MainSentence mainSentence, boolean processing);

    void delete(int id, boolean processing) throws SQLException;

    ArrayList<MainSentence> readAllByStandard(int id, boolean processing) throws SQLException;
}
