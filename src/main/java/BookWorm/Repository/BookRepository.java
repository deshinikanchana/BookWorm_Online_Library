package BookWorm.Repository;

import BookWorm.Config.SessionFactoryConfig;
import BookWorm.Entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class BookRepository {
    private final Session session;

    public BookRepository() throws IOException {
        session= SessionFactoryConfig
                .getInstance()
                .getSession();
    }

    public int  SaveBook(Book book){
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

    public Book GetBook(int id){
        try{
            Book book = session.get(Book.class,id);
            session.close();
            return book;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public boolean UpdateBook(Book book){
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

    public boolean DeleteBook(Book book){
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

    //jpql - java persistance query language
    public List<Book> getAllBooks(){
        String sql = "SELECT C FROM Book As C";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return list;

    }
}
