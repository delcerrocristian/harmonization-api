package persistence.firstfilter.dao;

import persistence.firstfilter.model.EnumSentence;

import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public interface EnumSentenceDao {

    void create(EnumSentence enumSentence);

    EnumSentence read(int i);

    void update (EnumSentence enumSentence);

    void delete (int i);

    ArrayList<EnumSentence> readAllByMainSentence(int i);
}
