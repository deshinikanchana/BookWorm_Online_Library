package BookWorm.BO.impl;

import BookWorm.BO.custom.UserSettingBO;
import BookWorm.DAO.DAOFactory;
import BookWorm.DAO.custom.UserDAO;
import BookWorm.DAO.custom.impl.UserDAOimpl;
import BookWorm.DTO.UserDto;
import BookWorm.Entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserSettingBOimpl implements UserSettingBO {

    UserDAO us = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public UserDto GetUser(int id) throws IOException {
        User user = us.Get(id);
        return new UserDto(user.getUserId(),user.getUserName(),user.getEmail(),user.getPasssword(),user.getTransactionList());
    }

    @Override
    public boolean UpdateUser(UserDto user) throws IOException {
        return us.Update(new User(user.getUserId(),user.getUserName(),user.getEmail(),user.getPasssword(),user.getTransactionList()));
    }

    @Override
    public boolean DeleteUser(UserDto user) throws IOException {
        return us.Delete(new User(user.getUserId(),user.getUserName(),user.getEmail(),user.getPasssword(),user.getTransactionList()));
    }

    @Override
    public List<UserDto> getAllUsers() throws IOException {
        List<UserDto> user = new ArrayList<>();
        for(User usr:us.GetAll()){
            user.add(new UserDto(usr.getUserId(),usr.getUserName(),usr.getEmail(),usr.getPasssword(),usr.getTransactionList()));
        }
        return user;
    }
}
