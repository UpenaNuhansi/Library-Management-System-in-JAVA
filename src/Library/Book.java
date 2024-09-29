package Library;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String publisher;
    private int yearPublished;

    //add book
    public Book(String title, String author, String publisher, int yearPublished) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
    }
    //update book
    public Book(int bookId, String title, String author, String publisher, int yearPublished) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId +
                ", Title: '" + title + '\'' +
                ", Author: '" + author + '\'' +
                ", Publisher: '" + publisher + '\'' +
                ", Year Published: " + yearPublished;
    }
}

