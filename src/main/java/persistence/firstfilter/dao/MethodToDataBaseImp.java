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
    public void allMainSentenceAsProcessed(int standard) throws SQLException{
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("update main_sentence set is_processed=1 where standard="+standard);


            preparedStatement.executeUpdate();

        } catch (SQLException e) {

        }
        finally{
            dataBaseConnection.close();
        }
    }


    
}
