package persistence.firstfilter.dao;

import persistence.firstfilter.DataBaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by spukyn on 22/04/14.
 */
public class MethodToDataBaseImp implements MethodToDataBase {

    @Override
    public void insertProcessingMainSentence(int id) throws SQLException{

        int idProcessingMainSentence=-1;

        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("insert into processing_main_sentence (content, category, standard) select content, category, " +
                                    "standard from main_sentence where id=?",
                            PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if( resultSet.next() ) {
                idProcessingMainSentence = resultSet.getInt(1);
            }

            preparedStatement = dataBaseConnection.preparedStatement
                    ("insert into processing_enum_sentence (position, content, main_sentence) select position, content, ?" +
                                    " from enum_sentence where main_sentence=?");

            preparedStatement.setInt(1, idProcessingMainSentence);
            preparedStatement.setInt(2, id);



        } catch (SQLException e) {

        }
        finally{
            dataBaseConnection.close();
        }

    }

}
