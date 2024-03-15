package BookWorm.Controller;

import BookWorm.Entity.Book;
import BookWorm.Entity.User;
import BookWorm.Repository.BookRepository;
import BookWorm.Repository.UserRepository;
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
import java.util.List;

import static BookWorm.Controller.AdminLoginFormController.CurrentAdmin;

public class AdminDbFormController {
    public AnchorPane root;
    public Label lblDate;
    public Label lblTime;
    public Label lblBooksCount;
    public Label lblUsersCount;
    public Label lblAdminName;

    private Stage stage = new Stage();

    public void initialize() throws IOException {
        SetData();
    }

    private void SetData() throws IOException {
        lblAdminName.setText(CurrentAdmin.getAdminName());
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
        };
        timer.start();
        lblDate.setText(String.valueOf(LocalDate.now()));

        UserRepository userRepo = new UserRepository();
        List<User> userList = userRepo.getAllUsers();
        lblUsersCount.setText(String.valueOf(userList.size()));

        BookRepository bookRepo = new BookRepository();
        List<Book> bookList = bookRepo.getAllBooks();
        lblBooksCount.setText(String.valueOf(bookList.size()));
    }

    public void onActionBtnUsers(ActionEvent actionEvent) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/users_form.fxml"));
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Users");
        stage.setScene(scene);
        stage.setY(170);
        stage.setX(361);
        stage.show();


    }

    public void OnActionBtnBooks(ActionEvent actionEvent) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/Books_Admin_Form.fxml"));
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Books");
        stage.setScene(scene);
        stage.setY(170);
        stage.setX(361);
        stage.show();
    }

    public void onActionBtnBranches(ActionEvent actionEvent) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/Branches_Form.fxml"));
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Branches");
        stage.setScene(scene);
        stage.setY(170);
        stage.setX(361);
        stage.show();
    }

    public void onActionBtnTransactions(ActionEvent actionEvent) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/All_Transactions_form.fxml"));
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Transactions");
        stage.setScene(scene);
        stage.setY(170);
        stage.setX(361);
        stage.show();
    }

    public void onActionBtnSetting(ActionEvent actionEvent) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/Admin_Setting_form.fxml"));
        Scene scene = new Scene(anchorPane);

        stage.setTitle("Account Setting");
        stage.setScene(scene);
        stage.setY(170);
        stage.setX(361);
        stage.show();
    }

    public void onActionBtnLogout(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Welcome_form.fxml"));
        Stage st = (Stage) root.getScene().getWindow();

        st.setScene(new Scene(anchorPane));
        st.centerOnScreen();
    }

    public void onActionAddAdmin(ActionEvent actionEvent) throws IOException {

        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/Admin_New_Account_form.fxml"));
        Scene scene = new Scene(anchorPane);

        stage.setTitle("New Admin");
        stage.setScene(scene);
        stage.setY(170);
        stage.setX(361);
        stage.show();
    }
}
