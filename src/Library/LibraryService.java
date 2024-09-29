package Library;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class LibraryService {
    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author, publisher, year_published, availability) VALUES (?, ?, ?, ?,1)";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setInt(4, book.getYearPublished());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, publisher = ?, year_published = ? WHERE book_id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setInt(4, book.getYearPublished());
            stmt.setInt(5, book.getBookId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int bookId) {
        String sql = "DELETE FROM books WHERE book_id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> searchBooks(String query) {
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";
        List<String> books = new ArrayList<>();
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + query + "%");
            stmt.setString(2, "%" + query + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int bookId = rs.getInt("book_id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                int yearPublished = rs.getInt("year_published");
                int availability = rs.getInt("availability");
                String availabilityStatus = availability == 1 ? "Yes" : "No";
                String bookInfo = "Book ID: " + bookId +
                        " | Title: " + title +
                        " | Author: " + author +
                        " | Publisher: " + publisher +
                        " | Year Published: " + yearPublished +
                        " | Available: " + availabilityStatus;
                books.add(bookInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void addMember(member member) {
        String sql = "INSERT INTO members (name, email, phone) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setString(3,member.getPhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean loanBook(int bookId, int memberId, Date loanDate, Date dueDate) {
        String checkAvailabilitySql = "SELECT availability FROM books WHERE book_id = ?";
        String loanSql = "INSERT INTO loans (book_id, member_id, loan_date, due_date) VALUES (?, ?, ?, ?)";
        String updateBookSql = "UPDATE books SET availability = 0 WHERE book_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkAvailabilitySql);
             PreparedStatement loanStmt = conn.prepareStatement(loanSql);
             PreparedStatement updateBookStmt = conn.prepareStatement(updateBookSql)) {

            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                int availability = rs.getInt("availability");
                if (availability == 0) {
                    return false;
                }
            }

            loanStmt.setInt(1, bookId);
            loanStmt.setInt(2, memberId);
            loanStmt.setDate(3, new java.sql.Date(loanDate.getTime()));
            loanStmt.setDate(4, new java.sql.Date(dueDate.getTime()));
            loanStmt.executeUpdate();

            updateBookStmt.setInt(1, bookId);
            updateBookStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean returnBook(int loanId, Date returnDate) {
        String checkReturnSql = "SELECT return_date, book_id FROM loans WHERE loan_id = ?";
        String returnSql = "UPDATE loans SET return_date = ? WHERE loan_id = ?";
        String updateBookSql = "UPDATE books SET availability = 1 WHERE book_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement checkReturnStmt = conn.prepareStatement(checkReturnSql);
             PreparedStatement returnStmt = conn.prepareStatement(returnSql);
             PreparedStatement updateBookStmt = conn.prepareStatement(updateBookSql)) {

            checkReturnStmt.setInt(1, loanId);
            ResultSet rs = checkReturnStmt.executeQuery();
            if (rs.next()) {
                Date existingReturnDate = rs.getDate("return_date");
                int bookId = rs.getInt("book_id");
                if (existingReturnDate != null) {
                    return false;
                }

            returnStmt.setDate(1, new java.sql.Date(returnDate.getTime()));
            returnStmt.setInt(2, loanId);
            returnStmt.executeUpdate();

                updateBookStmt.setInt(1, bookId);
                updateBookStmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
