package service;

import data.UsersData;
import entity.AccountType;
import entity.BankTransaction;
import entity.User;
import exception.InvalidAccountTypeException;
import exception.InvalidIbanException;
import exception.InvalidPincodeException;
import exception.PincodeLimitExceededException;
import utility.ValidityCheck;

import java.time.Instant;
import java.util.Scanner;

public class MenuServiceImpl implements MenuService {

    @Override
    public void createBankTransactionData(BankTransactionsServiceImpl bankTransactionsService) {

        BankTransaction bankTransaction1 = new BankTransaction(500, "BE23454576890879",
                "BE45346789800762", Instant.now());
        BankTransaction bankTransaction2 = new BankTransaction(1500, "BE23454576890879",
                "BE11346789890763", Instant.now());
        BankTransaction bankTransaction3 = new BankTransaction(200, "BE23454576890879",
                "BE45346789890767", Instant.now());
        BankTransaction bankTransaction4 = new BankTransaction(1500, "BE11346789890763",
                "BE45342129890768", Instant.now());
        BankTransaction bankTransaction5 = new BankTransaction(800, "BE11346789890763",
                "BE45346789890767", Instant.now());
        BankTransaction bankTransaction6 = new BankTransaction(3500.53, "BE45342129890768",
                "BE45342129890768", Instant.now());
        BankTransaction bankTransaction7 = new BankTransaction(1500, "BE23454576890879",
                "BE11346789890763", Instant.now());
        BankTransaction bankTransaction8 = new BankTransaction(740.9, "BE23454576890879",
                "BE11346789890763", Instant.now());
        BankTransaction bankTransaction9 = new BankTransaction(500.5, "BE45346789800762",
                "BE45346789800762", Instant.now());
        BankTransaction bankTransaction10 = new BankTransaction(1500, "BE45346789890767",
                "BE11346789890763", Instant.now());
        BankTransaction bankTransaction11 = new BankTransaction(100, "BE45346789890767",
                "BE23454576890879", Instant.now());
        BankTransaction bankTransaction12 = new BankTransaction(500, "BE45346789800762",
                "BE23454576890879", Instant.now());
        BankTransaction bankTransaction13 = new BankTransaction(500, "BE45342129890768",
                "BE45342129890768", Instant.now());
        BankTransaction bankTransaction14 = new BankTransaction(400, "BE45342129890768",
                "BE45342129890768", Instant.now());
        BankTransaction bankTransaction15 = new BankTransaction(650, "BE45346789800762",
                "BE23454576890879", Instant.now());

        bankTransactionsService.makeBankTransaction(bankTransaction1);
        bankTransactionsService.makeBankTransaction(bankTransaction2);
        bankTransactionsService.makeBankTransaction(bankTransaction3);
        bankTransactionsService.makeBankTransaction(bankTransaction4);
        bankTransactionsService.makeBankTransaction(bankTransaction5);
        bankTransactionsService.makeBankTransaction(bankTransaction6);
        bankTransactionsService.makeBankTransaction(bankTransaction7);
        bankTransactionsService.makeBankTransaction(bankTransaction8);
        bankTransactionsService.makeBankTransaction(bankTransaction9);
        bankTransactionsService.makeBankTransaction(bankTransaction10);
        bankTransactionsService.makeBankTransaction(bankTransaction11);
        bankTransactionsService.makeBankTransaction(bankTransaction12);
        bankTransactionsService.makeBankTransaction(bankTransaction13);
        bankTransactionsService.makeBankTransaction(bankTransaction14);
        bankTransactionsService.makeBankTransaction(bankTransaction15);
    }

    @Override
    public void createUserData(UserServiceImpl userService){
        User user1 = new User("Ali Demir", 3421, "BE23454576890879", AccountType.IND, 5000);
        User user2 = new User("John Doe", 6657, "BE45346789890767", AccountType.COM, 12000);
        User user3 = new User("Kate Night", 2653, "BE11346789890763", AccountType.COR, 102000);
        User user4 = new User("Hans Steen", 6858, "BE45346789800762", AccountType.STU, 800);
        User user5 = new User("Jack Doelwit", 9067, "BE45342129890768", AccountType.SAV, 3000);
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);
        userService.add(user5);
    }

    @Override
    public void loginProcess(UserServiceImpl userService){
        System.out.println("Enter your credentials for Login");
        boolean isContinueLoginProcess = true;

        while(isContinueLoginProcess) {
            System.out.println("\nPlease enter your IBAN no to enter to your account:   -> Enter 1 to cancel login! ");

            boolean isIbanFormatWrong = true;
            boolean isIbanNotAvailable = true;
            String userIBAN = "";
            while (isIbanFormatWrong || isIbanNotAvailable) {
                Scanner scannerIBAN = new Scanner(System.in);
                userIBAN = scannerIBAN.nextLine();
                if (userIBAN.equals("1")){
                    break;
                }
                try {
                    String validIban = ValidityCheck.ibanValidityCheck(userIBAN);
                    isIbanFormatWrong = false;
                    if(userService.checkIban(validIban).equalsIgnoreCase("success")){
                        isIbanNotAvailable = false;
                    }else if(userService.checkIban(validIban).equalsIgnoreCase("blocked")){
                        System.out.println("\nYou entered a blocked IBAN no! Please try again!");
                    }
                    else{
                        System.out.println("\nYou entered unavailable IBAN no! Please try again!");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("\nPlease enter again your IBAN no:\n");
                }
            }
            if (userIBAN.equals("1")){
                break;
            }

            int i = 0;
            while (i<3) {
                System.out.println("\nPlease enter your Pincode! You can try 3 times! -> -> Enter 1 to cancel login!");
                Scanner scannerPincode = new Scanner(System.in);

                try {
                    int userPincode = scannerPincode.nextInt();
                    if (userPincode==1){
                        break;
                    }
                    ValidityCheck.pincodeValidityCheck(userPincode);
                    if (userService.login(userIBAN, userPincode)){
                        break;
                    }else{
                        System.out.println("Your credentials are not matching! Please try again!");
                    }
                } catch (Exception e) {
                    System.out.println("\nError message: " + e.getMessage());
                    System.out.println("\nPlease try again your pin code:\n");
                }

                i++;
                try{
                    if (i==3){
                        UsersData data = new UsersData();
                         User[] users = data.getUsers();
                        for (User user : users) {
                            if (userIBAN.equalsIgnoreCase(user.getIban())){
                                user.setPincodeBlocked(true);
                                break;
                            }
                        }
                        throw new PincodeLimitExceededException("You entered wrong Pincode 3 times! Sorry, your account is blocked!");
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    break;
                }
            }
            isContinueLoginProcess = false;
        }
    }

    @Override
    public void showUserMenu(UserServiceImpl userService, BankTransactionsServiceImpl bankTransactionsService) {

        boolean isInvalidUserChoice = true;
        int userChoice = 0;
        while(isInvalidUserChoice) {
           System.out.println("\nAccount Owner: " + userService.getLoggedInUser().getFullName());
           System.out.println("Account Type: " + userService.getLoggedInUser().getAccountType());
           System.out.println("Balance: " + userService.getLoggedInUser().getBalance());
           System.out.println("\nEnter 1 for Incoming Transactions");
           System.out.println("Enter 2 for Outgoing Transactions");
           System.out.println("Enter 3 to  Make Money Transfer");
           System.out.println("Enter 4 to  Sign out");

           try {
               Scanner scannerChoice = new Scanner(System.in);
               userChoice = scannerChoice.nextInt();
               if ((userChoice==1 || userChoice==2 || userChoice==3 || userChoice==4)){
                   isInvalidUserChoice = false;
               }else {
                   System.out.println("\nEnter a valid choice! Choose 1 or 2 or 3 or 4");
               }
           }catch (Exception e){
               System.out.println("\nError message: " + e.getMessage());
               System.out.println("\nEnter a valid choice!");
           }
       }

        switch (userChoice) {
            case 1 -> showIncomingTransactions(userService, bankTransactionsService);
            case 2 -> showOutgoingTransactions(userService, bankTransactionsService);
            case 3 -> makeMoneyTransfer(userService, bankTransactionsService);
            case 4 -> signOut(userService);
        }
    }

    @Override
    public void signOut(UserServiceImpl userService) {
        userService.logout();
    }

    @Override
    public void makeMoneyTransfer(UserServiceImpl userService, BankTransactionsServiceImpl bankTransactionsService) {
        System.out.println("Enter the Transfer name: ");
        Scanner scanner1 = new Scanner(System.in);
        String transferName = scanner1.nextLine();

        boolean isAmountInvalid = true;
        double amount = 0;
        while(isAmountInvalid) {
            System.out.println("Enter the amount of money: ");
            Scanner scanner2 = new Scanner(System.in);
            try {
                amount = scanner2.nextDouble();
                if(amount<=0){
                    System.out.println("\nPlease enter a valid amount to transfer!\n");
                }else if(amount>userService.getLoggedInUser().getBalance()){
                    System.out.println("\nYour balance is not sufficient! Please enter a valid amount to transfer!\n");
                }else{
                    isAmountInvalid = false;
                }
            }catch (Exception e){
                System.out.println("\nError message: "+e.getMessage());
                System.out.println("\nPlease enter a valid amount to transfer!\n");
            }
        }

        boolean isIbanInvalid = true;
        String receiverIban = "";
        while(isIbanInvalid) {
            System.out.println("\nEnter the IBAN no of a person/entity that you want to send your money: ");
            Scanner scanner3 = new Scanner(System.in);
            receiverIban = scanner3.nextLine();
            if (receiverIban.equalsIgnoreCase(userService.getLoggedInUser().getIban())){
                System.out.println("\nYou can't transfer money to yourself! Please enter another Iban no!\n");
                continue;
            }
            try {
                ValidityCheck.ibanValidityCheck(receiverIban);
                isIbanInvalid = false;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        boolean isCommentInvalid = true;
        String comment="";
        while(isCommentInvalid) {
            System.out.println("Enter if any additional comment/reference: ");
            Scanner scanner4 = new Scanner(System.in);
            try {
                comment = scanner4.nextLine();
                comment = ValidityCheck.commentValidityCheck(comment);
                isCommentInvalid = false;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        BankTransaction newBankTransaction = new BankTransaction(transferName,amount,userService.getLoggedInUser().getIban(),
                receiverIban,comment,Instant.now());

        if (bankTransactionsService.makeBankTransaction(newBankTransaction)!=null){
            balanceAdjustmentAfterTransfer(userService, receiverIban, amount);
            System.out.println("\nYou have just transferred "+amount+"€ to account "+receiverIban+"\n");
        }else{
            System.out.println("Transfer is not succeeded!");
        }
        showUserMenu(userService, bankTransactionsService);
    }

    @Override
    public void balanceAdjustmentAfterTransfer(UserServiceImpl userService, String receiverIban, double amount) {
        userService.getLoggedInUser().setBalance(userService.getLoggedInUser().getBalance() - amount);

        UsersData data = new UsersData();
        User[] bankUsers = data.getUsers();
        for (User user : bankUsers) {
            if (receiverIban.equalsIgnoreCase(user.getIban())){
                user.setBalance(user.getBalance()+amount);
                return;
            }
        }
    }

    @Override
    public void showOutgoingTransactions(UserServiceImpl userService, BankTransactionsServiceImpl bankTransactionsService) {
        System.out.println("\nYour Outgoing Money transfers: \n");
        bankTransactionsService.printOutgoingTransactions(userService.getLoggedInUser().getIban());
        showUserMenu(userService, bankTransactionsService);
    }

    @Override
    public void showIncomingTransactions(UserServiceImpl userService, BankTransactionsServiceImpl bankTransactionsService) {
        System.out.println("\nYour Incoming Money transfers: \n");

        bankTransactionsService.printIncomingTransactions(userService.getLoggedInUser().getIban());
        showUserMenu(userService, bankTransactionsService);
    }
}
