import java.util.Scanner;

// Bank class representing a bank account
class Bank {
    //declare private instance variables to store account details.
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    // Constructor to initialize the Bank object
    //constructor of the Bank class
    public Bank(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    // creat getter methods to retrieve the values of the private instance variables.
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    //  method  depositing a specified amount into the account.
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. New balance: $" + balance);
    }

    // withdrawing a specified amount from the account
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    // method displays the account details.
    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Balance: $" + balance);
    }
}

// define an interface named Command with a single method execute.
interface Command {
    void execute(Bank[] accounts, Scanner scanner);
}

// Concrete command to display all account details
class DisplayCommand implements Command {
    
    public void execute(Bank[] accounts, Scanner scanner) {
        System.out.println("\nAll Account Details:");
        for (Bank account : accounts) {
            account.displayAccountDetails();
            System.out.println("----------------------");
        }
    }
}

// Concrete command to search by account number
class SearchCommand implements Command {
    
    public void execute(Bank[] accounts, Scanner scanner) {
        System.out.print("Enter account number to search: ");
        String searchAccountNumber = scanner.next();

        boolean accountFound = false;
        for (Bank account : accounts) {
            if (account.getAccountNumber().equals(searchAccountNumber)) {
                account.displayAccountDetails();
                accountFound = true;
                break;
            }
        }

        if (!accountFound) {
            System.out.println("Account not found with account number: " + searchAccountNumber);
        }
    }
}

// Concrete command to deposit amount
class DepositCommand implements Command {
    
    public void execute(Bank[] accounts, Scanner scanner) {
        System.out.print("Enter account number to deposit amount: ");
        String accountNumber = scanner.next();

        for (Bank account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                System.out.print("Enter amount to deposit: $");
                double amount = scanner.nextDouble();
                account.deposit(amount);
                break;
            }
        }
    }
}

// Concrete command to withdraw amount
class WithdrawCommand implements Command {
    
    public void execute(Bank[] accounts, Scanner scanner) {
        System.out.print("Enter account number to withdraw amount: ");
        String accountNumber = scanner.next();

        for (Bank account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                System.out.print("Enter amount to withdraw: $");
                double amount = scanner.nextDouble();
                account.withdraw(amount);
                break;
            }
        }
    }
}

// Invoker class
class MenuInvoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand(Bank[] accounts, Scanner scanner) {
        command.execute(accounts, scanner);
    }
}

public class BankingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.println("-------------------------------------------------------------");
        System.out.println("__        __  _____   _        ____    ___    __  __   _____ ");
        System.out.println("\\ \\      / / | ____| | |      / ___|  / _ \\  |  \\/  | | ____|");
        System.out.println(" \\ \\ /\\ / /  |  _|   | |     | |     | | | | | |\\/| | |  _|  ");
        System.out.println("  \\ V  V /   | |___  | |___  | |___  | |_| | | |  | | | |___ ");
        System.out.println("   \\_/\\_/    |_____| |_____|  \\____|  \\___/  |_|  |_| |_____|");
        System.out.println("--------------------------------------------------------------");  



        System.out.print("Enter the number of customers: ");
        int numCustomers = scanner.nextInt();

        Bank[] accounts = new Bank[numCustomers];

        // Input customer details
        for (int i = 0; i < numCustomers; i++) {
            System.out.println("\nEnter details for customer " + (i + 1) + ":");
            System.out.print("Enter account number: ");
            String accountNumber = scanner.next();
            System.out.print("Enter account holder name: ");
            String accountHolderName = scanner.next();
            System.out.print("Enter initial balance: $");
            double initialBalance = scanner.nextDouble();

            accounts[i] = new Bank(accountNumber, accountHolderName, initialBalance);
        }

        // Create command objects
        Command displayCommand = new DisplayCommand();
        Command searchCommand = new SearchCommand();
        Command depositCommand = new DepositCommand();
        Command withdrawCommand = new WithdrawCommand();

        MenuInvoker menuInvoker = new MenuInvoker();
        int choice;
        do {
            // Display menu
            System.out.println("\nMenu:");
            System.out.println("1. Display all account details");
            System.out.println("2. Search by account number");
            System.out.println("3. Deposit amount");
            System.out.println("4. Withdraw amount");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            // Set the command based on user choice
            switch (choice) {
                case 1:
                    menuInvoker.setCommand(displayCommand);
                    break;
                case 2:
                    menuInvoker.setCommand(searchCommand);
                    break;
                case 3:
                    menuInvoker.setCommand(depositCommand);
                    break;
                case 4:
                    menuInvoker.setCommand(withdrawCommand);
                    break;
                case 5:
                    System.out.println("Thank You For Banking With Us ");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    continue;
            }

            // Execute the command
            menuInvoker.executeCommand(accounts, scanner);
        } while (choice != 5);

        scanner.close();
    }
}
