package BookWorm.DTO.TM;

public class BookTm {
    private String BookId;
    private String BranchId;
    private String Title;
    private String Author;
    private String Genre;
    private String AvailabilityStatus;

    public BookTm() {
    }

    public BookTm(String bookId, String branchId, String title, String author, String genre, String availabilityStatus) {
        BookId = bookId;
        BranchId = branchId;
        Title = title;
        Author = author;
        Genre = genre;
        AvailabilityStatus = availabilityStatus;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getBranchId() {
        return BranchId;
    }

    public void setBranchId(String branchId) {
        BranchId = branchId;
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

    @Override
    public String toString() {
        return "BookTm{" +
                "BookId='" + BookId + '\'' +
                ", BranchId='" + BranchId + '\'' +
                ", Title='" + Title + '\'' +
                ", Author='" + Author + '\'' +
                ", Genre='" + Genre + '\'' +
                ", AvailabilityStatus='" + AvailabilityStatus + '\'' +
                '}';
    }
}
