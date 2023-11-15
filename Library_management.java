import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean available;
    private boolean returned;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true;
        this.returned = true; // Initialize as returned
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}

class User {
    private int userId;
    private String username;

    public User(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}

class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(int bookId, String title, String author) {
        Book book = new Book(bookId, title, author);
        books.add(book);
    }

    public void addUser(int userId, String username) {
        User user = new User(userId, username);
        users.add(user);
    }

    public void issueBook(int bookId, int userId) {
        Book book = findBookById(bookId);
        User user = findUserById(userId);

        if (book != null && user != null && book.isAvailable()) {
            book.setAvailable(false);
            book.setReturned(false); // Book is issued
            System.out.println(user.getUsername() + " has issued the book: " + book.getTitle());
        } else {
            System.out.println("Book not available or user not found.");
        }
    }

    public void returnBook(int bookId) {
        Book book = findBookById(bookId);

        if (book != null && !book.isReturned()) {
            book.setAvailable(true);
            book.setReturned(true); // Book is returned
            System.out.println("Book " + book.getTitle() + " has been returned.");
        } else {
            System.out.println("Book not issued or not found.");
        }
    }

    public void displayBooks() {
        System.out.println("Library Books:");
        for (Book book : books) {
            System.out.println("Book ID: " + book.getBookId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Available: " + (book.isAvailable() ? "Yes" : "No"));
            System.out.println("Returned: " + (book.isReturned() ? "Yes" : "No"));
            System.out.println();
        }
    }

    private Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    private User findUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }
}

public class Library_management  {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library Management System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Books");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Book Author: ");
                    String author = scanner.nextLine();
                    library.addBook(bookId, title, author);
                    System.out.println("Book added to the library.");
                    break;
                case 2:
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter User Name: ");
                    String username = scanner.nextLine();
                    library.addUser(userId, username);
                    System.out.println("User added to the library.");
                    break;
                case 3:
                    System.out.print("Enter Book ID to Issue: ");
                    int bookToIssue = scanner.nextInt();
                    System.out.print("Enter User ID to Issue: ");
                    int userToIssue = scanner.nextInt();
                    library.issueBook(bookToIssue, userToIssue);
                    break;
                case 4:
                    System.out.print("Enter Book ID to Return: ");
                    int bookToReturn = scanner.nextInt();
                    library.returnBook(bookToReturn);
                    break;
                case 5:
                    library.displayBooks();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
