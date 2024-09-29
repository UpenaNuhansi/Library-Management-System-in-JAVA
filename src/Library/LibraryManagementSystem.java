package Library;

import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class LibraryManagementSystem {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryService libraryService = new LibraryService();

        while (true) {
            System.out.println("\nWelcome to the Library Management System!!!\n");
            System.out.println("1. Add a New Book");
            System.out.println("2. Update Book Details");
            System.out.println("3. Delete a Book");
            System.out.println("4. Search for a Book");
            System.out.println("5. Add a New Member");
            System.out.println("6. Loan a Book");
            System.out.println("7. Return a Book");
            System.out.println("8. Exit");
            System.out.print("\nChoose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nAdd a New Book\n");
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author Name: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Book Publisher: ");
                    String publisher = scanner.nextLine();
                    System.out.print("Enter Year Published: ");
                    int yearPublished = scanner.nextInt();
                    scanner.nextLine();

                    Book newBook = new Book(title, author, publisher, yearPublished);
                    libraryService.addBook(newBook);
                    System.out.println("\nBook added successfully.\n");
                    break;

                case 2:
                    System.out.println("\nUpdate Book Details\n");
                    System.out.print("Enter Book ID: ");
                    int updateBookId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter New Book Title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter New Author Name: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Enter New Publisher: ");
                    String newPublisher = scanner.nextLine();
                    System.out.print("Enter New Year Published: ");
                    int newYearPublished = scanner.nextInt();
                    scanner.nextLine();

                    Book updatedBook = new Book(updateBookId, newTitle, newAuthor, newPublisher, newYearPublished);
                    libraryService.updateBook(updatedBook);
                    System.out.println("\nBook details updated successfully.\n");
                    break;

                case 3:
                    System.out.println("\nDelete a Book\n");
                    System.out.print("Enter Book ID: ");
                    int deleteBookId = scanner.nextInt();
                    scanner.nextLine();

                    libraryService.deleteBook(deleteBookId);
                    System.out.println("\nBook deleted successfully.\n");
                    break;

                case 4:
                    System.out.println("\nSearch for a Book\n");
                    System.out.print("Enter Book Title or Author Name to search: ");
                    String searchQuery = scanner.nextLine();
                    List<String> searchResults = libraryService.searchBooks(searchQuery);
                    if (searchResults.isEmpty()) {
                        System.out.println("\nNo books found.\n");
                    } else {
                        for (String bookInfo : searchResults) {
                            System.out.println(bookInfo);
                        }
                    }
                    break;


                case 5:
                    System.out.println("\nAdd a New Member\n");
                    System.out.print("Enter Member Name: ");
                    String memberName = scanner.nextLine();
                    System.out.print("Enter Member Email: ");
                    String memberEmail = scanner.nextLine();
                    System.out.print("Enter Member Phone Number: ");
                    String memberPhone = scanner.nextLine();

                    member newMember = new member(memberName, memberEmail, memberPhone);
                    libraryService.addMember(newMember);
                    System.out.println("\nMember added successfully.\n");
                    break;

                case 6:
                    System.out.println("\nLoan a Book\n");
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Loan Date (yyyy-mm-dd): ");
                    String loanDateStr = scanner.nextLine();
                    System.out.print("Enter Due Date (yyyy-mm-dd): ");
                    String dueDateStr = scanner.nextLine();

                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date loanDate = dateFormat.parse(loanDateStr);
                        Date dueDate = dateFormat.parse(dueDateStr);

                        boolean loanSuccessful = libraryService.loanBook(bookId, memberId, loanDate, dueDate);
                        if (loanSuccessful) {
                            System.out.println("\nBook loaned successfully.\n");
                        } else {
                            System.out.println("\nThe book is currently loaned out.\n");
                        }
                    } catch (ParseException e) {
                        System.out.println("\nInvalid date format!! Please use yyyy-mm-dd format.\n");
                    }
                    break;

                case 7:
                    System.out.println("\nReturn a Book\n");
                    System.out.print("Enter Loan ID to return: ");
                    int loanId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter return date (yyyy-mm-dd): ");
                    String returnDateStr = scanner.nextLine();
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date returnDate = dateFormat.parse(returnDateStr);
                        boolean returnSuccessful = libraryService.returnBook(loanId, returnDate);
                        if (returnSuccessful) {
                            System.out.println("\nBook returned successfully.\n");
                        } else {
                            System.out.println("\nIt's already returned.\n");
                        }
                    } catch (ParseException e) {
                        System.out.println("Invalid date format!! Please use yyyy-mm-dd.\n");
                    }
                    break;

                case 8:
                    System.out.println("Exiting from the system...Bye..");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!! Please try again.");
            }
        }
    }
}
