package service;

import data.BankTransactionsData;
import entity.BankTransaction;
import response.TransactionResponse;

import java.util.Arrays;

public class BankTransactionsServiceImpl implements BankTransactionService{

    BankTransactionsData data = new BankTransactionsData();
    @Override
    public TransactionResponse makeBankTransaction(BankTransaction newBankTransaction) {
        try {
            data.setBankTransactions(Arrays.copyOf(data.getBankTransactions(), data.getBankTransactions().length + 1));
            data.getBankTransactions()[data.getBankTransactions().length - 1] = newBankTransaction;
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }

        TransactionResponse transactionResponse = new TransactionResponse();

        transactionResponse.setTransactionName(newBankTransaction.getTransactionName());
        transactionResponse.setAmount(newBankTransaction.getAmount());
        transactionResponse.setReceiverIban(newBankTransaction.getReceiverIban());
        transactionResponse.setComment(newBankTransaction.getComment());
        transactionResponse.setDate(newBankTransaction.getDate());

        return transactionResponse;
    }

    @Override
    public void printIncomingTransactions(String receiver) {
        for (BankTransaction bankTransaction : data.getBankTransactions()) {
            if(receiver.equalsIgnoreCase(bankTransaction.getReceiverIban())){
                System.out.println(bankTransaction);
            }
        }
    }

    @Override
    public void printOutgoingTransactions(String sender) {
        for (BankTransaction bankTransaction : data.getBankTransactions()) {
            if(sender.equalsIgnoreCase(bankTransaction.getSenderIban())){
                System.out.println(bankTransaction);
            }
        }
    }
}
