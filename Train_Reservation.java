import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class TrainRoute {
    private String startStation;
    private String departureStation;
    private String trainNumber;
    private String trainName;

    public TrainRoute(String startStation, String departureStation, String trainNumber, String trainName) {
        this.startStation = startStation;
        this.departureStation = departureStation;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
    }

    public String getStartStation() {
        return startStation;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }
}

class Reservation {
    private String pnr;
    private String trainNumber;
    private String startStation;
    private String departureStation;
    private String travelDate;
    private String selectedClass;

    public Reservation(String trainNumber, String startStation, String departureStation, String travelDate, String selectedClass) {
        this.pnr = UUID.randomUUID().toString();
        this.trainNumber = trainNumber;
        this.startStation = startStation;
        this.departureStation = departureStation;
        this.travelDate = travelDate;
        this.selectedClass = selectedClass;
    }

    public String getPNR() {
        return pnr;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getStartStation() {
        return startStation;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public String getSelectedClass() {
        return selectedClass;
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

public class Train_Reservation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<User> users = new ArrayList<>();
        users.add(new User("admin", "password"));

        System.out.println("Welcome to the Online Reservation System");

        // Login or Register
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            // User wants to login
            login(users, scanner);
        } else if (choice == 2) {
            // User wants to register
            register(users, scanner);
            System.out.println("Registration successful. You can now log in.");
            login(users, scanner);
        } else {
            System.out.println("Invalid choice. Please select a valid option.");
        }
    }

    public static void login(List<User> users, Scanner scanner) {
        System.out.print("Enter your username: ");
        String inputUsername = scanner.nextLine();
        System.out.print("Enter your password: ");
        String inputPassword = scanner.nextLine();

        User user = findUser(users, inputUsername);

        if (user != null && user.getPassword().equals(inputPassword)) {
            System.out.println("Login successful. You can now access the main system.");
            runReservationSystem();
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    public static void register(List<User> users, Scanner scanner) {
        System.out.print("Enter a new username: ");
        String newUsername = scanner.nextLine();
        System.out.print("Enter a new password: ");
        String newPassword = scanner.nextLine();

        User existingUser = findUser(users, newUsername);

        if (existingUser != null) {
            System.out.println("Username is already taken. Please choose a different one.");
        } else {
            users.add(new User(newUsername, newPassword));
        }
    }

    public static User findUser(List<User> users, String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void runReservationSystem() {
        Scanner scanner = new Scanner(System.in);
        List<TrainRoute> trainRoutes = new ArrayList<>();
        trainRoutes.add(new TrainRoute("Station A", "Station B", "Train001", "Express Train 1"));
        trainRoutes.add(new TrainRoute("Station B", "Station C", "Train002", "Local Train 1"));
        trainRoutes.add(new TrainRoute("Station A", "Station D", "Train003", "Express Train 2"));

        List<Reservation> reservations = new ArrayList<>();

        while (true) {
            System.out.println("Reservation System Menu:");
            System.out.println("1. Make a Reservation");
            System.out.println("2. View Reservations");
            System.out.println("3. Cancel a Reservation");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the starting station: ");
                    String startStation = scanner.nextLine();
                    System.out.print("Enter the departure station: ");
                    String departureStation = scanner.nextLine();

                    // Show available trains for the selected stations
                    System.out.println("Available Trains for Route:");

                    boolean foundTrains = false;
                    for (TrainRoute route : trainRoutes) {
                        if (route.getStartStation().equals(startStation) && route.getDepartureStation().equals(departureStation)) {
                            foundTrains = true;
                            System.out.println("Train Number: " + route.getTrainNumber());
                            System.out.println("Train Name: " + route.getTrainName());
                            System.out.println();
                        }
                    }

                    if (!foundTrains) {
                        System.out.println("No trains available for the selected route.");
                        break;
                    }

                    System.out.print("Select a train from the list (Enter Train Number): ");
                    String trainNumber = scanner.nextLine();

                    // Add a prompt for selecting the class
                    System.out.print("Select a class (e.g., First Class, Second Class): ");
                    String selectedClass = scanner.nextLine();

                    // Add a prompt for selecting the date of the journey
                    System.out.print("Enter the date of the journey (e.g., MM/DD/YYYY): ");
                    String journeyDate = scanner.nextLine();

                    Reservation reservation = new Reservation(trainNumber, startStation, departureStation, journeyDate, selectedClass);
                    reservations.add(reservation);
                    System.out.println("Reservation successful. Your PNR is: " + reservation.getPNR());
                    break;

                case 2:
                    System.out.println("Your Reservations:");
                    for (Reservation res : reservations) {
                        System.out.println("PNR: " + res.getPNR());
                        System.out.println("Train Number: " + res.getTrainNumber());
                        System.out.println("From: " + res.getStartStation());
                        System.out.println("To: " + res.getDepartureStation());
                        System.out.println("Date: " + res.getTravelDate());
                        System.out.println("Class: " + res.getSelectedClass());
                        System.out.println();
                    }
                    break;

                case 3:
                    System.out.print("Enter your PNR to cancel the reservation: ");
                    String pnrToCancel = scanner.nextLine();
                    boolean removed = false;

                    for (Reservation res : reservations) {
                        if (res.getPNR().equals(pnrToCancel)) {
                            reservations.remove(res);
                            removed = true;
                            System.out.println("Reservation with PNR " + pnrToCancel + " has been canceled.");
                            break;
                        }
                    }

                    if (!removed) {
                        System.out.println("No reservation found with PNR " + pnrToCancel);
                    }
                    break;

                case 4:
                    System.out.println("Thank you for using the Online Reservation System. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
