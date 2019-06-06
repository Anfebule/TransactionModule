package com.revolut.repository;

import com.revolut.model.Account;
import com.revolut.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class AccountRepositoryTest {

    private User user;

    private Account account;

    @Mock
    private AccountRepositoryImpl accountRepository;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp(){
        user = new User();
        user.setName("Andres");
        user.setPhone("301555");

        account = new Account();
        account.setAccountNumber(100);
        account.setBalance(Double.valueOf("1000"));
        account.setUser(user);

        accountRepository = new AccountRepositoryImpl();
    }

    @Test
    public void addAccount(){
        assertNotNull(accountRepository.addAccount(account));
    }

    @Test
    public void updateAccountBalance (){
        accountRepository.addAccount(account);
        accountRepository.updateAccountBalance(account);

        Account accountFound = accountRepository.getAccountById(account.getAccountNumber());
        assertEquals(accountFound.getBalance(), account.getBalance());
    }

    @Test
    public void  getAccountById (){
        accountRepository.addAccount(account);
        assertNotNull(accountRepository.getAccountById(account.getAccountNumber()));
    }
}
