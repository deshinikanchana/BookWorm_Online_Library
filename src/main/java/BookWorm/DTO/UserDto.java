package BookWorm.DTO;

import BookWorm.Entity.BookTransaction;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private int UserId;
    private String UserName;
    private String Email;
    private String Passsword;
    private List<BookTransaction> transactionList = new ArrayList<>();

    public UserDto() {
    }

    public UserDto(int userId, String userName, String email, String passsword, List<BookTransaction> transactionList) {
        UserId = userId;
        UserName = userName;
        Email = email;
        Passsword = passsword;
        this.transactionList = transactionList;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPasssword() {
        return Passsword;
    }

    public void setPasssword(String passsword) {
        Passsword = passsword;
    }

    public List<BookTransaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<BookTransaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "UserId=" + UserId +
                ", UserName='" + UserName + '\'' +
                ", Email='" + Email + '\'' +
                ", Passsword='" + Passsword + '\'' +
                '}';
    }
}
