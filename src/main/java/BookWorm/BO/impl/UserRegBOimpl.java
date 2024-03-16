package BookWorm.BO.impl;

import BookWorm.BO.custom.UserRegBO;
import BookWorm.DAO.DAOFactory;
import BookWorm.DAO.custom.UserDAO;
import BookWorm.DAO.custom.impl.UserDAOimpl;
import BookWorm.DTO.UserDto;
import BookWorm.Entity.User;

import java.io.IOException;

public class UserRegBOimpl implements UserRegBO {
    UserDAO us = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public int SaveUser(UserDto user) throws IOException {
        return us.Save(new User(user.getUserId(), user.getUserName(),user.getEmail(),user.getPasssword()));
    }
}
