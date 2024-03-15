package BookWorm.Controller;

import BookWorm.DAO.BranchDAO;
import BookWorm.DAO.BranchDAOimpl;
import BookWorm.DTO.BranchDto;
import BookWorm.Entity.Branch;
import BookWorm.DTO.TM.BranchTm;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static BookWorm.Controller.AdminLoginFormController.CurrentAdmin;

public class BranchesFormController {
    public TableView<BranchTm> tblBranches;
    public TableColumn<?,?> colBranchId;
    public TableColumn<?,?> colLocation;
    public TableColumn<?,?> colEmail;
    public TextField txtBranchId;
    public TextField txtLocation;
    public TextField txtEmail;
    public AnchorPane root;
    public JFXButton btnDelete;

    public BranchDAO brDao = new BranchDAOimpl();

    public void initialize() throws IOException {
        loadAllBranches();
        setCellValueFactory();
        setBranchId();
    }

    private void setBranchId() throws IOException {
       // BranchRepository br = new BranchRepository();
        List<BranchDto> brList = brDao.GetAll();
        List<Branch> branchList = new ArrayList<>();
        for(BranchDto dto:brList){
            branchList.add(new Branch(dto.getBranchId(),dto.getAddress(),dto.getEmail(),dto.getAdmin(),dto.getBookList()));
        }

        int id = 0;
        for(Branch branch : branchList) {
            id = branch.getBranchId();
            id++;
        }
        txtBranchId.setText(modifyId(id,"Br"));
    }

    private void setCellValueFactory() {
        colBranchId.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("Address"));
    }

    private void loadAllBranches() throws IOException {
        ObservableList<BranchTm> obList = FXCollections.observableArrayList();
        try {
            //BranchRepository br = new BranchRepository();
            List<BranchDto> Br = brDao.GetAll();
            List<Branch> branchList = new ArrayList<>();
            for(BranchDto dto:Br){
                branchList.add(new Branch(dto.getBranchId(),dto.getAddress(),dto.getEmail(),dto.getAdmin(),dto.getBookList()));
            }
            for(Branch branch:branchList){
                obList.add(
                        new BranchTm(
                                modifyId(branch.getBranchId(),"Br"),
                                branch.getAddress(),
                                branch.getEmail()
                        )
                );
            }
            tblBranches.setItems(obList);
        }catch(Exception e){
            e.printStackTrace();
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

    public void onActionBtnAdd(ActionEvent actionEvent) throws IOException {
        if((txtBranchId.getText() != null) & (txtLocation.getText() != null)) {
           //BranchRepository br = new BranchRepository();
           Branch branch = new Branch(splitId(txtBranchId.getText(),"Br"),txtLocation.getText(),txtEmail.getText(),CurrentAdmin);
                brDao.Save(new BranchDto(branch.getBranchId(),branch.getAddress(),branch.getEmail(),branch.getAdmin(),branch.getBookList()));
                onActionBtnClear(actionEvent);
                return;
        }
        new Alert(Alert.AlertType.ERROR, "Fill Fields First !!!").show();

    }

    public void onActionBtnClear(ActionEvent actionEvent) throws IOException {
        txtEmail.setText("");
        txtLocation.setText("");
        setBranchId();
        loadAllBranches();
    }

    public void onActionBtnUpdate(ActionEvent actionEvent) throws IOException {
        if((txtBranchId.getText()!= null) & (txtLocation.getText()!= null)){
           // BranchRepository br = new BranchRepository();
            BranchDto branch = brDao.Get(splitId(txtBranchId.getText(), "Br"));
            branch.setAddress(txtLocation.getText());
            branch.setEmail(txtEmail.getText());

            //br = new BranchRepository();
            if(brDao.Update(branch)){
                onActionBtnClear(actionEvent);
            }
        }
    }

    public void onActionBtnDelete(ActionEvent actionEvent) throws IOException {
        int id = splitId(txtBranchId.getText(), "Br");
        //BranchRepository br  = new BranchRepository();
        BranchDto branch = brDao.Get(id);

        btnDelete.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Delete this Branch ?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                try {
                   // BranchRepository Br = new BranchRepository();
                    brDao.Delete(branch);
                    onActionBtnClear(actionEvent);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    public void onActionBranchId(ActionEvent actionEvent) throws IOException {
        int id = splitId(txtBranchId.getText(),"Br");

        //BranchRepository br = new BranchRepository();
        BranchDto branch = brDao.Get(splitId(txtBranchId.getText(), "Br"));

        txtBranchId.setText(modifyId(branch.getBranchId(), "Br"));
        txtLocation.setText(branch.getAddress());
        txtEmail.setText(branch.getEmail());
    }
}
