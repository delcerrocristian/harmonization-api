package persistence.firstfilter;

/**
 * Created by Cristian del Cerro.
 */

import java.sql.*;
import java.util.Hashtable;
import java.util.Vector;
public class Broker {
    protected static Broker instance=null;
    protected Vector<DataBaseConnection> freeConnections;
    protected Hashtable <Integer, DataBaseConnection> busyConnections;
    protected final int CONNECTIONS = 150;

    protected Broker() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        this.freeConnections = new Vector<DataBaseConnection>();
        this.busyConnections = new Hashtable<Integer, DataBaseConnection>();
        for (int i=0; i<CONNECTIONS; i++) {
            DataBaseConnection dataBaseConnection = new DataBaseConnection(i);
            this.freeConnections.add(dataBaseConnection);
        }
    }

    public static Broker get() throws SQLException {
        if (Broker.instance==null)
            instance=new Broker();
        return instance;
    }

    public DataBaseConnection getDataBase() throws SQLException, NotFreeConnectionsException {
        if (this.freeConnections.size()==0)
            throw new NotFreeConnectionsException();
        synchronized (this) {
            DataBaseConnection result=this.freeConnections.remove(0);
            this.busyConnections.put(result.getId(),result);
            return result;
        }
    }

    public void liberate(DataBaseConnection dataBaseConnection) {
        synchronized (this) {
            this.busyConnections.remove(dataBaseConnection.getId());
            this.freeConnections.add(dataBaseConnection);
        }
    }
}
