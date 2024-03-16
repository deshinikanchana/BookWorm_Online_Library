package BookWorm.DAO.custom;

import BookWorm.DAO.CrudDAO;
import BookWorm.DTO.AdminDto;
import BookWorm.Entity.Admin;
import BookWorm.projection.AdminProjection;

import java.io.IOException;
import java.util.List;

public interface AdminDAO extends CrudDAO<Admin> {
     List<AdminProjection> getAdminProjection() throws IOException;
}
