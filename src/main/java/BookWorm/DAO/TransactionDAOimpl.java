package BookWorm.DAO;

import BookWorm.Config.SessionFactoryConfig;
import BookWorm.Entity.BookTransaction;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class TransactionDAOimpl implements TransactionDAO{
    @Override
    public Object SaveTransaction(BookTransaction bookTransaction) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
        try {
            Object transId = (Object) session.save(bookTransaction);
            transaction.commit();
            System.out.println("Transaction save : " + bookTransaction.toString());
            session.close();
            return transId;
        }catch (Exception ex){
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public BookTransaction GetTransaction(int id) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        try{
            BookTransaction trans = session.get(BookTransaction.class,id);
            session.close();
            return trans;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean UpdateTransaction(BookTransaction trans) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(trans);
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
    public List<BookTransaction> GetAllTransactions() throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        String sql = "SELECT C FROM BookTransaction As C";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return list;
    }
}
