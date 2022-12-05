import data.UsersData;
import exception.InvalidAccountTypeException;
import exception.InvalidIbanException;
import exception.InvalidPincodeException;
import exception.PincodeLimitExceededException;
import service.BankTransactionsServiceImpl;
import service.MenuServiceImpl;
import service.UserServiceImpl;

import java.util.Arrays;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args){

        UserServiceImpl userService = new UserServiceImpl();
        BankTransactionsServiceImpl bankTransactionsService = new BankTransactionsServiceImpl();
        MenuServiceImpl menuService = new MenuServiceImpl();

        menuService.createUserData(userService);
        menuService.createBankTransactionData(bankTransactionsService);

        boolean isContinue = true;
        while(isContinue){
            System.out.println("Welcome to The Big Bank!");
            System.out.println("You can try application with this User credentials: Username: Ali Demir,Pincode: 3421, Iban:BE23454576890879");

            if (userService.isLoggedIn()){
                System.out.println("Dear " + userService.getLoggedInUser().getFullName() + "! Welcome to The Big Bank!\n");
                menuService.showUserMenu(userService, bankTransactionsService);
            }else{
                menuService.loginProcess(userService);
                if(userService.isLoggedIn()){
                    System.out.println("Dear " + userService.getLoggedInUser().getFullName() + "! Welcome to The Big Bank!\n");
                    menuService.showUserMenu(userService, bankTransactionsService);
                }
            }

            System.out.println("\nEnter X to exit Bank application! Enter any key to login!");
            Scanner exitScanner = new Scanner(System.in);
            if(exitScanner.nextLine().equalsIgnoreCase("x")){
                isContinue = false;
                System.out.println("\nThank you for using The Big Bank! Have a good day!");
            }

        }
    }

}
