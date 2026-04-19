package example;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class ReaderDAO {

    private static final Logger logger = LoggerConfig.getLogger(ReaderDAO.class);

    public void addReader(Reader reader) {
        String sql = "INSERT INTO readers(name, book_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reader.getName());
            stmt.setObject(2, reader.getBookId());

            stmt.executeUpdate();
            logger.info("Читатель добавлен");

        } catch (SQLException e) {
            logger.severe("Ошибка добавления читателя: " + e.getMessage());
        }
    }

    public List<String> getReadersWithBooks() {
        List<String> result = new ArrayList<>();

        String sql = """
            SELECT r.name, b.title
            FROM readers r
            LEFT JOIN books b ON r.book_id = b.id
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                String title = rs.getString("title");
                result.add(name + " -> " + title);
            }

            logger.info("Читатели с книгами получены");

        } catch (SQLException e) {
            logger.severe("Ошибка получения данных: " + e.getMessage());
        }

        return result;
    }
}