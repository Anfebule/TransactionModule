package com.revolut.service;

import com.revolut.model.TransactionResult;

public interface TransactionService {
    TransactionResult transfer(Integer recipientAccountNumber, Integer senderAccountNumber, Double amount);
}
