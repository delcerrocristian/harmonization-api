package persistence.firstfilter.dao;

import persistence.firstfilter.model.EnumSentence;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public interface EnumSentenceDao {

    int create(EnumSentence enumSentence, boolean processing) throws SQLException;

    EnumSentence read(int i, boolean processing) throws SQLException;

    void update (EnumSentence enumSentence, boolean processing);

    void delete (int id, boolean processing) throws SQLException;

    ArrayList<EnumSentence> readAllByMainSentence(int id, boolean processing) throws SQLException;
}
