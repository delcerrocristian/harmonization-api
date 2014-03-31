package persistence.firstfilter.dao;

import persistence.firstfilter.model.EnumSentence;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public interface EnumSentenceDao {

    void create(EnumSentence enumSentence) throws SQLException;

    EnumSentence read(int i) throws SQLException;

    void update (EnumSentence enumSentence);

    void delete (int id) throws SQLException;

    ArrayList<EnumSentence> readAllByMainSentence(int id) throws SQLException;
}
