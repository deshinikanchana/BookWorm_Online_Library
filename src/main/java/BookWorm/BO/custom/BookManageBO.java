package BookWorm.BO.custom;

import BookWorm.BO.SuperBO;
import BookWorm.DTO.BookDto;
import BookWorm.DTO.BookTransactionDto;
import BookWorm.DTO.BranchDto;
import BookWorm.Entity.Book;
import BookWorm.Entity.BookTransaction;
import BookWorm.Entity.Branch;

import java.io.IOException;
import java.util.List;

public interface BookManageBO extends SuperBO {
    int  SaveBook(BookDto book) throws IOException;
    BookDto GetBook(int id) throws IOException;
    boolean UpdateBook(BookDto book) throws IOException;
    boolean DeleteBook(BookDto book) throws IOException;
    List<BookDto> getAllBooks() throws IOException;
    List<BranchDto> getAllBranches() throws IOException;
    List<BookTransactionDto> getAllTransactions() throws IOException;
    BranchDto getBranch(int id) throws IOException;


}
