package persistence.firstfilter.dao;

import persistence.firstfilter.Broker;
import persistence.firstfilter.DataBaseConnection;
import persistence.firstfilter.NotFreeConnectionsException;
import persistence.firstfilter.model.EnumSentence;
import persistence.firstfilter.model.MainSentence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro.
 */
public class EnumSentenceDaoImp implements EnumSentenceDao {
    @Override
    public int create(EnumSentence enumSentence) throws SQLException {
        DataBaseConnection dataBaseConnection = null;
        PreparedStatement preparedStatement;
        int id=-1;//If finally return -1 means something bad happened
        try {
            dataBaseConnection = Broker.get().getDataBase();
            preparedStatement = dataBaseConnection.preparedStatement
                    ("insert into enum_sentence (position, content, main_sentence) VALUES (?,?,?)",
                            PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, enumSentence.getPosition());
            preparedStatement.setString(2,enumSentence.getContent());
            preparedStatement.setInt(3, enumSentence.getMainSentence());

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
    public EnumSentence read(int id) throws SQLException {
        DataBaseConnection dataBaseConnection = null;
        PreparedStatement preparedStatement;
        EnumSentence enumSentenceFromDB = null;
        try {
            dataBaseConnection = Broker.get().getDataBase();
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select * from enum_sentence where id=?");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                resultSet.next();
                enumSentenceFromDB = new EnumSentence();
                enumSentenceFromDB.setPosition(resultSet.getInt("position"));
                enumSentenceFromDB.setContent(resultSet.getString("content"));
                enumSentenceFromDB.setMainSentence(resultSet.getInt("main_sentence"));
            }

        } catch (SQLException e) {
        } catch (NotFreeConnectionsException e) {
        }finally{
            dataBaseConnection.close();
        }
        return enumSentenceFromDB;
    }

    @Override
    public void update(EnumSentence enumSentence) {

    }

    @Override
    public void delete(int id) throws SQLException {
        DataBaseConnection dataBaseConnection = null;
        PreparedStatement preparedStatement;
        try {
            dataBaseConnection = Broker.get().getDataBase();
            preparedStatement = dataBaseConnection.preparedStatement
                    ("delete from enum_sentence where id=?");
            preparedStatement.setInt(1,id);

            preparedStatement.execute();

        } catch (SQLException e) {
        } catch (NotFreeConnectionsException e) {
        }finally{
            dataBaseConnection.close();
        }
    }

    @Override
    public ArrayList<EnumSentence> readAllByMainSentence(int id) throws SQLException {
        DataBaseConnection dataBaseConnection = null;
        PreparedStatement preparedStatement;
        EnumSentence enumSentenceFromDB;
        ArrayList<EnumSentence> allEnumSentencesByMainSentence = new ArrayList<>();
        try {
            dataBaseConnection = Broker.get().getDataBase();
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select * from enum_sentence where main_sentence=?");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet != null){
                resultSet.next();

                enumSentenceFromDB = new EnumSentence();
                enumSentenceFromDB.setPosition(resultSet.getInt("position"));
                enumSentenceFromDB.setContent(resultSet.getString("content"));
                enumSentenceFromDB.setMainSentence(resultSet.getInt("main_sentence"));
                allEnumSentencesByMainSentence.add(enumSentenceFromDB);
            }

        } catch (SQLException e) {
        } catch (NotFreeConnectionsException e) {
        }finally{
            dataBaseConnection.close();
        }
        return allEnumSentencesByMainSentence;
    }
}
