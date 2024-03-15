package BookWorm.DTO.TM;

public class BranchTm {
    private String branchId;
    private String Address;
    private String email;

    public BranchTm() {
    }

    public BranchTm(String branchId, String addres, String email) {
        this.branchId = branchId;
        this.Address = addres;
        this.email = email;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getAddres() {
        return Address;
    }

    public void setAddres(String addres) {
        this.Address = addres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "BranchTm{" +
                "branchId='" + branchId + '\'' +
                ", addres='" + Address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
