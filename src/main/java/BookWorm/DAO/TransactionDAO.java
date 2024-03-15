package BookWorm.DAO;

import BookWorm.Entity.BookTransaction;


import java.io.IOException;
import java.util.List;

public interface TransactionDAO {
    Object  SaveTransaction(BookTransaction bookTransaction) throws IOException;
    BookTransaction GetTransaction(int id) throws IOException;
    boolean UpdateTransaction(BookTransaction trans) throws IOException;
    List<BookTransaction> GetAllTransactions() throws IOException;

}
