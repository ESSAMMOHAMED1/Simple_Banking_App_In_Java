// Bank class representing a bank account
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