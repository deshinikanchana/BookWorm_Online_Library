package BookWorm.Controller;

import BookWorm.DAO.UserDAO;
import BookWorm.DAO.UserDAOimpl;
import BookWorm.Entity.User;
import BookWorm.DTO.TM.UserTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class UsersFormController {
    public TableView<UserTm> tblUsers;
    public TableColumn<?,?> colUserId;
    public TableColumn<?,?> colUserName;
    public TableColumn<?,?> colEmail;
    public TextField txtUserId;
    public TextField txtUserName;
    public AnchorPane pane;

    public UserDAO userDao = new UserDAOimpl();

    public void initialize() throws IOException {
        loadAllUsers();
        setCellValueFactory();
    }

    private void loadAllUsers() throws IOException {
        ObservableList<UserTm> obList = FXCollections.observableArrayList();
        try {
           // UserRepository userRepo = new UserRepository();
            List<User> userList = userDao.getAllUsers();

            for(User us:userList){
                obList.add(new UserTm(
                        modifyId(us.getUserId(), "U"),
                        us.getUserName(),
                        us.getEmail()
                ));
            }
            tblUsers.setItems(obList);
        }catch(Exception e){
            e.printStackTrace();
            }

    }

    private void setCellValueFactory() {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    public void onActionUserId(ActionEvent actionEvent) throws IOException {
        ObservableList<UserTm> obList = FXCollections.observableArrayList();
        if(txtUserId.getText()!= null) {
            try {
                //UserRepository usr = new UserRepository();
                List<User> usrList = userDao.getAllUsers();

                for(User user:usrList){
                    if(user.getUserId()== splitId(txtUserId.getText(), "U")){
                        obList.add(new UserTm(modifyId(user.getUserId(), "U"), user.getUserName(),user.getEmail()));
                    }
                }
                if(obList.isEmpty()){
                    new Alert(Alert.AlertType.ERROR, "User Not Found !").show();
                }else {
                    tblUsers.setItems(obList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "User Id Format is incorrect !").show();
        }
    }

    public int splitId(String id,String letter){
        String[] split = id.split(letter);
        int num = Integer.parseInt(split[1]);
        return num;
    }

    private String modifyId(int id,String letter){
        if (id < 10) {
            return letter + "00" + id;
        } else {
            return letter + "0" + id;
        }
    }

    public void onActionUserName(ActionEvent actionEvent) throws IOException {
        ObservableList<UserTm> obList = FXCollections.observableArrayList();
        if(txtUserName.getText()!= null) {
            try {
                //UserRepository usr = new UserRepository();
                List<User> usrList = userDao.getAllUsers();

                for(User user:usrList){
                    if(user.getUserName().equals(txtUserName.getText())){
                        obList.add(new UserTm(modifyId(user.getUserId(), "U"), user.getUserName(),user.getEmail()));
                    }
                }
                if(obList.isEmpty()){
                    new Alert(Alert.AlertType.ERROR, "User Not Found !").show();
                }else {
                    tblUsers.setItems(obList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "User Name Format is incorrect !").show();
            onActionBtnClear(actionEvent);
        }
    }

    public void onActionBtnSearch(ActionEvent actionEvent) throws IOException {
        if((txtUserName.getText()!= null) & (txtUserId.getText().equals(null))){
            onActionUserName(actionEvent);
        }else if((txtUserId.getText()!= null) & (txtUserName.getText().equals(null))){
            onActionUserId(actionEvent);
        }else{
            System.out.println("userId : " + txtUserId.getText());
            System.out.println("userName : " + txtUserName.getText());

            new Alert(Alert.AlertType.ERROR, "Search only the User Id or User Name at a time !").show();
            onActionBtnClear(actionEvent);
        }
    }

    public void onActionBtnClear(ActionEvent actionEvent) throws IOException {
        txtUserName.setText("");
        txtUserId.setText("");
        loadAllUsers();
    }
}
