package BookWorm.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private int BranchId;
    @Column(name = "address")
    private String Address;

    @Column(name = "email")
    private String Email;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "branch")
    private List<Book> BookList = new ArrayList<>();

    public Branch() {
    }

    public Branch(int branchId, String address, String email, Admin admin, List<Book> bookList) {
        BranchId = branchId;
        Address = address;
        Email = email;
        this.admin = admin;
        BookList = bookList;
    }

    public Branch(int branchId, String address, String email,Admin admin) {
        BranchId = branchId;
        Address = address;
        Email = email;
        this.admin = admin;
    }

    public int getBranchId() {
        return BranchId;
    }

    public void setBranchId(int branchId) {
        BranchId = branchId;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Book> getBookList() {
        return BookList;
    }

    public void setBookList(List<Book> bookList) {
        BookList = bookList;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "BranchId=" + "Br00" + BranchId +
                ", Address='" + Address + '\'' +
                ", Email='" + Email + '\'' +
                ", admin=" + admin +
                '}';
    }
}
