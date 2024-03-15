package BookWorm.DTO;

import BookWorm.Entity.Book;
import BookWorm.Entity.User;
import BookWorm.embedded.TransactionPK;

import java.sql.Timestamp;

public class BookTransactionDto {
    private TransactionPK transactionPk;
    private Timestamp BorrowedDate;
    private Timestamp ReturnedDate;
    private String Status;

    private User user;
    private Book book;

    public BookTransactionDto() {
    }

    public BookTransactionDto(TransactionPK transactionPk, Timestamp borrowedDate, Timestamp returnedDate, String status, User user, Book book) {
        this.transactionPk = transactionPk;
        BorrowedDate = borrowedDate;
        ReturnedDate = returnedDate;
        Status = status;
        this.user = user;
        this.book = book;
    }

    public TransactionPK getTransactionPk() {
        return transactionPk;
    }

    public void setTransactionPk(TransactionPK transactionPk) {
        this.transactionPk = transactionPk;
    }

    public Timestamp getBorrowedDate() {
        return BorrowedDate;
    }

    public void setBorrowedDate(Timestamp borrowedDate) {
        BorrowedDate = borrowedDate;
    }

    public Timestamp getReturnedDate() {
        return ReturnedDate;
    }

    public void setReturnedDate(Timestamp returnedDate) {
        ReturnedDate = returnedDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookTransactionDto{" +
                "transactionPk=" + transactionPk.toString() +
                ", BorrowedDate=" + BorrowedDate +
                ", ReturnedDate=" + ReturnedDate +
                ", Status='" + Status + '\'' +
                '}';
    }
}
