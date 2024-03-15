package BookWorm.DAO;

import BookWorm.Config.SessionFactoryConfig;
import BookWorm.Entity.Admin;
import BookWorm.projection.AdminProjection;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class AdminDAOimpl implements AdminDAO {


    @Override
    public int saveAdmin(Admin admin) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
        try {
            int adminId = (int) session.save(admin);
            transaction.commit();
            System.out.println("Admin save : " + admin.toString());
            session.close();
            return adminId;
        }catch (Exception ex){
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public Admin getAdmin(int id) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        try{
            Admin admin = session.get(Admin.class,id);
            session.close();
            return admin;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean UpdateAdmin(Admin ad) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(ad);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Admin> getAllAdmin() throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        String sql = "SELECT C FROM Admin As C";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<AdminProjection> getAdminProjection() throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        String sql = "SELECT\n" +
                "new BookWorm.projection.AdminProjection(C.AdminId,C.AdminName, C.Password,C.Email)\n" +
                "FROM Admin AS C";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return list;
    }
}
