package persistence.firstfilter.iso.dao;

import persistence.firstfilter.dataBaseConnection.IsoDataBaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by spukyn on 22/04/14.
 */
public class MethodToDataBaseImp implements MethodToDataBase {

    @Override
    public void allMainSentenceAsProcessed(int standard) throws SQLException{
        IsoDataBaseConnection isoDataBaseConnection = new IsoDataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = isoDataBaseConnection.preparedStatement
                    ("update main_sentence set is_processed=1 where standard="+standard);


            preparedStatement.executeUpdate();

        } catch (SQLException e) {

        }
        finally{
            isoDataBaseConnection.close();
        }
    }


    
}
