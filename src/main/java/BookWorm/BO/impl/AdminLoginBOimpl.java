package BookWorm.BO.impl;

import BookWorm.BO.custom.AdminLoginBO;
import BookWorm.DAO.DAOFactory;
import BookWorm.DAO.custom.AdminDAO;
import BookWorm.DAO.custom.impl.AdminDAOimpl;
import BookWorm.DTO.AdminDto;
import BookWorm.Entity.Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminLoginBOimpl implements AdminLoginBO {
    AdminDAO ad = (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADMIN);
    @Override
    public List<AdminDto> GetAllAdmins() throws IOException {
        List<AdminDto> adList = new ArrayList<>();
        for(Admin admin:ad.GetAll()){
            adList.add(new AdminDto(admin.getAdminId(),admin.getAdminName(),admin.getEmail(),admin.getPassword(),admin.getBranchList()));
        }
        return adList;
    }
}
