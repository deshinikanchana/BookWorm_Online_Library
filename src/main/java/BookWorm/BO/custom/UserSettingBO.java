package BookWorm.BO.custom;

import BookWorm.BO.SuperBO;
import BookWorm.DTO.UserDto;
import BookWorm.Entity.User;

import java.io.IOException;
import java.util.List;

public interface UserSettingBO extends SuperBO {
    UserDto GetUser(int id) throws IOException;
    boolean UpdateUser(UserDto user) throws IOException;
    boolean DeleteUser(UserDto user) throws IOException;
    List<UserDto> getAllUsers() throws IOException;
}
