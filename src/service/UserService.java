package service;

import entity.User;

public interface UserService {

    void add(User user);
    String checkIban(String iban);
    boolean login(String iban, int pincode);
    void logout();
    User findUserByIban(String iban);


}
