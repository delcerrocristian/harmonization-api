package persistence.firstfilter.dao;

import persistence.firstfilter.model.MainSentence;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public interface MainSentenceDao {

    void create(MainSentence mainSentence) throws SQLException;

    MainSentence read(int id);

    void update(MainSentence mainSentence);

    void delete(int id);

    ArrayList<MainSentence> readAllByStandard(int id);
}
