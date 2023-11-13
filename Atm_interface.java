import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Account {
    private int userId;
    private int pin;
    private double balance;
    private List<String> transactionHistory;

    public Account(int userId, int pin) {
        this.userId = userId;
        this.pin = pin;
        this.balance = 1000.0; // Initial balance
        this.transactionHistory = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void addToTransactionHistory(String transaction) {
        transactionHistory.add(transaction);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

public class Atm_interface {
    private static final int MAX_PIN_ATTEMPTS = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Account> accounts = new HashMap<>();

        while (true) {
            System.out.println("Welcome to the  ATM!");

            
            System.out.print("Enter User ID (4-digit): ");
            int userId = scanner.nextInt();

            
            if (accounts.containsKey(userId)) {
                Account account = accounts.get(userId);
                int pinAttempts = 0;

                while (pinAttempts < MAX_PIN_ATTEMPTS) {
                    System.out.print("Enter PIN (4-digit): ");
                    int pin = scanner.nextInt();

                    if (account.getPin() == pin) {
                        performTransactions(account, accounts, scanner);
                        break;
                    } else {
                        pinAttempts++;
                        System.out.println("Incorrect PIN. Attempt " + pinAttempts + " of " + MAX_PIN_ATTEMPTS);
                    }
                }

                if (pinAttempts == MAX_PIN_ATTEMPTS) {
                    System.out.println("Max PIN attempts reached. Account locked.");
                }
            } else {
                System.out.print("Account not found. Create a new PIN to register (4-digit): ");
                int pin = scanner.nextInt();
                Account newAccount = new Account(userId, pin);
                accounts.put(userId, newAccount);
                System.out.println("Account created successfully.");
                performTransactions(newAccount, accounts, scanner);
            }
        }
    }

    public static void performTransactions(Account account, Map<Integer, Account> accounts, Scanner scanner) {
        int choice;

        while (true) {
            System.out.println("ATM Menu:");
            System.out.println("1. View Transaction History");				
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. View Balance");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
               	 case 1:
                    viewTransactionHistory(account);
                    break;							
                case 2:
                    performWithdrawal(account, scanner);
                    break;
                case 3:
                    performDeposit(account, scanner);
                    break;
                case 4:
                    performTransfer(account, accounts, scanner);
                    break;
                case 5:
                    System.out.println("Current Balance: $" + account.getBalance());
                   break;
                case 6:
                    System.out.println("Thank you for using the ATM service. Goodbye!");
                    return; 
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void performWithdrawal(Account account, Scanner scanner) {
        System.out.print("Enter withdrawal amount: $");
        double withdrawAmount = scanner.nextDouble();

        if (withdrawAmount > 0) {
            if (account.getBalance() >= withdrawAmount) {
                account.setBalance(account.getBalance() - withdrawAmount);
                account.addToTransactionHistory("Withdrawn: $" + withdrawAmount);
                System.out.println("Withdrawn: $" + withdrawAmount);
                System.out.println("New Balance: $" + account.getBalance());
            } else {
                System.out.println("Insufficient balance!");
            }
        } else {
            System.out.println("Invalid amount!");
        }
    }

    public static void performDeposit(Account account, Scanner scanner) {
        System.out.print("Enter deposit amount: $");
        double depositAmount = scanner.nextDouble();

        if (depositAmount > 0) {
            account.setBalance(account.getBalance() + depositAmount);
            account.addToTransactionHistory("Deposited: $" + depositAmount);
            System.out.println("Deposited: $" + depositAmount);
            System.out.println("New Balance: $" + account.getBalance());
        } else {
            System.out.println("Invalid amount!");
        }
    }

    public static void performTransfer(Account account, Map<Integer, Account> accounts, Scanner scanner) {
        System.out.print("Enter recipient's User ID: ");
        int recipientUserId = scanner.nextInt();

        if (accounts.containsKey(recipientUserId)) {
            System.out.print("Enter transfer amount: $");
            double transferAmount = scanner.nextDouble();

            if (transferAmount > 0) {
                if (account.getBalance() >= transferAmount) {
                    Account recipientAccount = accounts.get(recipientUserId);
                    recipientAccount.setBalance(recipientAccount.getBalance() + transferAmount);
                    account.setBalance(account.getBalance() - transferAmount);
                    account.addToTransactionHistory("Transferred: $" + transferAmount + " to User ID " + recipientUserId);
                    System.out.println("Transferred: $" + transferAmount);
                    System.out.println("New Balance: $" + account.getBalance());
                } else {
                    System.out.println("Insufficient balance!");
                }
            } else {
                System.out.println("Invalid amount!");
            }
        } else {
            System.out.println("Recipient account not found!");
        }
    }

    public static void viewTransactionHistory(Account account) {
        System.out.println("Transaction History:");
        for (String transaction : account.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }
}
