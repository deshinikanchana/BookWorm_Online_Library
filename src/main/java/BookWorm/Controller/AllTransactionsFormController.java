package BookWorm.Controller;

import BookWorm.DAO.TransactionDAO;
import BookWorm.DAO.TransactionDAOimpl;
import BookWorm.Entity.BookTransaction;
import BookWorm.DTO.TM.TransactionTm;
import BookWorm.embedded.TransactionPK;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AllTransactionsFormController {
    public TableView<TransactionTm> tblTransactions;
    public TableColumn<?,?> colUserId;
    public TableColumn<?,?> colBookId;
    public TableColumn<?,?> colDateOfIssue;
    public TableColumn<?,?> colStatus;
    public TableColumn<?,?> colReturnedDate;
    public TableColumn<?,?> colAction;
    public TextField txtBookId;
    public TextField txtUserId;
    public AnchorPane root;

    public TransactionDAO transDao = new TransactionDAOimpl();
    public void initialize() throws IOException {
       loadAllTransactions();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("UserId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("BookId"));
        colDateOfIssue.setCellValueFactory(new PropertyValueFactory<>("BorrowedDate"));
        colReturnedDate.setCellValueFactory(new PropertyValueFactory<>("ReturnedDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    private void loadAllTransactions() throws IOException {
        try {
           // TransactionRepository transRep = new TransactionRepository();
            List<BookTransaction> transList = transDao.GetAllTransactions();

            ObservableList<TransactionTm> obList = FXCollections.observableArrayList();
            for(BookTransaction btr : transList){
                Button btn = new Button("Returned");
                btn.setCursor(Cursor.HAND);

                btn.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to make change ?", yes, no).showAndWait();

                    if(type.orElse(no) == yes) {
                        try {
                            UpdateTransaction(btr.getTransactionPk(),btr.getBorrowedDate());
                            btn.setDisable(true);
                        } catch (IOException ex) {
                           ex.printStackTrace();
                        }
                    }
                });

                TransactionTm tm = new TransactionTm(
                       modifyId(btr.getBook().getBookId(),"B"),
                        modifyId(btr.getUser().getUserId(),"U"),
                        btr.getBorrowedDate(),
                        btr.getReturnedDate(),
                        btr.getStatus(),
                        btn
                );
                obList.add(tm);
            }
            tblTransactions.setItems(obList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void UpdateTransaction(TransactionPK pk, Timestamp borrowedDate) throws IOException {
       // TransactionRepository transRepo = new TransactionRepository();
        List<BookTransaction> list = transDao.GetAllTransactions();
        BookTransaction btr = new BookTransaction();
        for(BookTransaction myBook:list){
            if(myBook.getBorrowedDate().equals(borrowedDate) & (myBook.getTransactionPk().getUserId() == pk.getUserId() & (myBook.getTransactionPk().getBookId() == pk.getBookId()))){
                 btr = myBook;
            }
        }
        btr.setStatus("Available");

        //transRepo = new TransactionRepository();
        if(transDao.UpdateTransaction(btr)){
            new Alert(Alert.AlertType.CONFIRMATION, "Transaction Updated !!!").show();
            loadAllTransactions();
        }
    }

    public void onActionBookId(ActionEvent actionEvent) throws IOException {
        int id =  splitId(txtBookId.getText(),"B");
        ObservableList<TransactionTm> obList = FXCollections.observableArrayList();

        try {
            //TransactionRepository tr = new TransactionRepository();
            List<BookTransaction> btr = transDao.GetAllTransactions();

            List<BookTransaction> book = new ArrayList<>();
            for (BookTransaction Btr : btr) {
                if (id == Btr.getTransactionPk().getBookId()) {
                    book.add(Btr);
                }
            }

            for (BookTransaction trans:book){
                Button btn = new Button("Returned");
                btn.setCursor(Cursor.HAND);

                btn.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to make change ?", yes, no).showAndWait();

                    if(type.orElse(no) == yes) {
                        try {
                            UpdateTransaction(trans.getTransactionPk(), trans.getBorrowedDate());
                            btn.setDisable(true);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                TransactionTm tm = new TransactionTm(
                       modifyId(trans.getBook().getBookId(),"B"),
                        modifyId(trans.getUser().getUserId(),"U"),
                        trans.getBorrowedDate(),
                        trans.getReturnedDate(),
                        trans.getStatus(),
                        btn
                );
                obList.add(tm);
            }
tblTransactions.setItems(obList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onActionBtnSearch(ActionEvent actionEvent) throws IOException {
        if((txtUserId.getText()!= null) & (txtBookId.getText().equals(null))){
            onActionUserId(actionEvent);
        }else if((txtBookId.getText()!= null) & (txtUserId.getText().equals(null))){
            onActionBookId(actionEvent);
        }else{
            new Alert(Alert.AlertType.ERROR, "Search Using Only Book Id Or User Id at a time").show();
        }
    }

    public void onActionBtnClear(ActionEvent actionEvent) {
        txtBookId.setText("");
        txtUserId.setText("");
    }

    public void onActionUserId(ActionEvent actionEvent) {
        int id =  splitId(txtUserId.getText(),"U");
        ObservableList<TransactionTm> obList = FXCollections.observableArrayList();

        try {
           // TransactionRepository tr = new TransactionRepository();
            List<BookTransaction> btr = transDao.GetAllTransactions();

            List<BookTransaction> usr = new ArrayList<>();
            for (BookTransaction Btr : btr) {
                if (id == Btr.getTransactionPk().getUserId()) {
                    usr.add(Btr);
                }
            }

            for (BookTransaction trans:usr){
                Button btn = new Button("Returned");
                btn.setCursor(Cursor.HAND);

                btn.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to make change ?", yes, no).showAndWait();

                    if(type.orElse(no) == yes) {
                        try {
                            UpdateTransaction(trans.getTransactionPk(), trans.getBorrowedDate());
                            btn.setDisable(true);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                TransactionTm tm = new TransactionTm(
                        modifyId(trans.getBook().getBookId(),"B"),
                        modifyId(trans.getUser().getUserId(),"U"),
                        trans.getBorrowedDate(),
                        trans.getReturnedDate(),
                        trans.getStatus(),
                        btn
                );
                obList.add(tm);
            }
            tblTransactions.setItems(obList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onActionExpiredUsers(ActionEvent actionEvent) throws IOException {
        //TransactionRepository trans = new TransactionRepository();
        System.out.println("Hello All transactions 217 line eka yko mee");

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
