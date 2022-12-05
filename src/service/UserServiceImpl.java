package service;

import data.UsersData;
import entity.User;

import java.util.Arrays;

public class UserServiceImpl implements UserService{
    private boolean loggedIn = false;
    private User loggedInUser = null;

    UsersData data = new UsersData();
    public UserServiceImpl() {}
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    public User getLoggedInUser() {
        return loggedInUser;
    }
    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    @Override
    public void add(User newUser) {
        data.setUsers(Arrays.copyOf(data.getUsers(), data.getUsers().length + 1));
        data.getUsers()[data.getUsers().length-1] = newUser;
    }

    @Override
    public String checkIban(String iban) {
        for (User user: data.getUsers()) {
            if(iban.equalsIgnoreCase(user.getIban())){
                if(user.isPincodeBlocked()){
                    return "blocked";
                }
                return "success";
            }
        }
        return "none";
    }

    @Override
    public boolean login(String iban, int pincode) {
        for (User user: data.getUsers()) {
            if(iban.equalsIgnoreCase(user.getIban()) && pincode==user.getPincode()){
                 loggedInUser = user;
                 loggedIn = true;
                 return true;
            }
        }
        return false;
    }

    @Override
    public void logout() {
        setLoggedIn(false);
        setLoggedInUser(null);
    }

    @Override
    public User findUserByIban(String iban) {
        for (User user: data.getUsers()) {
            if(iban.equalsIgnoreCase(user.getIban())){
                return user;
            }
        }
        return null;
    }
}
