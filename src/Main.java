import model.Account;
import model.Customer;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("C001", "Thabo Modise", "thabo@example.com", "pass123");
        Account savings = new Account("ACC-101", "Savings", 1000.00);
        Account checking = new Account("ACC-102", "Checking", 250.00);

        customer.addAccount(savings);
        customer.addAccount(checking);

        savings.deposit(500.00);
        savings.transfer(checking, 300.00);

        System.out.println("Customer: " + customer.getFullName());
        System.out.println("Savings Balance: " + savings.getBalance());   // Expected: 1200.0
        System.out.println("Checking Balance: " + checking.getBalance()); // Expected: 550.0
    }
}