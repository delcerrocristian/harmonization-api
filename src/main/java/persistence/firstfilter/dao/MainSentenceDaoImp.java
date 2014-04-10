package persistence.firstfilter.dao;

import persistence.firstfilter.Broker;
import persistence.firstfilter.DataBaseConnection;
import persistence.firstfilter.NotFreeConnectionsException;
import persistence.firstfilter.model.MainSentence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public class MainSentenceDaoImp implements MainSentenceDao {
    @Override
    public int create(MainSentence mainSentence) throws SQLException {
        DataBaseConnection dataBaseConnection = null;
        PreparedStatement preparedStatement;
        int id=-1;//If finally return -1 means something bad happened
        try {
            dataBaseConnection = Broker.get().getDataBase();
            preparedStatement = dataBaseConnection.preparedStatement
                    ("insert into main_sentence (content, category, standard) VALUES (?,?,?)",
                            PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, mainSentence.getContent());
            preparedStatement.setString(2,mainSentence.getCategory());
            preparedStatement.setInt(3, mainSentence.getStandard());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if( resultSet.next() ) {
                id = resultSet.getInt(1);
            }

        } catch (SQLException e) {
        } catch (NotFreeConnectionsException e) {
        }
        finally{
            dataBaseConnection.close();
        }

        return id;
    }

    @Override
    public MainSentence read(int id) throws SQLException {
        DataBaseConnection dataBaseConnection = null;
        PreparedStatement preparedStatement;
        MainSentence mainSentenceFromDB = null;
        try {
            dataBaseConnection = Broker.get().getDataBase();
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select * from main_sentence where id=?");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                resultSet.next();
                mainSentenceFromDB = new MainSentence();
                mainSentenceFromDB.setId(resultSet.getInt("id"));
                mainSentenceFromDB.setContent(resultSet.getString("content"));
                mainSentenceFromDB.setCategory(resultSet.getString("category"));
                mainSentenceFromDB.setStandard(resultSet.getInt("standard"));
            }

        } catch (SQLException e) {
        } catch (NotFreeConnectionsException e) {
        }finally{
            dataBaseConnection.close();
        }
        return mainSentenceFromDB;
    }

    @Override
    public void update(MainSentence mainSentence) {

    }

    @Override
    public void delete(int id) throws SQLException {
        DataBaseConnection dataBaseConnection = null;
        PreparedStatement preparedStatement;
        try {
            dataBaseConnection = Broker.get().getDataBase();
            preparedStatement = dataBaseConnection.preparedStatement
                    ("delete from main_sentence where id=?");
            preparedStatement.setInt(1,id);

            preparedStatement.execute();

        } catch (SQLException e) {
        } catch (NotFreeConnectionsException e) {
        }finally{
            dataBaseConnection.close();
        }
    }

    @Override
    public ArrayList<MainSentence> readAllByStandard(int id) throws SQLException {
        DataBaseConnection dataBaseConnection = null;
        PreparedStatement preparedStatement;
        MainSentence mainSentenceFromDB;
        ArrayList<MainSentence> allMainSentencesByStandard = new ArrayList<>();
        try {
            dataBaseConnection = Broker.get().getDataBase();
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select * from main_sentence where standard=?");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet != null){
                resultSet.next();

                mainSentenceFromDB = new MainSentence();
                mainSentenceFromDB.setId(resultSet.getInt("id"));
                mainSentenceFromDB.setContent(resultSet.getString("content"));
                mainSentenceFromDB.setCategory(resultSet.getString("category"));
                mainSentenceFromDB.setStandard(resultSet.getInt("standard"));
                allMainSentencesByStandard.add(mainSentenceFromDB);
            }

        } catch (SQLException e) {
        } catch (NotFreeConnectionsException e) {
        }finally{
            dataBaseConnection.close();
        }
        return allMainSentencesByStandard;
    }
}
