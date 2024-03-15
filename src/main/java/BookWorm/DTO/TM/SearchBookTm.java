package BookWorm.DTO.TM;

import javafx.scene.control.Button;

public class SearchBookTm {
    private String BookId;
    private String Branch;
    private String Title;
    private String Author;
    private String Genre;
    private String AvailabilityStatus;
    private Button btn;

    public SearchBookTm() {
    }

    public SearchBookTm(String bookId, String branch, String title, String author, String genre, String availabilityStatus, Button btn) {
        BookId = bookId;
        Branch = branch;
        Title = title;
        Author = author;
        Genre = genre;
        AvailabilityStatus = availabilityStatus;
        this.btn = btn;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getAvailabilityStatus() {
        return AvailabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        AvailabilityStatus = availabilityStatus;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "SearchBookTm{" +
                "BookId='" + BookId + '\'' +
                ", Branch='" + Branch + '\'' +
                ", Title='" + Title + '\'' +
                ", Author='" + Author + '\'' +
                ", Genre='" + Genre + '\'' +
                ", AvailabilityStatus='" + AvailabilityStatus + '\'' +
                ", btn=" + btn +
                '}';
    }
}
