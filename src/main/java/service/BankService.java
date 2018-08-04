package service;

import exception.BankAccountException;
import model.Account;
import model.Customer;

public class BankService {

    /**
     * Withdraw money from an account that belongs to a customer.
     * To withdraw, enough money is needed in the account. Unless, an exception is thrown.
     * If the account does not belong to the customer, an exception is thrown.
     *
     * @param value    the amount to withdraw
     * @param account  the account to debit
     * @param customer the customer who wants to withdraw
     */
    public void withdraw(double value, Account account, Customer customer) {
        checkUser(customer, account);
        checkAccountAmount(value, account);

        double currentAmount = account.getAmount();
        double newAmount = currentAmount - value;

        account.setAmount(newAmount);
    }

    private void checkUser(Customer customer, Account account) {
        if (!account.getCustomer().equals(customer)) {
            throw new BankAccountException("The account does not belong to '" + customer.getName() + "'");
        }
    }

    private void checkAccountAmount(double value, Account account) {
        if (value > account.getAmount()) {
            throw new BankAccountException("Amount is too low");
        }
    }
}
