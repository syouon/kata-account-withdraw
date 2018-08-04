package model;

/**
 * An account belongs to only one customer and has an initial value
 */
public class Account {

    private final Customer customer;
    private double amount;

    public Account(Customer customer, double amount) {
        this.customer = customer;
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
