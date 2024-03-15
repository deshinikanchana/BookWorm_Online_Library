package BookWorm.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int UserId;

    @Column(name = "user_name")
    private String UserName;
    @Column(name = "email")
    private String Email;
    @Column(name = "password")
    private String Passsword;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    private List<BookTransaction> transactionList = new ArrayList<>();

    public User() {
    }

    public User(int userId, String userName, String email, String passsword) {
        UserId = userId;
        UserName = userName;
        Email = email;
        Passsword = passsword;
    }

    public User(int userId, String userName, String email, String passsword, List<BookTransaction> transactionList) {
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
        return "User{" +
                "UserId=" + "U00" + UserId +
                ", UserName='" + UserName + '\'' +
                ", Email='" + Email + '\'' +
                ", Passsword='" + Passsword + '\'' +
                ", transactionList=" + transactionList +
                '}';
    }
}
