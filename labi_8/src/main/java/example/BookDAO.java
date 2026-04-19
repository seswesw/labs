package example;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class BookDAO {

    private static final Logger logger = LoggerConfig.getLogger(BookDAO.class);

    public void addBook(Book book) {
        String sql = "INSERT INTO books(title, author, year) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getYear());

            stmt.executeUpdate();
            logger.info("Книга добавлена");

        } catch (SQLException e) {
            logger.severe("Ошибка добавления книги: " + e.getMessage());
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setYear(rs.getInt("year"));
                books.add(book);
            }

            logger.info("Список книг получен");

        } catch (SQLException e) {
            logger.severe("Ошибка получения книг: " + e.getMessage());
        }

        return books;
    }
}