package BookWorm.Controller;

import BookWorm.BO.BOFactory;
import BookWorm.BO.custom.AdminSettingBO;
import BookWorm.BO.custom.UserRegBO;
import BookWorm.DAO.custom.UserDAO;
import BookWorm.DAO.custom.impl.UserDAOimpl;
import BookWorm.DTO.UserDto;
import BookWorm.Entity.User;
import BookWorm.util.RegExPatterns;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserRegisterFormController {
    public TextField txtName;
    public TextField txtEmail;
    public TextField txtPw;
    public TextField txtConfirmPw;
    public CheckBox choiceBoxShowPw;
    public PasswordField pwFieldConfirmPw;
    public PasswordField pwFieldPw;
    public AnchorPane root;

    UserRegBO bo = (UserRegBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USERREG);
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
            UserDto user = new UserDto(1, txtName.getText(), txtEmail.getText(), pwFieldConfirmPw.getText());
           // UserRepository repo = new UserRepository();
            bo.SaveUser(user);
            onActionBtnClear(actionEvent);
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/User_Login_form.fxml"));
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
