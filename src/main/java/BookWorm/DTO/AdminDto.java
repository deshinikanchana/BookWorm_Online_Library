package BookWorm.DTO;

import BookWorm.Entity.Branch;

import java.util.ArrayList;
import java.util.List;

public class AdminDto {
    private int AdminId;
    private String AdminName;
    private String Email;
    private String Password;
    private List<Branch> branchList = new ArrayList<>();

    public AdminDto() {
    }

    public AdminDto(int adminId, String adminName, String email, String password, List<Branch> branchList) {
        AdminId = adminId;
        AdminName = adminName;
        Email = email;
        Password = password;
        this.branchList = branchList;
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
        return "AdminDto{" +
                "AdminId=" + AdminId +
                ", AdminName='" + AdminName + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}

