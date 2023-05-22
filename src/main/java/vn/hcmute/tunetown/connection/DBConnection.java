package vn.hcmute.tunetown.connection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private final String serverName = "localhost";
    private final String dbName = "tunetown";
    private final String portNumber = "3306";
    private final String instance = "";
    private final String userID = "root";
    private final String password = "023345458";

    public Connection getConnection() throws Exception{
        String url = "jdbc:mysql://" + serverName + ":" + portNumber + "//" + instance + ";databaseName=" + dbName;
        if (instance == null || instance.trim().isEmpty()) {
            url = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName;
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, userID, password);
    }

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("tunetown");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}
