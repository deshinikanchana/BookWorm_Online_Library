package BookWorm.BO.impl;

import BookWorm.BO.custom.AdminNewAccBO;
import BookWorm.DAO.DAOFactory;
import BookWorm.DAO.custom.AdminDAO;
import BookWorm.DAO.custom.impl.AdminDAOimpl;
import BookWorm.DTO.AdminDto;
import BookWorm.Entity.Admin;

import java.io.IOException;

public class AdminNewAccBOimpl implements AdminNewAccBO {

    AdminDAO ad = (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADMIN);
    @Override
    public int SaveAdmin(AdminDto admin) throws IOException {
       return ad.Save(new Admin(admin.getAdminId(), admin.getAdminName(), admin.getEmail(),admin.getPassword()));
    }
}
