import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String userName;
    private String userPin;
    private double balance;
    private List<Transaction> transactionHistory;

    public User(String userName, String userPin, double initialBalance) {
        this.userName = userName;
        this.userPin = userPin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getuserName() {
        return userName;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addTransaction(Transaction transaction) {
        this.transactionHistory.add(transaction);
    }
}

class Transaction {
    private String transactionType;
    private double amount;

    public Transaction(String transactionType, double amount) {
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }
}

class TransactionHistory {
    public static void displayTransactionHistory(User user) {
        System.out.println("Transaction History for User: " + user.getuserName());
        List<Transaction> transactions = user.getTransactionHistory();
        for (Transaction transaction : transactions) {
            System.out.println(transaction.getTransactionType() + ": " + transaction.getAmount());
        }
    }
}

class ATM {
    private User currentUser;

    public void login(String userName, String userPin) {
        
        if ("abcd".equals(userName) && "5678".equals(userPin)) {
            
            currentUser = new User(userName, userPin, 1000.0);
            System.out.println("Login successful. Welcome, " + userName + "!");
        } else {
            System.out.println("Invalid credentials. Login failed.");
        }
    }

    public void withdraw(double amount) {
        if (currentUser != null && amount > 0 && amount <= currentUser.getBalance()) {
            currentUser.setBalance(currentUser.getBalance() - amount);
            currentUser.addTransaction(new Transaction("Withdrawal", amount));
            System.out.println("Withdrawal successful. Remaining balance: " + currentUser.getBalance());
        } else {
            System.out.println("Withdrawal failed. Insufficient funds or invalid amount.");
        }
    }

    public void deposit(double amount) {
        if (currentUser != null && amount > 0) {
            currentUser.setBalance(currentUser.getBalance() + amount);
            currentUser.addTransaction(new Transaction("Deposit", amount));
            System.out.println("Deposit successful. Updated balance: " + currentUser.getBalance());
        } else {
            System.out.println("Deposit failed. Invalid amount.");
        }
    }

    public void transfer(String recipientUserId, double amount) {
        if (currentUser != null && amount > 0 && amount <= currentUser.getBalance()) {
            
            currentUser.setBalance(currentUser.getBalance() - amount);
            currentUser.addTransaction(new Transaction("Transfer to " + recipientUserId, amount));
            System.out.println("Transfer successful. Remaining balance: " + currentUser.getBalance());
        } else {
            System.out.println("Transfer failed. Insufficient funds or invalid amount.");
        }
    }

    public void quit() {
        System.out.println("Quitting ATM. Have a nice day!");
        System.exit(0);
    }

    public User getCurrentUser() {
        return currentUser;
    }
}

public class ATMApp {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter User Name: ");
        String userName = sc.nextLine();
        System.out.print("Enter User PIN: ");
        String userPin = sc.nextLine();

        
        atm.login(userName, userPin);

        
        while (true) {
            System.out.println("\nATM Functionalities:");
            System.out.println("1. View Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    TransactionHistory.displayTransactionHistory(atm.getCurrentUser());
                    break;
                case 2:
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient User Name: ");
                    String recipientUserId = sc.next();
                    System.out.print("Enter the amount to transfer: ");
                    double transferAmount = sc.nextDouble();
                    atm.transfer(recipientUserId, transferAmount);
                    break;
                case 5:
                    atm.quit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
        }
        
    }
}
