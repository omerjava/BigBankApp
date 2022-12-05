package service;

import entity.BankTransaction;
import response.TransactionResponse;

public interface BankTransactionService {

    TransactionResponse makeBankTransaction(BankTransaction bankTransaction);
    void printIncomingTransactions(String receiver);
    void printOutgoingTransactions(String sender);

}
