package utility;

import exception.InvalidCommentException;
import exception.InvalidIbanException;
import exception.InvalidPincodeException;


public class ValidityCheck {

    public static int pincodeValidityCheck(int pincode){
        if (String.valueOf(pincode).length()!=4){
            throw new InvalidPincodeException("Your pincode should be a number with 4 digits!");
        }
        return pincode;
    }

    public static String ibanValidityCheck(String iban){
        if (iban.length()!=16){
            throw new InvalidIbanException("\nYour IBAN no should be 16 characters!" +
                    "\n For example, BE23342345657589");
        }
        if (!iban.startsWith("BE")){
            throw new InvalidIbanException("\nIBAN no must start with BE letters!" +
                    "\n For example, BE23342345657589");
        }
        String[] digits = iban.substring(2).split("");
        boolean check = true;
        for (String digit : digits) {
            boolean result = "0123456789".contains(digit);
            check = check && result;
        }
        if (!check){
            throw new InvalidIbanException("\nIBAN no must consists of digits except first 2 letters!" +
                    "\n For example, BE23342345657589\n");
        }
        return iban;
    }
    public static String commentValidityCheck(String comment){
        if (comment.trim().length()>140){
            throw new InvalidCommentException("\nYou exceeded character limit! Please enter a comment with max 140 characters!\n");
        }
        return comment;
    }
}
