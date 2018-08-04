import model.Account;
import model.Customer;
import service.BankService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer("pierre-jean");
        Account account = new Account(customer, 100);

        BankService bankService = new BankService();

        System.out.print(">> ");
        Scanner scanner = new Scanner(System.in);

        double value = scanner.nextDouble();

        System.out.println("Withdrawing " + value + " EUR...");

        bankService.withdraw(value, account, customer);

        System.out.println("Account has " + account.getAmount() + " EUR now");
    }
}
