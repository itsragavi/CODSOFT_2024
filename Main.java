import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// Custom exception class for insufficient funds
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// Class to represent a user's bank account
class BankAccount {
    private double balance;
    private double totalDeposited; // Track total deposited amount
    private List<Double> depositHistory; // List to store deposit amounts

    // Constructor
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
        this.totalDeposited = 0.0;
        this.depositHistory = new ArrayList<>();
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            totalDeposited += amount; // Update total deposited amount
            depositHistory.add(amount); // Store deposit amount
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds in account.");
        }
        if (amount > totalDeposited) {
            throw new InsufficientFundsException("Cannot withdraw more than total deposited amount.");
        }
        balance -= amount;
        System.out.println("Withdrew: $" + amount);
    }

    // Method to check balance
    public double checkBalance() {
        return balance;
    }

    // Method to get deposit history
    public List<Double> getDepositHistory() {
        return depositHistory;
    }
}

// Class to represent the ATM machine
class ATM {
    private BankAccount account;
    private Scanner scanner;

    // Constructor
    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // Method to start the ATM machine
    public void start() {
        boolean hasDeposited = false;
        int option;

        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            try {
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        handleDeposit();
                        hasDeposited = true; // Mark as deposited
                        break;
                    case 2:
                        if (hasDeposited) {
                            handleWithdrawal();
                        } else {
                            System.out.println("Please make a deposit before withdrawing.");
                        }
                        break;
                    case 3:
                        handleCheckBalance();
                        break;
                    case 4:
                        System.out.println("Exiting. Thank you for using the ATM.");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // clear the invalid input
                option = 0; // force loop continuation
            }
        } while (option != 4);
    }

    // Method to handle withdrawal
    private void handleWithdrawal() {
        System.out.print("Enter amount to withdraw: ");
        try {
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a number.");
            scanner.next(); // clear the invalid input
        } catch (IllegalArgumentException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to handle deposit
    private void handleDeposit() {
        System.out.print("Enter amount to deposit: ");
        try {
            double amount = scanner.nextDouble();
            account.deposit(amount);
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a number.");
            scanner.next(); // clear the invalid input
        }
    }

    // Method to handle checking balance
    private void handleCheckBalance() {
        System.out.println("Current balance: $" + account.checkBalance());
    }
}

// Main class to run the ATM simulation
public class Main {
    public static void main(String[] args) {
        // Create a bank account with an initial balance
        BankAccount account = new BankAccount(1000.0);

        // Create an ATM machine with the bank account
        ATM atm = new ATM(account);

        // Start the ATM
        atm.start();
    }
}
