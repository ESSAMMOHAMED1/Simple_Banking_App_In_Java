class Bank {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    // Constructor to initialize the Bank object
    public Bank(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit method
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. New balance: $" + balance);
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

     // Display account details
    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Balance: $" + balance);
    }
}

// Command interface
interface Command {
    void execute(Bank[] accounts, Scanner scanner);
}

// Concrete command to display all account details
class DisplayCommand implements Command {
    @Override
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
    @Override
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
    @Override
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
    @Override
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