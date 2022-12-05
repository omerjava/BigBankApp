package entity;

import exception.InvalidAccountTypeException;
import exception.InvalidIbanException;
import exception.InvalidPincodeException;
import utility.ValidityCheck;

public class User {

    private String fullName;
    private int pincode;
    private boolean pincodeBlocked = false;
    private String iban;
    private AccountType accountType;
    private double balance;

    public User(String fullName, int pincode, String iban, AccountType accountType, double balance) {
        setFullName(fullName);
        setPincode(pincode);
        setIban(iban);
        setAccountType(accountType);
        setBalance(balance);
    }
    public User(String fullName, int pincode, boolean pincodeBlocked, String iban, AccountType accountType, double balance) {
        setFullName(fullName);
        setPincode(pincode);
        setIban(iban);
        setAccountType(accountType);
        setBalance(balance);
        setPincodeBlocked(pincodeBlocked);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode){
        this.pincode = ValidityCheck.pincodeValidityCheck(pincode);
    }
    public boolean isPincodeBlocked() {
        return pincodeBlocked;
    }

    public void setPincodeBlocked(boolean pincodeBlocked) {
        this.pincodeBlocked = pincodeBlocked;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban){
        this.iban = ValidityCheck.ibanValidityCheck(iban);
    }
    public String getAccountType() {
        return accountType.getType();
    }

    public void setAccountType(AccountType accountType) {
        if (!(accountType.getType().equalsIgnoreCase("commercial") || accountType.getType().equalsIgnoreCase("corporate")
        || accountType.getType().equalsIgnoreCase("individual") || accountType.getType().equalsIgnoreCase("student") ||
        accountType.getType().equalsIgnoreCase("savings"))){
            throw new InvalidAccountTypeException("You should enter a valid account type!");
        }

        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", pincode='" + pincode + '\'' +
                ", iban='" + iban + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                '}';
    }
}
