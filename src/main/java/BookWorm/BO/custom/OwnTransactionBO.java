package BookWorm.BO.custom;

import BookWorm.BO.SuperBO;
import BookWorm.DTO.BookTransactionDto;
import BookWorm.Entity.BookTransaction;

import java.io.IOException;
import java.util.List;

public interface OwnTransactionBO extends SuperBO {
    List<BookTransactionDto> GetAllTransactions() throws IOException;
}
