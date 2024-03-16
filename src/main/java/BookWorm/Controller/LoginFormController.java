package BookWorm.Controller;

import BookWorm.BO.BOFactory;
import BookWorm.BO.custom.AdminSettingBO;
import BookWorm.BO.custom.UserLoginBO;
import BookWorm.DAO.custom.UserDAO;
import BookWorm.DAO.custom.impl.UserDAOimpl;
import BookWorm.DTO.UserDto;
import BookWorm.Entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class LoginFormController {
    public CheckBox showPw;
    public TextField txtUserName;
    public TextField txtPassword;
    public PasswordField pwFieldPassword;
    public Hyperlink hyperlinkLink;

    public AnchorPane root;
    public static UserDto currentUser;

    UserLoginBO bo = (UserLoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USERLOG);

    public void onActionShowPw(ActionEvent actionEvent) {
        if(showPw.isSelected()){
            txtPassword.setText(pwFieldPassword.getText());
            pwFieldPassword.setVisible(false);
            txtPassword.setVisible(true);
            return;
        }
        pwFieldPassword.setText(txtPassword.getText());
        txtPassword.setVisible(false);
        pwFieldPassword.setVisible(true);
    }

    public void onActiontxtUserName(ActionEvent actionEvent) {
    }


    public void onActionHyperLink(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/user_register_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.centerOnScreen();
    }

    public void onActionBtnSubmit(ActionEvent actionEvent) throws IOException {
        if((txtUserName.getText()!= null) & (pwFieldPassword.getText()!= null)){
            String password = pwFieldPassword.getText();
            //UserRepository userRepo = new UserRepository();

            List<UserDto> userList = bo.getAllUsers();
            for (UserDto user : userList) {
                if (user.getUserName().equals(txtUserName.getText())) {
                    if (user.getPasssword().equals(password)) {
                        currentUser = user;
                        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/User_db_form.fxml"));
                        Stage stage = (Stage) root.getScene().getWindow();

                        stage.setScene(new Scene(anchorPane));
                        stage.centerOnScreen();
                        return;
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Incorrect Password !!!").show();
                        return;
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "User Name is Incorrect !!!").show();
                    return;
                }
            }
        }else{
            new Alert(Alert.AlertType.WARNING, "All Fields Are Required !!!").show();
        }
    }

    public void onActionBtnClear(ActionEvent actionEvent) {
        txtPassword.setText("");
        txtPassword.setText("");
        pwFieldPassword.setText("");
    }

    public void onActionBtnBack(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Welcome_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.centerOnScreen();
    }
}
