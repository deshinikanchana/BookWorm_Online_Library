package BookWorm.Entity;

import BookWorm.embedded.TransactionPK;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "transaction")
public class BookTransaction {

    @EmbeddedId
    private TransactionPK transactionPk;
    @CreationTimestamp
    @Column(name = "borrowed_date")
    private Timestamp BorrowedDate;

    @UpdateTimestamp
    @Column(name = "returned_date")
    private Timestamp ReturnedDate;

    @Column(name = "status")
    private String Status;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id",insertable = false,updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id",referencedColumnName = "book_id",insertable = false,updatable = false)
    private Book book;

    public BookTransaction() {
    }

    public BookTransaction(TransactionPK transactionPk, Timestamp borrowedDate, Timestamp returnedDate, String status, User user, Book book) {
        this.transactionPk = transactionPk;
        BorrowedDate = borrowedDate;
        ReturnedDate = returnedDate;
        Status = status;
        this.user = user;
        this.book = book;
    }

    public BookTransaction(TransactionPK transactionPk, Timestamp borrowedDate, String status, User user, Book book) {
        this.transactionPk = transactionPk;
        BorrowedDate = borrowedDate;
        Status = status;
        this.user = user;
        this.book = book;
    }
    public BookTransaction(TransactionPK transactionPk, String status, User user, Book book) {
        this.transactionPk = transactionPk;
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
        return "Transaction{" +
                "transactionPk=" + transactionPk.toString() +
                ", BorrowedDate=" + BorrowedDate +
                ", ReturnedDate=" + ReturnedDate +
                ", Status='" + Status + '\'' +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
