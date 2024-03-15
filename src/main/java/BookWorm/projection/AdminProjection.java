package BookWorm.projection;

public class AdminProjection {

    private int id;
    private String name;
    private String pw;

    private String email;

    public AdminProjection() {
    }

    public AdminProjection(int id, String name, String pw, String email) {
        this.id = id;
        this.name = name;
        this.pw = pw;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AdminProjection{" +
                "id=" + "A00" + id +
                ", name='" + name + '\'' +
                ", pw='" + pw + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
