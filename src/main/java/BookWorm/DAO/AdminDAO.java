package BookWorm.DAO;

import BookWorm.Entity.Admin;
import BookWorm.projection.AdminProjection;

import java.io.IOException;
import java.util.List;

public interface AdminDAO {
     int  saveAdmin(Admin admin) throws IOException;
     Admin getAdmin(int id) throws IOException;
     boolean UpdateAdmin(Admin ad) throws IOException;
     List<Admin> getAllAdmin() throws IOException;
     List<AdminProjection> getAdminProjection() throws IOException;
}
