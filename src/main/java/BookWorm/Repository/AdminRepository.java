package BookWorm.Repository;

import BookWorm.Config.SessionFactoryConfig;
import BookWorm.Entity.Admin;
import BookWorm.projection.AdminProjection;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class AdminRepository {
    private final Session session;

    public AdminRepository() throws IOException {
        session= SessionFactoryConfig
                .getInstance()
                .getSession();
    }

    public int  saveAdmin(Admin admin){
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

    public Admin getAdmin(int id){
        try{
            Admin admin = session.get(Admin.class,id);
            session.close();
            return admin;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public boolean UpdateAdmin(Admin ad){
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

    //jpql - java persistance query language
    public List<Admin> getAllAdmin(){
        String sql = "SELECT C FROM Admin As C";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return list;

    }

    public List<AdminProjection> getAdminProjection() {
        String sql = "SELECT\n" +
                "new BookWorm.projection.AdminProjection(C.AdminId,C.AdminName, C.Password,C.Email)\n" +
                "FROM Admin AS C";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return list;
    }
}
