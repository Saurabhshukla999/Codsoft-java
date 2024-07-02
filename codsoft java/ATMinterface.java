
import java.util.Scanner;

class BankAccount{
    private double balance;
    public BankAccount(double initialBalance) {
    }

    public double getBalance(){
        return balance;
    }

    public  boolean deposit(double amount){
        if (amount > 0) {
            balance +=amount;
            return true;
        }else{return false;}
    }

    public boolean withdraw(double amount){
        if (amount > 0 && amount <= balance) {
            balance -=amount;
            return true;
        }else{
            return false;
        }
    }
}
class ATM {
    private final BankAccount account;
    public ATM(BankAccount account){
        this.account = account;
    }
    void showMenu(){
        System.out.println("1.Check balance");
        System.out.println("2.Deposit");
        System.out.println("3.Withdraw");
        System.out.println("4.Exit");
    }

    void checkBalance(){
        System.out.println("your balance is :"+ account.getBalance());
    }

    void deposit(double amount){
        if (account.deposit(amount)) {
            System.out.println("successfully deposited:"+ amount);
        }else{
            System.out.println("invalid amount");
        }
    }

    void withdraw(double amount){
        if (account.withdraw(amount)) {
            System.out.println("succesfully withdrawn:" + amount);
        }else{
            System.out.println("invalid amount");
        }
    }

    public void processTransation(int option, double amount){
       switch (option) {
            case 1 -> checkBalance();
            case 2 -> deposit(amount);
            case 3 -> withdraw(amount);
            case 4 -> {
                System.out.println("exiting thankyou for using ATM");
            }
           default -> System.out.println("invalid option");
       }
    }
}

public class ATMinterface {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        ATM atm = new ATM(account);
        int option;
        double amount;

        try (Scanner scanner = new Scanner(System.in)) {
            do {
                atm.showMenu();
                System.out.println("please select an option");
                option = scanner.nextInt();
                if (option == 2 || option == 3){
                    System.out.println("enter amount:");
                    amount = scanner.nextDouble();
                }else{
                    amount = 0;
                }
                atm.processTransation(option, amount);
            } while (option!=4);
        }
    }
}