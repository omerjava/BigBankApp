package service;

import exception.InvalidAccountTypeException;
import exception.InvalidIbanException;
import exception.InvalidPincodeException;
import exception.PincodeLimitExceededException;

public interface MenuService {

    void createBankTransactionData(BankTransactionsServiceImpl bankTransactionsService);

    void createUserData(UserServiceImpl userService);

    void loginProcess(UserServiceImpl userService);

    void showUserMenu(UserServiceImpl userService, BankTransactionsServiceImpl bankTransactionsService);

    void signOut(UserServiceImpl userService);

    void makeMoneyTransfer(UserServiceImpl userService, BankTransactionsServiceImpl bankTransactionsService);

    void balanceAdjustmentAfterTransfer(UserServiceImpl userService, String receiverIban, double amount);

    void showOutgoingTransactions(UserServiceImpl userService, BankTransactionsServiceImpl bankTransactionsService);

    void showIncomingTransactions(UserServiceImpl userService, BankTransactionsServiceImpl bankTransactionsService);
}
