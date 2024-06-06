package ba.sinergija.projekatpj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;

public class Database {
    
    private Connection connection;
    
    public boolean connect() {
        try {
            String dbUser = "root";
            String dbPassword = "";
            String url = "jdbc:mariadb://localhost";
            int port = 3306;
            String dbName = "hemijska_cistionica";
            url = url + ":" + port + "/" + dbName;
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
            System.out.println("Database connected successfully.");
            return true;
        } catch (SQLException exception) {
            Logger.getLogger(LoginFrame.class.getName()).log(SEVERE, "Failed to connect to database", exception);
            return false;
        }
    }
    
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException exception) {
                Logger.getLogger(Database.class.getName()).log(SEVERE, "Failed to disconnect from database", exception);
            }
        }
        System.out.println("Database disconnected successfully.");
    }

    public Connection getConnection() {
        return connection;
    }
    
}
