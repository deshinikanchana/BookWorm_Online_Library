package BookWorm.Controller;

import BookWorm.BO.BOFactory;
import BookWorm.BO.custom.AdminLoginBO;
import BookWorm.DTO.AdminDto;
import BookWorm.Entity.Admin;
import BookWorm.util.RegExPatterns;
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

    AdminLoginBO bo = (AdminLoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMINLOG);


    public void initialize() throws IOException {

        //AdminRepository adrep = new AdminRepository();
        List<AdminDto> admins = bo.GetAllAdmins();
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

            List<AdminDto> adminProj = bo.GetAllAdmins();
            for (AdminDto admin : adminProj) {
                if (admin.getAdminName().equals(txtUserName.getText())) {
                    if (admin.getPassword().equals(password)) {
                        CurrentAdmin = new Admin(admin.getAdminId(), admin.getAdminName(), admin.getEmail(), admin.getPassword());
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

    private boolean validateAdmin() {
        boolean isValid = true;

        if (RegExPatterns.namePattern(txtUserName.getText())) {
            isValid = false;
        }

        if (RegExPatterns.passwordPattern(pwFeildPassword.getText())) {
            isValid = false;
        }
        return isValid;
    }
}
