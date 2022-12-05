package entity;

import exception.InvalidCommentException;
import exception.InvalidTransferAmountException;
import utility.ValidityCheck;

import java.time.Instant;

public class BankTransaction {

    private String transactionName = "Normal Transfer";
    private double amount;
    private String senderIban;
    private String receiverIban;
    private String comment = "No Comment";
    private Instant date;

    public BankTransaction( double amount, String senderIban, String receiverIban, Instant date) {
        this.amount = amount;
        this.senderIban = senderIban;
        this.receiverIban = receiverIban;
        this.date = date;
    }
    public BankTransaction(String transactionName, double amount, String senderIban,
                           String receiverIban, String comment, Instant date) {
        this.transactionName = transactionName;
        this.amount = amount;
        this.senderIban = senderIban;
        this.receiverIban = receiverIban;
        this.comment = comment;
        this.date = date;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        if (comment.trim().length()==0){
            comment="Normal Transfer";
        }
        this.transactionName = transactionName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) throws InvalidTransferAmountException {
        if(amount<=0){
            throw new InvalidTransferAmountException("\nYou must enter a positive amount of money to make a transfer!\n");
        }
        this.amount = amount;
    }

    public String  getSenderIban() {
        return senderIban;
    }

    public void setSenderIban(String sender) {
        this.senderIban = sender;
    }

    public String getReceiverIban() {
        return receiverIban;
    }

    public void setReceiverIban(String receiver) {
        this.receiverIban = receiver;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) throws InvalidCommentException {
        if (comment.trim().length()==0){
            comment="No Comment";
        }
        this.comment = ValidityCheck.commentValidityCheck(comment);;
    }
    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = Instant.now();
    }

    @Override
    public String toString() {

        return "Transaction{" +
                "Transaction Name='" + transactionName + '\'' +
                ", Amount of Transfer=" + amount +
                ", Sender Account='" + senderIban + '\'' +
                ", Receiver Account='" + receiverIban + '\'' +
                ", Comment='" + comment + '\'' +
                ", Date=" + date.toString().substring(0,10) +
                '}';
    }
}
