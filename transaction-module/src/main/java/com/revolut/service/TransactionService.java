package com.revolut.service;

import com.revolut.model.TransactionResult;

/**
 * Contains all transactions methods allowed by the system
 */
public interface TransactionService {

    /**
     * Transfers money between two accounts
     * @param recipientAccountNumber recipient account number
     * @param senderAccountNumber sender account number
     * @param amount amount to transfer
     * @return a TransferResult object with a successful or failed transaction
     */
    TransactionResult transfer(Integer recipientAccountNumber, Integer senderAccountNumber, Double amount);
}
