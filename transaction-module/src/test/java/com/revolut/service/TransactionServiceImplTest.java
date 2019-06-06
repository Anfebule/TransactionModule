package com.revolut.service;

import com.revolut.model.Account;
import com.revolut.model.TransactionResult;
import com.revolut.repository.AccountRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionServiceImpl transactionController;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test to check successful transaction
     */
    @Test
    public void transferSuccessful(){
        Account recipientAccount = new Account();
        recipientAccount.setBalance(Double.valueOf("1000"));
        recipientAccount.setAccountNumber(0);

        Account senderAccount = new Account();
        senderAccount.setBalance(Double.valueOf("1000"));
        senderAccount.setAccountNumber(1);

        when(accountRepository.getAccountById(0)).thenReturn(recipientAccount);
        when(accountRepository.getAccountById(1)).thenReturn(senderAccount);
        TransactionResult transactionResult = transactionController.transfer(0, 1, Double.valueOf("1000"));
        assertEquals(transactionResult.getMessage(), "transaction successful" );
    }

    /**
     * Test to check failed transaction
     */
    @Test
    public void transferFailed(){
        Account recipientAccount = new Account();
        recipientAccount.setBalance(Double.valueOf("0"));
        recipientAccount.setAccountNumber(0);

        Account senderAccount = new Account();
        senderAccount.setBalance(Double.valueOf("0"));
        senderAccount.setAccountNumber(1);

        when(accountRepository.getAccountById(0)).thenReturn(recipientAccount);
        when(accountRepository.getAccountById(1)).thenReturn(senderAccount);
        TransactionResult transactionResult = transactionController.transfer(0, 1, Double.valueOf("1000"));
        assertEquals(transactionResult.getMessage(), "transaction failed" );
    }
}
