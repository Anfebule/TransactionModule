package com.revolut.repository;

import com.revolut.model.Account;
import com.revolut.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class AccountRepositoryTest {

    private Account account;

    private AccountRepositoryImpl accountRepository;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp(){
        User user = new User();
        user.setName("Andres");
        user.setPhone("301555");

        account = new Account();
        account.setBalance(Double.valueOf("1000"));
        account.setUser(user);

        accountRepository = new AccountRepositoryImpl();
    }

    /**
     * Test to check if account is added.
     * Account numbers are hard-coded for the sake of this test (No endpoint yet to get already created accounts)
     */
    @Test
    public void addAccount(){
        account.setAccountNumber(100);
        assertNotNull(accountRepository.addAccount(account));
    }

    /**
     * Test to check if account balance is updated
     * Account numbers are hard-coded for the sake of this test (No endpoint yet to get already created accounts)
     */
    @Test
    public void updateAccountBalance (){
        account.setAccountNumber(101);
        accountRepository.addAccount(account);
        accountRepository.updateAccountBalance(account);

        Account accountFound = accountRepository.getAccountById(account.getAccountNumber());
        assertEquals(accountFound.getBalance(), account.getBalance());
    }

    /**
     * Test to check if account is found
     * Account numbers are hard-coded for the sake of this test (No endpoint yet to get already created accounts)
     */
    @Test
    public void  getAccountById (){
        account.setAccountNumber(102);
        accountRepository.addAccount(account);
        assertNotNull(accountRepository.getAccountById(account.getAccountNumber()));
    }
}
