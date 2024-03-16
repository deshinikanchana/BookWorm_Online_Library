package BookWorm.BO.impl;

import BookWorm.BO.custom.BookManageBO;
import BookWorm.DAO.DAOFactory;
import BookWorm.DAO.custom.BookDAO;
import BookWorm.DAO.custom.BranchDAO;
import BookWorm.DAO.custom.TransactionDAO;
import BookWorm.DAO.custom.impl.BookDAOimpl;
import BookWorm.DAO.custom.impl.BranchDAOimpl;
import BookWorm.DAO.custom.impl.TransactionDAOimpl;
import BookWorm.DTO.BookDto;
import BookWorm.DTO.BookTransactionDto;
import BookWorm.DTO.BranchDto;
import BookWorm.Entity.Book;
import BookWorm.Entity.BookTransaction;
import BookWorm.Entity.Branch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookManageBOimpl implements BookManageBO {

    BookDAO bo = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK);
    BranchDAO br =(BranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BRANCH);
    TransactionDAO tr = (TransactionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TRANSACTION);
    @Override
    public int SaveBook(BookDto book) throws IOException {
      return bo.Save(new Book(book.getBookId(), book.getTitle(),book.getAuthor() , book.getGenre(),book.getAvailabilityStatus(),book.getBranch()));
    }

    @Override
    public BookDto GetBook(int id) throws IOException {
        Book b = bo.Get(id);
        return new BookDto(b.getBookId(),b.getTitle(),b.getAuthor(),b.getGenre(),b.getAvailabilityStatus(),b.getBranch(),b.getTransList());
    }

    @Override
    public boolean UpdateBook(BookDto book) throws IOException {
        return bo.Update(new Book(book.getBookId(), book.getTitle(), book.getAuthor(), book.getGenre(),book.getAvailabilityStatus(),book.getBranch()));
    }

    @Override
    public boolean DeleteBook(BookDto book) throws IOException {
        return bo.Delete(new Book(book.getBookId(), book.getTitle(), book.getAuthor(), book.getGenre(),book.getAvailabilityStatus(),book.getBranch()));
    }

    @Override
    public List<BookDto> getAllBooks() throws IOException {
        List<BookDto> list = new ArrayList<>();
        for(Book b:bo.GetAll()){
            list.add(new BookDto(b.getBookId(),b.getTitle(),b.getAuthor(),b.getGenre(),b.getAvailabilityStatus(),b.getBranch(),b.getTransList()));
        }
        return list;
    }

    @Override
    public List<BranchDto> getAllBranches() throws IOException {
        List<BranchDto> list = new ArrayList<>();
        for(Branch branch:br.GetAll()){
            list.add(new BranchDto(branch.getBranchId(),branch.getAddress(), branch.getEmail(),branch.getAdmin(),branch.getBookList()));
        }
        return list;
    }

    @Override
    public List<BookTransactionDto> getAllTransactions() throws IOException {
        List<BookTransactionDto> trList = new ArrayList<>();
        for(BookTransaction Tr: tr.GetAll()){
            trList.add(new BookTransactionDto(Tr.getTransactionPk(),Tr.getBorrowedDate(),Tr.getReturnedDate(),Tr.getStatus(),Tr.getUser(),Tr.getBook()));
        }
        return trList;
    }

    @Override
    public BranchDto getBranch(int id) throws IOException {
        Branch branch = br.Get(id);
        return new BranchDto(branch.getBranchId(),branch.getAddress(),branch.getEmail(),branch.getAdmin(),branch.getBookList());
    }
}
