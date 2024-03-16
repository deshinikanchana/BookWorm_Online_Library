package BookWorm.Controller;

import BookWorm.BO.BOFactory;
import BookWorm.BO.custom.AdminSettingBO;
import BookWorm.BO.custom.OwnTransactionBO;
import BookWorm.DAO.custom.TransactionDAO;
import BookWorm.DAO.custom.impl.TransactionDAOimpl;
import BookWorm.DTO.BookTransactionDto;
import BookWorm.Entity.BookTransaction;
import BookWorm.DTO.TM.MyTransTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

import static BookWorm.Controller.LoginFormController.currentUser;

public class OwnTransactionsFormController {
    public TableView<MyTransTm> tblMyTransactions;
    public TableColumn<?,?> colBookId;
    public TableColumn<?,?> colBorrowedDate;
    public TableColumn<?,?> colReturnedDate;
    public TableColumn<?,?> colStatus;

    public AnchorPane root;

    OwnTransactionBO bo = (OwnTransactionBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.OWNTRANS);
    public void initialize() throws IOException {
        setCellValueFactory();
        loadaAllTransactions();
    }

    private void loadaAllTransactions() throws IOException {
        ObservableList<MyTransTm> obList = FXCollections.observableArrayList();
        try {
            //TransactionRepository tr = new TransactionRepository();
            List<BookTransactionDto> bookList = bo.GetAllTransactions();

            for (BookTransactionDto book : bookList) {
                if (book.getTransactionPk().getUserId() == currentUser.getUserId()) {
                    obList.add(new MyTransTm(
                            modifyId(book.getBook().getBookId(),"B"),
                            book.getBorrowedDate(),
                            book.getReturnedDate(),
                            book.getStatus()
                            )
                    );
                }
            }
            tblMyTransactions.setItems(obList);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBorrowedDate.setCellValueFactory(new PropertyValueFactory<>("borrowedDate"));
        colReturnedDate.setCellValueFactory(new PropertyValueFactory<>("returnedDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

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
}
