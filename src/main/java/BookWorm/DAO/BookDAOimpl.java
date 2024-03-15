package BookWorm.DAO;

import BookWorm.Config.SessionFactoryConfig;
import BookWorm.Entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class BookDAOimpl implements BookDAO{
    @Override
    public int SaveBook(Book book) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            int bookId = (int) session.save(book);
            transaction.commit();
            System.out.println("Book save : " + book.toString());
            session.close();
            return bookId;
        }catch (Exception ex){
            System.out.println("Roll Back wenne ai ? line 30 bookrepo");
            //transaction.rollback();
            session.close();
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public Book GetBook(int id) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        try{
            Book book = session.get(Book.class,id);
            session.close();
            return book;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean UpdateBook(Book book) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction trans = session.beginTransaction();
        try{
            session.update(book);
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
    public boolean DeleteBook(Book book) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        Transaction delTrans = session.beginTransaction();
        try{
            session.delete(book);
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
    public List<Book> getAllBooks() throws IOException {
       Session session= SessionFactoryConfig.getInstance().getSession();
        String sql = "SELECT C FROM Book As C";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return list;
    }
}
