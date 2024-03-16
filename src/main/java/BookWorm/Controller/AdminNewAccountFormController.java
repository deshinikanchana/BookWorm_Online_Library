package BookWorm.Controller;

import BookWorm.BO.BOFactory;
import BookWorm.BO.custom.AdminNewAccBO;
import BookWorm.DAO.custom.AdminDAO;
import BookWorm.DAO.custom.impl.AdminDAOimpl;
import BookWorm.DTO.AdminDto;
import BookWorm.Entity.Admin;
import BookWorm.util.RegExPatterns;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminNewAccountFormController {
    public TextField txtName;
    public TextField txtEmail;
    public TextField txtPw;
    public TextField txtConfirmPw;
    public CheckBox choiceBoxShowPw;
    public PasswordField pwFieldConfirmPw;
    public PasswordField pwFieldPw;

    @FXML
    private AnchorPane root;

    AdminNewAccBO bo = (AdminNewAccBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMINNEW);

    public void onActionBtnClear(ActionEvent actionEvent) {
        txtPw.setText("");
        txtConfirmPw.setText("");
        txtEmail.setText("");
        txtName.setText("");
        pwFieldPw.setText("");
        pwFieldConfirmPw.setText("");
    }

    private boolean validateAdmin() {
        boolean isValid = true;

        if (RegExPatterns.namePattern(txtName.getText())) {
            isValid = false;
        }

        if (RegExPatterns.emailPattern(txtEmail.getText())) {
            isValid = false;
        }

        if (RegExPatterns.passwordPattern(pwFieldPw.getText())) {
            isValid = false;
        }
        return isValid;
    }

    public void onActionBtnSubmit(ActionEvent actionEvent) throws IOException {

        if(pwFieldPw.getText().equals(pwFieldConfirmPw.getText())) {
            Admin ad = new Admin(1, txtName.getText(), txtEmail.getText(), pwFieldConfirmPw.getText());
            //AdminRepository repo = new AdminRepository();
            bo.SaveAdmin(new AdminDto(ad.getAdminId(),ad.getAdminName(),ad.getEmail(),ad.getPassword(),ad.getBranchList()));
            onActionBtnClear(actionEvent);
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Admin_login_form.fxml"));
            Stage stage = (Stage) root.getScene().getWindow();

            stage.setScene(new Scene(anchorPane));
            stage.centerOnScreen();
        }else{
            new Alert(Alert.AlertType.ERROR, "Passwords not matches !!!").show();
        }
    }

    public void onActionShowPw(ActionEvent actionEvent) {
        if(choiceBoxShowPw.isSelected()){
            txtPw.setText(pwFieldPw.getText());
            pwFieldPw.setVisible(false);
            txtPw.setVisible(true);
            txtConfirmPw.setText(pwFieldConfirmPw.getText());
            pwFieldConfirmPw.setVisible(false);
            txtConfirmPw.setVisible(true);

            return;
        }
        pwFieldPw.setText(txtPw.getText());
        txtPw.setVisible(false);
        pwFieldPw.setVisible(true);
        pwFieldConfirmPw.setText(txtConfirmPw.getText());
        txtConfirmPw.setVisible(false);
        pwFieldConfirmPw.setVisible(true);
    }
}
