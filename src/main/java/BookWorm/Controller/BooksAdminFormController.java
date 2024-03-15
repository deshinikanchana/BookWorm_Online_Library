package BookWorm.Controller;

import BookWorm.DAO.*;
import BookWorm.DTO.BranchDto;
import BookWorm.Entity.Book;
import BookWorm.Entity.BookTransaction;
import BookWorm.Entity.Branch;
import BookWorm.DTO.TM.BookTm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BooksAdminFormController {
    public TableView<BookTm> tblBooks;
    public TableColumn<?,?> colBookId;
    public TableColumn<?,?> colBranchId;
    public TableColumn<?,?> colTitle;
    public TableColumn<?,?> colGenre;
    public TableColumn<?,?> colAuthor;
    public TableColumn<?,?> colAvailability;
    public TextField txtBookId;
    public TextField txtBookTitle;
    public TextField txtGenre;
    public TextField txtAuthor;
    public Label lblAvailability;

    public AnchorPane root;
    public JFXComboBox comboBoxBranchId;
    public JFXButton btnDelete;
    public JFXButton BtnUpdate;

    public TransactionDAO transDao = new TransactionDAOimpl();
    public BookDAO bookDao = new BookDAOimpl();

    public BranchDAO brDao = new BranchDAOimpl();
    public void initialize() throws IOException {
        setCellValueFactory();
        LoadBranchIds();
        loadAllBooks();
        SetBookId();
        btnDelete.setDisable(true);
        BtnUpdate.setDisable(true);
    }

    private void setStatus(int id) throws IOException {
       // TransactionRepository trans = new TransactionRepository();
        List<BookTransaction> transList = transDao.GetAllTransactions();
        List<BookTransaction> thisBook = new ArrayList<>();
        for(BookTransaction bookTrans:transList){
            if(id == bookTrans.getBook().getBookId()){
                thisBook.add(bookTrans);
            }
        }

        if(thisBook.get(thisBook.size()-1).getStatus() .equals("Returned")){
            lblAvailability.setText("Available");
        }else if(thisBook.isEmpty()){
            lblAvailability.setText("Available");
        }else{
            lblAvailability.setText("Not Available");
        }

    }

    private void loadAllBooks() throws IOException {
        ObservableList<BookTm> obList = FXCollections.observableArrayList();
    try {
        //BookRepository bookRepo = new BookRepository();
        List<Book> bookList = bookDao.getAllBooks();
        for (Book book : bookList) {
            obList.add(
                    new BookTm(
                            modifyId(book.getBookId(),"B"),
                            modifyId(book.getBranch().getBranchId(),"Br"),
                            book.getTitle(),
                            book.getAuthor(),
                            book.getGenre(),
                            book.getAvailabilityStatus()

                    )
            );
        }
        tblBooks.setItems(obList);
    }catch (Exception e){
        e.printStackTrace();
    }

    }

    private String modifyId(int id,String letter){
        if (id < 10) {
            return letter + "00" + id;
        } else {
            return letter + "0" + id;
        }
    }

    private void LoadBranchIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try{
           // BranchRepository branchRepo = new BranchRepository();
            List<BranchDto> branchList = brDao.GetAll();
            List<Branch> brList = new ArrayList<>();
            for(BranchDto dto: branchList){
                brList.add(new Branch(dto.getBranchId(),dto.getAddress(),dto.getEmail(),dto.getAdmin(),dto.getBookList()));
            }
            for(Branch branch: brList){
                obList.add(modifyId(branch.getBranchId(),"Br"));
            }
            comboBoxBranchId.setItems(obList);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setCellValueFactory() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("BookId"));
        colBranchId.setCellValueFactory(new PropertyValueFactory<>("BranchId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("AvailabilityStatus"));
    }

    public void SetBookId() throws IOException {
        //BookRepository bookRepo = new BookRepository();
        List<Book> bookList = bookDao.getAllBooks();
        int id = 0;
        for(Book book : bookList) {
        id = book.getBookId();
        id++;
        }
        txtBookId.setText(modifyId(id,"B"));
    }

    public int splitId(String id,String letter){
        String[] split = id.split(letter);
        int num = Integer.parseInt(split[1]);
        return num;
    }
    public void onActionBtnAdd(ActionEvent actionEvent) throws IOException {
        if((txtBookId.getText() != null) & (txtBookTitle.getText() != null) &((String)comboBoxBranchId.getValue()!= null)) {
           // BranchRepository branchRepo = new BranchRepository();
            BranchDto dto = brDao.Get(splitId((String) comboBoxBranchId.getValue(), "Br"));
            Branch br = new Branch(dto.getBranchId(),dto.getAddress(),dto.getEmail(),dto.getAdmin(),dto.getBookList());
            Book book = new Book(splitId(txtBookId.getText(), "B"), txtBookTitle.getText(), txtAuthor.getText(), txtGenre.getText(), "Available", br);

            //BookRepository bookRepo = new BookRepository();
            bookDao.SaveBook(book);
            onActionBtnClear(actionEvent);
            SetBookId();
        }
        new Alert(Alert.AlertType.ERROR, "Fill Fields First !!!").show();
    }

    public void onActionBtnClear(ActionEvent actionEvent) throws IOException {
        txtBookTitle.setText("");
        txtBookId.setText("");
        txtAuthor.setText("");
        txtGenre.setText("");
        lblAvailability.setText("");
        comboBoxBranchId.setValue("");
        SetBookId();
        btnDelete.setDisable(true);
        BtnUpdate.setDisable(true);
        loadAllBooks();
    }

    public void onActionBtnUpdate(ActionEvent actionEvent) throws IOException {
       int id = splitId(txtBookId.getText(),"B");

       //BookRepository bookRep = new BookRepository();
       Book book = bookDao.GetBook(id);
       setStatus(id);
       book.setAvailabilityStatus(lblAvailability.getText());

       //bookRep = new BookRepository();

       if(bookDao.UpdateBook(book)){
           onActionBtnClear(actionEvent);
       }
    }

    public void onActionBtnDelete(ActionEvent actionEvent) throws IOException {
        int id = splitId(txtBookId.getText(),"B");
        //BookRepository bookRepo = new BookRepository();
        Book book =bookDao.GetBook(id);

         //bookRepo = new BookRepository();
        if(bookDao.DeleteBook(book)){
            onActionBtnClear(actionEvent);
        }
    }

    public void onActionBookId(ActionEvent actionEvent) throws IOException {
        int id = splitId(txtBookId.getText(),"B");
        //BookRepository bookRepo = new BookRepository();
        Book book = bookDao.GetBook(id);

        txtBookId.setText(modifyId(book.getBookId(), "B"));
        txtGenre.setText(book.getGenre());
        txtAuthor.setText(book.getAuthor());
        txtBookTitle.setText(book.getTitle());
        lblAvailability.setText(book.getAvailabilityStatus());
        comboBoxBranchId.setValue(modifyId(book.getBranch().getBranchId(),"Br"));

        btnDelete.setDisable(false);
        BtnUpdate.setDisable(false);
    }
}
