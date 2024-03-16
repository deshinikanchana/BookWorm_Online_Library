package BookWorm.BO.custom;

import BookWorm.BO.SuperBO;
import BookWorm.DTO.UserDto;
import BookWorm.Entity.User;

import java.io.IOException;
import java.util.List;

public interface UserFormBO extends SuperBO {
    List<UserDto> getAllUsers() throws IOException;
}
