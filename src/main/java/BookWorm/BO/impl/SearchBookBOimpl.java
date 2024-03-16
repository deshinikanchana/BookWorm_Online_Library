package BookWorm.BO.impl;

import BookWorm.BO.custom.SearchBookBO;
import BookWorm.DAO.DAOFactory;
import BookWorm.DAO.custom.BookDAO;
import BookWorm.DAO.custom.TransactionDAO;
import BookWorm.DAO.custom.impl.BookDAOimpl;
import BookWorm.DAO.custom.impl.TransactionDAOimpl;
import BookWorm.DTO.BookDto;
import BookWorm.DTO.BookTransactionDto;
import BookWorm.Entity.Book;
import BookWorm.Entity.BookTransaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchBookBOimpl implements SearchBookBO {
BookDAO bd = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK);
TransactionDAO tr = (TransactionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TRANSACTION);
    @Override
    public BookDto GetBook(int id) throws IOException {
        Book b = bd.Get(id);
        return new BookDto(b.getBookId(),b.getTitle(),b.getAuthor(),b.getGenre(),b.getAvailabilityStatus(),b.getBranch(),b.getTransList());

    }

    @Override
    public boolean UpdateBook(BookDto book) throws IOException {
        return bd.Update(new Book(book.getBookId(), book.getTitle(), book.getAuthor(), book.getGenre(),book.getAvailabilityStatus(),book.getBranch()));
    }

    @Override
    public List<BookDto> getAllBooks() throws IOException {
        List<BookDto> list = new ArrayList<>();
        for(Book b:bd.GetAll()){
            list.add(new BookDto(b.getBookId(),b.getTitle(),b.getAuthor(),b.getGenre(),b.getAvailabilityStatus(),b.getBranch(),b.getTransList()));
        }
        return list;
    }

    @Override
    public Object SaveTransaction(BookTransactionDto bookTransaction) throws IOException {
        return tr.GetAll();
    }
}
