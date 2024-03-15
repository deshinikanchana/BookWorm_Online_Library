package BookWorm.DTO;

import BookWorm.Entity.BookTransaction;
import BookWorm.Entity.Branch;

import java.util.ArrayList;
import java.util.List;

public class BookDto {
    private int BookId;

    private String Title;

    private String Author;

    private String Genre;

    private String AvailabilityStatus;

    private Branch branch;

    private List<BookTransaction> transList = new ArrayList<>();

    public BookDto() {
    }

    public BookDto(int bookId, String title, String author, String genre, String availabilityStatus, Branch branch, List<BookTransaction> transList) {
        BookId = bookId;
        Title = title;
        Author = author;
        Genre = genre;
        AvailabilityStatus = availabilityStatus;
        this.branch = branch;
        this.transList = transList;
    }

    public int getBookId() {
        return BookId;
    }

    public void setBookId(int bookId) {
        BookId = bookId;
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

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<BookTransaction> getTransList() {
        return transList;
    }

    public void setTransList(List<BookTransaction> transList) {
        this.transList = transList;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "BookId=" + BookId +
                ", Title='" + Title + '\'' +
                ", Author='" + Author + '\'' +
                ", Genre='" + Genre + '\'' +
                ", AvailabilityStatus='" + AvailabilityStatus + '\'' +
                '}';
    }
}
