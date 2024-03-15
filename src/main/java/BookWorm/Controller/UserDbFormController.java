package BookWorm.Controller;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static BookWorm.Controller.LoginFormController.currentUser;

public class UserDbFormController {
    public Label lblName;
    public Label lblDate;
    public Label lblTime;
    public Label lblWarningBooksReturn;
    public AnchorPane userPane;
    public AnchorPane root;

    private Stage stage = new Stage();

    public void initialize(){
        setData();
    }

    private void setData() {
        lblName.setText(currentUser.getUserName());
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
        };
        timer.start();
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    public void OnActionBtnSearchBooks(ActionEvent actionEvent) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/Search_Books_form.fxml"));
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Books");
        stage.setScene(scene);
        stage.setY(170);
        stage.setX(361);
        stage.show();
    }

    public void onActionBtnMyTransactions(ActionEvent actionEvent) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/Own_Transactions_form.fxml"));
        Scene scene = new Scene(anchorPane);

        stage.setTitle("My Transactions");
        stage.setScene(scene);
        stage.setY(170);
        stage.setX(361);
        stage.show();
    }

    public void onActionBtnSetting(ActionEvent actionEvent) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/User_Setting_form.fxml"));
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Account Settings");
        stage.setScene(scene);
        stage.setY(170);
        stage.setX(361);
        stage.show();
    }

    public void onActionBtnLogout(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Welcome_form.fxml"));
        Stage St = (Stage) root.getScene().getWindow();

        St.setScene(new Scene(anchorPane));
        St.centerOnScreen();
    }
}
