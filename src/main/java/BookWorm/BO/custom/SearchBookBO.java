package BookWorm.BO.custom;

import BookWorm.BO.SuperBO;
import BookWorm.DTO.BookDto;
import BookWorm.DTO.BookTransactionDto;
import BookWorm.Entity.Book;
import BookWorm.Entity.BookTransaction;

import java.io.IOException;
import java.util.List;

public interface SearchBookBO extends SuperBO {
    BookDto GetBook(int id) throws IOException;
    boolean UpdateBook(BookDto book) throws IOException;
    List<BookDto> getAllBooks() throws IOException;
    Object  SaveTransaction(BookTransactionDto bookTransaction) throws IOException;
}
