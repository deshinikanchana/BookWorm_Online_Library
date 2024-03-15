package BookWorm.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int AdminId;
    @Column(name = "admin_name")
    private String AdminName;
    @Column(name = "email")
    private String Email;
    @Column(name = "password")
    private String Password;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "admin")
    private List<Branch> branchList = new ArrayList<>();

    public Admin() {
    }

    public Admin(int adminId, String adminName, String email, String password, List<Branch> branchList) {
        AdminId = adminId;
        AdminName = adminName;
        Email = email;
        Password = password;
        this.branchList = branchList;
    }

    public Admin(int adminId, String adminName, String email, String password) {
        AdminId = adminId;
        AdminName = adminName;
        Email = email;
        Password = password;
    }

    public int getAdminId() {
        return AdminId;
    }

    public void setAdminId(int adminId) {
        AdminId = adminId;
    }

    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String adminName) {
        AdminName = adminName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "AdminId=" + "A00" + AdminId +
                ", AdminName='" + AdminName + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
