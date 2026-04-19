package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConnection {

    private static final Logger logger = LoggerConfig.getLogger(DatabaseConnection.class);

    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "322Seswesw/";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("Подключение к БД успешно");
            return connection;
        } catch (SQLException e) {
            logger.severe("Ошибка подключения к БД: " + e.getMessage());
            return null;
        }
    }
}
