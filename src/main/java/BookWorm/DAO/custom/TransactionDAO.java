package BookWorm.DAO.custom;

import BookWorm.DAO.CrudDAO;
import BookWorm.DTO.BookTransactionDto;
import BookWorm.Entity.BookTransaction;


import java.io.IOException;
import java.util.List;

public interface TransactionDAO extends CrudDAO<BookTransaction> {
    Object  SaveTransaction(BookTransaction bookTransaction) throws IOException;

}
