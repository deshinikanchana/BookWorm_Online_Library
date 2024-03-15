package BookWorm.Tm;

import java.sql.Timestamp;

public class MyTransTm {
    private String bookId;
    private Timestamp borrowedDate;
    private Timestamp returnedDate;
    private String status;

    public MyTransTm() {
    }

    public MyTransTm(String bookId, Timestamp borrowedDate, Timestamp returnedDate, String status) {
        this.bookId = bookId;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
        this.status = status;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Timestamp getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Timestamp borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public Timestamp getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Timestamp returnedDate) {
        this.returnedDate = returnedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MyTransTm{" +
                "bookId='" + bookId + '\'' +
                ", borrowedDate=" + borrowedDate +
                ", returnedDate=" + returnedDate +
                ", status='" + status + '\'' +
                '}';
    }
}
