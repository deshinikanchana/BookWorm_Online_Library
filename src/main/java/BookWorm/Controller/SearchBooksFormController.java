package BookWorm.Controller;

import BookWorm.BO.BOFactory;
import BookWorm.BO.custom.SearchBookBO;
import BookWorm.DAO.custom.TransactionDAO;
import BookWorm.DAO.custom.impl.TransactionDAOimpl;
import BookWorm.DTO.BookDto;
import BookWorm.Entity.Book;
import BookWorm.Entity.BookTransaction;
import BookWorm.DTO.TM.SearchBookTm;
import BookWorm.Entity.User;
import BookWorm.embedded.TransactionPK;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import static BookWorm.Controller.LoginFormController.currentUser;
public class SearchBooksFormController {
    public TableView<SearchBookTm> tblBooks;
    public TableColumn<?,?> colBookId;
    public TableColumn<?,?> colBranchId;
    public TableColumn<?,?> colTitle;
    public TableColumn<?,?> colAuthor;
    public TableColumn<?,?> colGenre;
    public TableColumn<?,?> colAvailability;
    public TableColumn<?,?> colAction;
    public TextField txtBookName;
    public TextField txtAuthor;

    public AnchorPane root;

    SearchBookBO bo = (SearchBookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SEARCHBOOK);

    public TransactionDAO trDao = new TransactionDAOimpl();
    public void initialize() throws IOException {
        loadAllBooks();
        setCellValuefactory();
    }

    private void setCellValuefactory() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("BookId"));
        colBranchId.setCellValueFactory(new PropertyValueFactory<>("Branch"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("AvailabilityStatus"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }


    private void loadAllBooks() throws IOException {
        ObservableList<SearchBookTm> obList = FXCollections.observableArrayList();
        try {
            //BookRepository br = new BookRepository();
            List<BookDto> bookList = bo.getAllBooks();

            for (BookDto book : bookList) {
                Button btn = new Button("Borrow");
                btn.setCursor(Cursor.HAND);

                btn.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to borrow this Book ?", yes, no).showAndWait();

                    if (type.orElse(no) == yes) {
                        try {
                            updateStatus(book);
                            addTransaction(book);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        btn.setDisable(true);
                    }
                });
                obList.add(new SearchBookTm(
                        modifyId(book.getBookId(), "B"),
                        modifyId(book.getBranch().getBranchId(), "Br"),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getGenre(),
                        book.getAvailabilityStatus(),
                        btn
                ));
            }
            tblBooks.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTransaction(BookDto book) throws IOException {

        //TransactionRepository trans = new TransactionRepository();
        BookTransaction bookTr = new BookTransaction(new TransactionPK(currentUser.getUserId(), book.getBookId()),"Not Returned",new User(currentUser.getUserId(),currentUser.getUserName(),currentUser.getEmail(),currentUser.getPasssword()),new Book(book.getBookId(),book.getTitle(),book.getAuthor(),book.getGenre(),book.getAvailabilityStatus(),book.getBranch(),book.getTransList()));
        trDao.SaveTransaction(bookTr);
        onActionBtnClear(new ActionEvent());
    }

    private void updateStatus(BookDto bookorg) throws IOException {
        //BookRepository bookrepo = new BookRepository();
        BookDto book = bo.GetBook(bookorg.getBookId());
        book.setAvailabilityStatus("Borrowed");

        //bookrepo = new BookRepository();
       if(bo.UpdateBook(book)){
           onActionBtnClear(new ActionEvent());
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

    public void onActionBookName(ActionEvent actionEvent) throws IOException {
        ObservableList<SearchBookTm> obList = FXCollections.observableArrayList();
        try {
          //  BookRepository br = new BookRepository();
            List<BookDto> bookList = bo.getAllBooks();
            for(BookDto book :bookList){
                if(book.getTitle().equals(txtBookName.getText())){
                    Button btn = new Button("Borrow");
                    btn.setCursor(Cursor.HAND);

                    btn.setOnAction((e) -> {
                        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to borrow this Book ?", yes, no).showAndWait();

                        if (type.orElse(no) == yes) {
                            try {
                                updateStatus(book);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            try {
                                addTransaction(book);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            btn.setDisable(true);
                        }
                    });
                    obList.add( new SearchBookTm(
                            modifyId(book.getBookId(), "B"),
                            modifyId(book.getBranch().getBranchId(), "Br"),
                            book.getTitle(),
                            book.getAuthor(),
                            book.getGenre(),
                            book.getAvailabilityStatus(),
                            btn
                    ));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onActionAuthor(ActionEvent actionEvent) {
        ObservableList<SearchBookTm> obList = FXCollections.observableArrayList();
        try {
            //BookRepository br = new BookRepository();
            List<BookDto> bookList = bo.getAllBooks();
            for(BookDto bo:bookList){
                if(bo.getAuthor().equals(txtAuthor.getText())){
                    Button btn = new Button("Borrow");
                    btn.setCursor(Cursor.HAND);

                    btn.setOnAction((e) -> {
                        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to borrow this Book ?", yes, no).showAndWait();

                        if (type.orElse(no) == yes) {
                            try {
                                updateStatus(bo);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            try {
                                addTransaction(bo);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            btn.setDisable(true);
                        }
                    });
                   obList.add(new SearchBookTm(
                            modifyId(bo.getBookId(), "B"),
                            modifyId(bo.getBranch().getBranchId(), "Br"),
                            bo.getTitle(),
                            bo.getAuthor(),
                            bo.getGenre(),
                            bo.getAvailabilityStatus(),
                            btn
                    ));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onActionBtnSearch(ActionEvent actionEvent) throws IOException {
        if((txtBookName.getText()!= null) & (txtAuthor.getText().equals(null))){
            onActionBookName(actionEvent);
        }else if((txtAuthor.getText()!= null) & (txtBookName.getText().equals(null))){
            onActionAuthor(actionEvent);
        }else{
            new Alert(Alert.AlertType.ERROR, "Search only the book title or author name at a time !").show();
        }
    }

    public void onActionBtnClear(ActionEvent actionEvent) throws IOException {
        txtAuthor.setText("");
        txtBookName.setText("");
        loadAllBooks();
    }
}
