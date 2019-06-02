package com.revolut.controller;

import com.revolut.model.Account;

public class Transfer {

    public String start(Integer recipientAccountNumber, Integer senderAccountNumber, Integer amount){

        try {
            Account recipientAccount = accountDAO.getAccount(recipientAccountNumber);
            Account senderAccount = accountDAO.getAccount(senderAccountNumber);

            senderAccount.setBalance(senderAccount.getBalance() - amount);
            recipientAccount.setBalance(recipientAccount.getBalance() + amount);

            return "transaction successful";
        } catch (Exception e) {
            throw e;
        }
    }
}
