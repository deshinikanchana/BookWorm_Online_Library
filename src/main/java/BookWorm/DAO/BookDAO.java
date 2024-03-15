package BookWorm.DAO;

import BookWorm.Entity.Book;


import java.io.IOException;
import java.util.List;

public interface BookDAO {
    public int  SaveBook(Book book) throws IOException;
    public Book GetBook(int id) throws IOException;
    public boolean UpdateBook(Book book) throws IOException;
    public boolean DeleteBook(Book book) throws IOException;
    List<Book> getAllBooks() throws IOException;
}
