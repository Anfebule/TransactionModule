package com.revolut.controller;

import com.revolut.model.Account;
import com.revolut.model.TransactionResult;
import com.revolut.service.AccountService;

public class TransactionController {

    public TransactionResult transfer(Integer recipientAccountNumber, Integer senderAccountNumber, Integer amount){

        TransactionResult transactionResult = new TransactionResult();
        AccountService accountService = new AccountService();
        Account recipientAccount = accountService.getAccountById(recipientAccountNumber);
        Account senderAccount = accountService.getAccountById(senderAccountNumber);

        if (recipientAccount != null && senderAccount != null){
            senderAccount.setBalance(senderAccount.getBalance() - amount);
            recipientAccount.setBalance(recipientAccount.getBalance() + amount);

            accountService.updateAccountBalance(senderAccount);
            accountService.updateAccountBalance(recipientAccount);
            accountService.closeSession();

            transactionResult.setMessage("transaction successful");
        } else {
            transactionResult.setMessage("transaction failed");
            transactionResult.setDescription("Account not found");
        }
        accountService.closeSession();

        return transactionResult;
    }
}
