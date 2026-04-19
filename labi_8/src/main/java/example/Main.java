package example;
public class Main {
    public static void main(String[] args) {

        BookDAO bookDAO = new BookDAO();
        ReaderDAO readerDAO = new ReaderDAO();

        Book book = new Book();
        book.setTitle("1984");
        book.setAuthor("George Orwell");
        book.setYear(1949);

        bookDAO.addBook(book);

        Reader reader = new Reader();
        reader.setName("Иван Иванов");
        reader.setBookId(1);

        readerDAO.addReader(reader);

        bookDAO.getAllBooks().forEach(b ->
                System.out.println(b.getTitle())
        );

        readerDAO.getReadersWithBooks().forEach(System.out::println);
    }
}