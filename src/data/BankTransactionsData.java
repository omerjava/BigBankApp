package data;

import entity.BankTransaction;

public class BankTransactionsData {
    private BankTransaction[] bankTransactions = {};

    public BankTransaction[] getBankTransactions() {
        return bankTransactions;
    }
    public void setBankTransactions(BankTransaction[] bankTransactions) {
        this.bankTransactions = bankTransactions;
    }
}
