package BookWorm.DAO;

import BookWorm.Config.SessionFactoryConfig;
import BookWorm.Entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class UserDAOimpl implements UserDAO{
    @Override
    public int SaveUser(User user) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
        try {
            int userId = (int) session.save(user);
            transaction.commit();
            System.out.println("User save : " + user.toString());
            session.close();
            return userId;
        }catch (Exception ex){
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public User GetUser(int id) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        try{
            User user = session.get(User.class,id);
            session.close();
            return user;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean UpdateUser(User user) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction trans = session.beginTransaction();
        try{
            session.update(user);
            trans.commit();
            session.close();
            return true;
        }catch (Exception e){
            trans.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean DeleteUser(User user) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction delTrans = session.beginTransaction();
        try{
            session.delete(user);
            delTrans.commit();
            session.close();
            return true;
        }catch (Exception e){
            delTrans.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> getAllUsers() throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        String sql = "SELECT C FROM User As C";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return list;
    }
}
