package persistence.firstfilter.dao;

import persistence.firstfilter.Broker;
import persistence.firstfilter.DataBaseConnection;
import persistence.firstfilter.NotFreeConnectionsException;
import persistence.firstfilter.model.MainSentence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public class MainSentenceDaoImp implements MainSentenceDao {
    @Override
    public void create(MainSentence mainSentence) throws SQLException {
        DataBaseConnection dataBaseConnection = null;
        PreparedStatement preparedStatement;
        try {
            dataBaseConnection = Broker.get().getDataBase();
            preparedStatement = dataBaseConnection.preparedStatement
                    ("insert into main_sentence (content, category, standard) VALUES (?,?,?)");
            preparedStatement.setString(1, mainSentence.getContent());
            preparedStatement.setString(2,mainSentence.getCategory());
            preparedStatement.setInt(3, mainSentence.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        } catch (NotFreeConnectionsException e) {
        }
        finally{
            dataBaseConnection.close();
        }
    }

    @Override
    public MainSentence read(int id) {
        return null;
    }

    @Override
    public void update(MainSentence mainSentence) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ArrayList<MainSentence> readAllByStandard(int id) {
        return null;
    }
}
