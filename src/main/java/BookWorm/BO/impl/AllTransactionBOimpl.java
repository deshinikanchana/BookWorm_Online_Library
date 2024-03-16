package BookWorm.BO.impl;

import BookWorm.BO.custom.AllTransactionBO;
import BookWorm.DAO.DAOFactory;
import BookWorm.DAO.custom.TransactionDAO;
import BookWorm.DAO.custom.impl.TransactionDAOimpl;
import BookWorm.DTO.BookTransactionDto;
import BookWorm.Entity.BookTransaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllTransactionBOimpl implements AllTransactionBO {

    TransactionDAO tr = (TransactionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TRANSACTION);
    @Override
    public List<BookTransactionDto> GetAllTransactions() throws IOException {
        List<BookTransactionDto> trList = new ArrayList<>();
        for(BookTransaction Tr: tr.GetAll()){
            trList.add(new BookTransactionDto(Tr.getTransactionPk(),Tr.getBorrowedDate(),Tr.getReturnedDate(),Tr.getStatus(),Tr.getUser(),Tr.getBook()));
        }
         return trList;
    }

    @Override
    public boolean UpdateTransaction(BookTransactionDto trans) throws IOException {
        return tr.Update(new BookTransaction(trans.getTransactionPk(),trans.getBorrowedDate(),trans.getReturnedDate(),trans.getStatus(),trans.getUser(),trans.getBook()));
    }
}
