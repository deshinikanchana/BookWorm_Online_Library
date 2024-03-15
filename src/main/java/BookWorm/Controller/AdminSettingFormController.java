package BookWorm.Controller;

import BookWorm.DAO.AdminDAO;
import BookWorm.DAO.AdminDAOimpl;
import BookWorm.Entity.Admin;
import BookWorm.projection.AdminProjection;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

import static BookWorm.Controller.AdminLoginFormController.CurrentAdmin;

public class AdminSettingFormController {
    public TextField txtCurrentPw;
    public TextField txtNewPw;
    public TextField txtConfirmNewPw;
    public PasswordField pwFieldPw;
    public PasswordField pwFieldNewPw;
    public PasswordField pwFieldConfirmPw;
    public CheckBox CheckBoxShowPw;
    public AnchorPane root;
    public Label lblAdminName;

    public AdminDAO adminDao = new AdminDAOimpl();

    public void initialize() throws IOException {
        lblAdminName.setText(CurrentAdmin.getAdminName());
    }

    public void onActionBtnSubmit(ActionEvent actionEvent) throws IOException {
       // AdminRepository adRepo = new AdminRepository();
        if(pwFieldPw.getText() != null){
            List<AdminProjection> adminProj = adminDao.getAdminProjection();
            for (AdminProjection projection : adminProj) {
                if (projection.getName().equals(CurrentAdmin.getAdminName())) {
                    if (projection.getPw().equals(pwFieldPw.getText())) {
                        if((pwFieldConfirmPw.getText() != null) & (pwFieldNewPw.getText().equals(pwFieldConfirmPw.getText()))) {
                            Admin admin = new Admin(projection.getId(), projection.getName(), projection.getEmail(), pwFieldConfirmPw.getText());
                            //adRepo = new AdminRepository();

                            if( adminDao.UpdateAdmin(admin)){
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
        if(CheckBoxShowPw.isSelected()){

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

}

