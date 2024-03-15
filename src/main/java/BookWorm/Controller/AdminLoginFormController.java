package BookWorm.Controller;

import BookWorm.DAO.AdminDAO;
import BookWorm.DAO.AdminDAOimpl;
import BookWorm.Entity.Admin;
import BookWorm.projection.AdminProjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminLoginFormController {
    public CheckBox showPw;
    public TextField txtUserName;
    public TextField txtPassword;
    public PasswordField pwFeildPassword;
    public AnchorPane root;
    public Hyperlink hyperLinkNewAdmin;

    public static Admin CurrentAdmin;

    private AdminDAO adminDao = new AdminDAOimpl();


    public void initialize() throws IOException {

        //AdminRepository adrep = new AdminRepository();
        List<Admin> admins = adminDao.getAllAdmin();
        if(admins.size() == 1){
            hyperLinkNewAdmin.setDisable(true);
        }else{
            txtUserName.setDisable(true);
            pwFeildPassword.setDisable(true);
            txtPassword.setDisable(true);

        }

    }

    public void onActionShowPw(ActionEvent actionEvent) {
        if(showPw.isSelected()){
            txtPassword.setText(pwFeildPassword.getText());
            pwFeildPassword.setVisible(false);
            txtPassword.setVisible(true);
            return;
        }
        pwFeildPassword.setText(txtPassword.getText());
        txtPassword.setVisible(false);
        pwFeildPassword.setVisible(true);
    }

    public void onActiontxtUserName(ActionEvent actionEvent) {

    }

    public void onActionBtnSubmit(ActionEvent actionEvent) throws IOException {
        if((txtUserName.getText()!= null) & (pwFeildPassword.getText()!= null)){
            String password = pwFeildPassword.getText();
            //AdminRepository adRep = new AdminRepository();

            List<AdminProjection> adminProj = adminDao.getAdminProjection();
            for (AdminProjection projection : adminProj) {
                if (projection.getName().equals(txtUserName.getText())) {
                    if (projection.getPw().equals(password)) {
                        CurrentAdmin = new Admin(projection.getId(), projection.getName(), projection.getEmail(), projection.getPw());
                        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Admin_db_form.fxml"));
                        Stage stage = (Stage) root.getScene().getWindow();

                        stage.setScene(new Scene(anchorPane));
                        stage.centerOnScreen();
                     return;
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Incorrect Password !!!").show();
                        return;
                    }
                } else {
                  new Alert(Alert.AlertType.ERROR, "Admin Name is Incorrect !!!").show();
                  return;
                }
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "All Fields Are Required !!!").show();
        }
    }

    public void onActionBtnClear(ActionEvent actionEvent) {
        txtUserName.setText("");
        txtPassword.setText("");
        pwFeildPassword.setText("");
    }

    public void onActionBtnBack(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Welcome_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.centerOnScreen();
    }

    public void onActionNewAdmin(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Admin_New_Account_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.centerOnScreen();
    }
}
