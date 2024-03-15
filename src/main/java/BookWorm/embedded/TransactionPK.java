package BookWorm.embedded;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class TransactionPK implements Serializable {


    @Column(name = "user_id")
    private int userId;

    @Column(name = "book_id")
    private int bookId;

    public TransactionPK() {
    }

    public TransactionPK( int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "TransactionPK{" +
                ", userId=" + userId +
                ", bookId=" + bookId +
                '}';
    }
}
