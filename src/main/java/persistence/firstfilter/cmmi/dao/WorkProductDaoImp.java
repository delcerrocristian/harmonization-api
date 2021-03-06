package persistence.firstfilter.cmmi.dao;

import persistence.firstfilter.cmmi.model.WorkProduct;
import persistence.firstfilter.dataBaseConnection.CmmiDataBaseConnection;
import persistence.firstfilter.dataBaseConnection.DataBaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Cristian del Cerro
 */
public class WorkProductDaoImp implements WorkProductDao {
    @Override
    public int create(WorkProduct workProduct) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        int id = -1;

        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("insert into work_product (description, specific_practice) " +
                            "VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, workProduct.getDescription());
            preparedStatement.setInt(2, workProduct.getSpecificPractice());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        }
        catch (SQLException sqlException) {
            System.out.println("SQLException happened executing create to work product");
        }
        finally {
            dataBaseConnection.close();
        }
        return id;

    }

    @Override
    public WorkProduct read(int id) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        WorkProduct workProduct = null;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select * from work_product where id=?");
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                resultSet.next();
                workProduct = new WorkProduct();
                workProduct.setId(resultSet.getInt("id"));
                workProduct.setDescription(resultSet.getString("description"));
                workProduct.setSpecificPractice(resultSet.getInt("specific_practice"));
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select to work product");

        }finally{
            dataBaseConnection.close();
        }
        return workProduct;
    }

    @Override
    public void update(WorkProduct workProduct) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("update work_product set " +
                            "description=?, specific_practice=? " +
                            "where id=?");

            preparedStatement.setString(1, workProduct.getDescription());
            preparedStatement.setInt(2, workProduct.getSpecificPractice());
            preparedStatement.setInt(3, workProduct.getId());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println("SQLException happened executing update of work product");
        }finally{
            dataBaseConnection.close();
        }
    }

    @Override
    public void delete(int id) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("delete from work_product where id=?");
            preparedStatement.setInt(1,id);

            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("SQLException happened executing delete to work product");
        }finally{
            dataBaseConnection.close();
        }

    }

    @Override
    public ArrayList<WorkProduct> readAllBySpecificPractice(int specificPractice) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        WorkProduct workProduct;
        ArrayList<WorkProduct> allWorkProduct= new ArrayList<>();
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select * from work_product where specific_practice="+specificPractice);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet != null){
                resultSet.next();

                workProduct = new WorkProduct(resultSet.getInt("id"),resultSet.getString("description"),
                        resultSet.getInt("specific_practice"));
                allWorkProduct.add(workProduct);
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select all specific practice");
        }finally{
            dataBaseConnection.close();
        }
        return allWorkProduct;
    }

    @Override
    public ArrayList<WorkProduct> readAllByStandard(int standard) {
        DataBaseConnection dataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        WorkProduct workProduct;
        ArrayList<WorkProduct> allWorkProduct= new ArrayList<>();
        try {
            preparedStatement = dataBaseConnection.preparedStatement
                    ("select work_product.id, work_product.description, work_product.specific_practice from work_product," +
                            "specific_practice, specific_goal, process where work_product.specific_practice=specific_practice.id " +
                            "and specific_practice.specific_goal= specific_goal.id and specific_goal.process=process.id and " +
                            "process.standard=?");

            preparedStatement.setInt(1, standard);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                workProduct = new WorkProduct(resultSet.getInt("id"),resultSet.getString("description"),
                        resultSet.getInt("specific_practice"));
                allWorkProduct.add(workProduct);
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select all specific practice");
        }finally{
            dataBaseConnection.close();
        }
        return allWorkProduct;
    }

    @Override
    public int countByStandard(int id) {
        CmmiDataBaseConnection cmmiDataBaseConnection = new CmmiDataBaseConnection();
        PreparedStatement preparedStatement;
        int numWorkProduct = 0;
        try {
            preparedStatement = cmmiDataBaseConnection.preparedStatement
                    ("select count(*) as num from work_product where specific_practice in (select id from specific_practice " +
                            "where specific_goal in (select id from specific_goal where process in (select id from " +
                            "process where standard="+id+")))");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                numWorkProduct=  resultSet.getInt("num");
            }

        } catch (SQLException e) {
            System.out.println("SQLException happened executing select count process");
        }finally{
            cmmiDataBaseConnection.close();
        }
        return numWorkProduct;
    }

}
