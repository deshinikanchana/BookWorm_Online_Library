package BookWorm.BO.impl;

import BookWorm.BO.custom.AdminDashBoardBO;
import BookWorm.DAO.DAOFactory;
import BookWorm.DAO.custom.BookDAO;
import BookWorm.DAO.custom.UserDAO;
import BookWorm.DAO.custom.impl.BookDAOimpl;
import BookWorm.DTO.BookDto;
import BookWorm.DTO.UserDto;
import BookWorm.Entity.Book;
import BookWorm.Entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminDashBoardBOimpl implements AdminDashBoardBO {

UserDAO us = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
BookDAO book = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK);
    @Override
    public List<UserDto> getAllUsers() throws IOException {
      List<UserDto> dto = new ArrayList<>();
      for(User user :us.GetAll()){
          dto.add(new UserDto(user.getUserId(), user.getUserName(), user.getEmail(),user.getPasssword(),user.getTransactionList()));
      }
      return dto;
    }

    @Override
    public List<BookDto> getAllBooks() throws IOException {
        List<BookDto> booklist = new ArrayList<>();
        for(Book b:book.GetAll()){
            booklist.add(new BookDto(b.getBookId(),b.getTitle(),b.getAuthor(),b.getGenre(),b.getAvailabilityStatus(),b.getBranch(),b.getTransList()));
        }
        return booklist;
    }
}
