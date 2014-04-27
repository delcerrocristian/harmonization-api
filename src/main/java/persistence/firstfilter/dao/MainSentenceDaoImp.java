package persistence.firstfilter.dao;

import persistence.firstfilter.DataBaseConnection;
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


        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        int id=-1;//If finally return -1 means something bad happened
        try {
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

        }
        finally{
            dataBaseConnection.close();
        }

        return id;
    }

    @Override
    public MainSentence read(int id) throws SQLException {


        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        MainSentence mainSentenceFromDB = null;
        try {
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
        }finally{
            dataBaseConnection.close();
        }
        return mainSentenceFromDB;
    }

    @Override
    public void update(MainSentence mainSentence) throws SQLException{
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    (buildSqlUpdateStatement(mainSentence));


            preparedStatement.executeUpdate();

        } catch (SQLException e) {

        }
        finally{
            dataBaseConnection.close();
        }
    }



    @Override
    public void delete(int id) throws SQLException {

        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("delete from main_sentence where id=?");
            preparedStatement.setInt(1,id);

            preparedStatement.execute();

        } catch (SQLException e) {
        }finally{
            dataBaseConnection.close();
        }
    }

    @Override
    public ArrayList<MainSentence> readAllByStandard(int id) throws SQLException {

        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        PreparedStatement preparedStatement;
        MainSentence mainSentenceFromDB;
        ArrayList<MainSentence> allMainSentencesByStandard = new ArrayList<>();
        try {
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
        }finally{
            dataBaseConnection.close();
        }
        return allMainSentencesByStandard;
    }

    private String buildSqlUpdateStatement(MainSentence mainSentence) {
        boolean anyFieldUpdate = false;
        String statement = "update main_sentence set ";
        if(mainSentence.getContent() != null){
            statement += "content="+mainSentence.getContent();
            anyFieldUpdate = true;
        }
        if (mainSentence.getCategory() != null) {
            if(anyFieldUpdate){
                statement += ",";
            }
            statement += "category="+mainSentence.getCategory();
            anyFieldUpdate = true;
        }
        if(mainSentence.getReliability() != null) {
            if(anyFieldUpdate){
                statement += ",";
            }
            statement += "reliability="+mainSentence.getReliability();
            anyFieldUpdate = true;
        }

        if(mainSentence.getStandard() != null) {
            if(anyFieldUpdate){
                statement += ",";
            }
            statement += "standard="+mainSentence.getStandard();
            anyFieldUpdate = true;
        }
        if(anyFieldUpdate) {
            statement += ",";
        }
        statement += "is_processed=1";
        statement +=" where id="+mainSentence.getId();
        return statement;

    }
}
