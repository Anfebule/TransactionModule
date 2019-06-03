package com.revolut.controller;

import com.revolut.model.Account;
import com.revolut.service.AccountService;

public class TransactionController {

    public String transfer(Integer recipientAccountNumber, Integer senderAccountNumber, Integer amount){

        String message;
        AccountService accountService = new AccountService();
        Account recipientAccount = accountService.getAccountById(recipientAccountNumber);
        Account senderAccount = accountService.getAccountById(senderAccountNumber);

        if (recipientAccount != null && senderAccount != null){
            senderAccount.setBalance(senderAccount.getBalance() - amount);
            recipientAccount.setBalance(recipientAccount.getBalance() + amount);

            accountService.updateAccountBalance(senderAccount);
            accountService.updateAccountBalance(recipientAccount);
            accountService.closeSession();

            message = "transaction successful";
        } else {
            message = "transaction failed";
        }
        accountService.closeSession();

        return message;
    }
}
