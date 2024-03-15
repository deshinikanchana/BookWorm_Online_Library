package BookWorm.Controller;

import BookWorm.Entity.User;
import BookWorm.Repository.UserRepository;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static BookWorm.Controller.LoginFormController.currentUser;

public class UserSettingFormController {
    public Label lblUserName;
    public TextField txtCurrentPw;
    public TextField txtNewPw;
    public TextField txtConfirmNewPw;
    public PasswordField pwFieldPw;
    public PasswordField pwFieldNewPw;
    public PasswordField pwFieldConfirmPw;
    public CheckBox selectBoxShowPw;
    public JFXButton btnSubmit;
    public JFXButton btnClear;
    public AnchorPane root;
    public JFXButton btnDeleteAcc;

    public void initialize(){
        lblUserName.setText(currentUser.getUserName());

    }
    public void onActionBtnSubmit(ActionEvent actionEvent) throws IOException {
        UserRepository userRepo = new UserRepository();
        if(pwFieldPw.getText() != null){
            List<User> userList = userRepo.getAllUsers();
            for (User us : userList) {
                if (us.getUserName().equals(currentUser.getUserName())) {
                    if (us.getPasssword().equals(pwFieldPw.getText())) {
                        if((pwFieldConfirmPw.getText() != null) & (pwFieldNewPw.getText().equals(pwFieldConfirmPw.getText()))) {
                            User usr = new User(us.getUserId(),us.getUserName(),us.getEmail(),pwFieldConfirmPw.getText());
                            userRepo = new UserRepository();

                            if( userRepo.UpdateUser(usr)){
                                onActionBtnClear(actionEvent);
                                return;
                            }
                            return;
                        }else{
                            new Alert(Alert.AlertType.ERROR, "Passwords Are Not Match !!!").show();
                            return;
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Incorrect Password !!!").show();
                        return;
                    }
                }
            }

        }else{
            new Alert(Alert.AlertType.WARNING, "All Fields Are Required !!!").show();
        }
    }

    public void onActionBtnClear(ActionEvent actionEvent) {
        txtCurrentPw.setText("");
        txtNewPw.setText("");
        txtConfirmNewPw.setText("");
        pwFieldPw.setText("");
        pwFieldConfirmPw.setText("");
        pwFieldNewPw.setText("");
    }

    public void onActionShowPw(ActionEvent actionEvent) {
        if(selectBoxShowPw.isSelected()){

            txtCurrentPw.setText(pwFieldPw.getText());
            pwFieldPw.setVisible(false);
            txtCurrentPw.setVisible(true);

            txtNewPw.setText(pwFieldNewPw.getText());
            pwFieldNewPw.setVisible(false);
            txtNewPw.setVisible(true);

            txtConfirmNewPw.setText(pwFieldConfirmPw.getText());
            pwFieldConfirmPw.setVisible(false);
            txtConfirmNewPw.setVisible(true);
            return;
        }
        pwFieldPw.setText(txtCurrentPw.getText());
        txtCurrentPw.setVisible(false);
        pwFieldPw.setVisible(true);

        pwFieldNewPw.setText(txtNewPw.getText());
        txtNewPw.setVisible(false);
        pwFieldNewPw.setVisible(true);

        pwFieldConfirmPw.setText(txtConfirmNewPw.getText());
        txtConfirmNewPw.setVisible(false);
        pwFieldConfirmPw.setVisible(true);
    }

    public void onActionDeleteAcc(ActionEvent actionEvent) {
        try{
            btnDeleteAcc.setCursor(Cursor.HAND);

            btnDeleteAcc.setOnAction((e) -> {
                ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Delete Account ?", yes, no).showAndWait();

                if(type.orElse(no) == yes) {
                    try {
                        UserRepository userRepo = new UserRepository();
                        User usr = userRepo.GetUser(currentUser.getUserId());

                        if(userRepo.DeleteUser(usr)){
                            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/User_Login_form.fxml"));
                            Stage stage = (Stage) root.getScene().getWindow();

                            stage.setScene(new Scene(anchorPane));
                            stage.centerOnScreen();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
