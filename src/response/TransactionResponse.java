package response;

import java.time.Instant;

public class TransactionResponse {

    private String transactionName;
    private double amount;
    private String receiverIban;
    private String comment;
    private Instant date;

    public TransactionResponse(String transactionName, double amount, String receiverIban, String comment, Instant date) {
        this.transactionName = transactionName;
        this.amount = amount;
        this.receiverIban = receiverIban;
        this.comment = comment;
        this.date = date;
    }

    public TransactionResponse() {}

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReceiverIban() {
        return receiverIban;
    }

    public void setReceiverIban(String receiverIban) {
        this.receiverIban = receiverIban;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}
