package BookWorm.BO.impl;

import BookWorm.BO.custom.UserLoginBO;
import BookWorm.DAO.DAOFactory;
import BookWorm.DAO.custom.UserDAO;
import BookWorm.DAO.custom.impl.UserDAOimpl;
import BookWorm.DTO.UserDto;
import BookWorm.Entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserLoginBOimpl implements UserLoginBO {
    UserDAO us = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public List<UserDto> getAllUsers() throws IOException {
        List<UserDto> user = new ArrayList<>();
        for(User usr:us.GetAll()){
            user.add(new UserDto(usr.getUserId(),usr.getUserName(),usr.getEmail(),usr.getPasssword(),usr.getTransactionList()));
        }
        return user;
    }
}
