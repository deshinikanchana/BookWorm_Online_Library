package BookWorm.BO.custom;

import BookWorm.BO.SuperBO;
import BookWorm.DTO.UserDto;
import BookWorm.Entity.User;

import java.io.IOException;

public interface UserRegBO extends SuperBO {
    int  SaveUser(UserDto user) throws IOException;

}
