import exception.BankAccountException;
import model.Account;
import model.Customer;
import org.junit.Before;
import org.junit.Test;
import service.BankService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BankServiceTest {

    private BankService bankService;
    private Customer customer;
    private Customer evilCustomer;
    private Account account;

    @Before
    public void setUp() {
        bankService = new BankService();
        customer = new Customer("pierre-jean");
        evilCustomer = new Customer("Evil pierre-jean");
        account = new Account(customer, 100);
    }

    @Test
    public void withdraw_should_decrease_account_value() {
        bankService.withdraw(10, account, customer);

        assertThat(account.getAmount()).isEqualTo(90);
    }

    @Test
    public void withdraw_should_fail_if_there_is_not_enough_money() {
        assertThatThrownBy(() -> bankService.withdraw(110, account, customer))
                .isInstanceOf(BankAccountException.class)
                .hasMessage("Amount is too low");

        assertThat(account.getAmount()).isEqualTo(100);

        bankService.withdraw(50, account, customer);

        assertThatThrownBy(() -> bankService.withdraw(60, account, customer))
                .isInstanceOf(BankAccountException.class)
                .hasMessage("Amount is too low");
    }

    @Test
    public void withdraw_should_fail_if_user_is_not_the_account_owner() {
        assertThatThrownBy(() -> bankService.withdraw(10, account, evilCustomer))
                .isInstanceOf(BankAccountException.class)
                .hasMessage("The account does not belong to 'Evil pierre-jean'");
    }
}
