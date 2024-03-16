package BookWorm.BO.impl;

import BookWorm.BO.custom.AdminSettingBO;
import BookWorm.DAO.DAOFactory;
import BookWorm.DAO.custom.AdminDAO;
import BookWorm.DAO.custom.impl.AdminDAOimpl;
import BookWorm.projection.AdminProjection;

import java.io.IOException;
import java.util.List;

public class AdminSettingBOimpl implements AdminSettingBO {

    AdminDAO ad = (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADMIN);
    @Override
    public List<AdminProjection> getAdminProjection() throws IOException {
        return ad.getAdminProjection();
    }
}
