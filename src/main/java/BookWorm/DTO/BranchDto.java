package BookWorm.DTO;

import BookWorm.Entity.Admin;
import BookWorm.Entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BranchDto {

    private int BranchId;
    private String Address;

    private String Email;

    private Admin admin;

    private List<Book> BookList = new ArrayList<>();

    public BranchDto() {
    }

    public BranchDto(int branchId, String address, String email, Admin admin, List<Book> bookList) {
        BranchId = branchId;
        Address = address;
        Email = email;
        this.admin = admin;
        BookList = bookList;
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
        return "BranchDto{" +
                "BranchId=" + BranchId +
                ", Address='" + Address + '\'' +
                ", Email='" + Email + '\'' +
                ", admin=" + admin +
                '}';
    }
}
