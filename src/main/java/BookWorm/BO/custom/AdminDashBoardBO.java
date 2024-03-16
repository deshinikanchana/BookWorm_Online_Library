package BookWorm.BO.custom;

import BookWorm.BO.SuperBO;
import BookWorm.DTO.BookDto;
import BookWorm.DTO.UserDto;
import BookWorm.Entity.Book;
import BookWorm.Entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface AdminDashBoardBO extends SuperBO {
    List<UserDto> getAllUsers() throws IOException;
    List<BookDto> getAllBooks() throws IOException;
}
