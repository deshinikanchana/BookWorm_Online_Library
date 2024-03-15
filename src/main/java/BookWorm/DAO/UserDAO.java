package BookWorm.DAO;

import BookWorm.Entity.User;

import java.io.IOException;
import java.util.List;

public interface UserDAO {
     int  SaveUser(User user) throws IOException;
     User GetUser(int id) throws IOException;
     boolean UpdateUser(User user) throws IOException;
     boolean DeleteUser(User user) throws IOException;
     List<User> getAllUsers() throws IOException;
}
