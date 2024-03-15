package BookWorm.DTO.TM;

import javafx.scene.control.Button;

import java.sql.Timestamp;

public class TransactionTm {
    private String BookId;
    private String UserId;
    private Timestamp BorrowedDate;
    private Timestamp ReturnedDate;
    private String Status;

    private Button btn;

    public TransactionTm() {
    }

    public TransactionTm(String bookId, String userId, Timestamp borrowedDate, Timestamp returnedDate, String status, Button btn) {
        BookId = bookId;
        UserId = userId;
        BorrowedDate = borrowedDate;
        ReturnedDate = returnedDate;
        Status = status;
        this.btn = btn;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "TransactionTm{" +
                "BookId='" + BookId + '\'' +
                ", UserId='" + UserId + '\'' +
                ", BorrowedDate=" + BorrowedDate +
                ", ReturnedDate=" + ReturnedDate +
                ", Status='" + Status + '\'' +
                ", btn=" + btn +
                '}';
    }
}
