/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author nguye
 */
import java.util.HashMap;
import java.util.Map;
import manager.User;

public class AccountService {
    private Map<String, User> users;
    
    public AccountService() {
        this.users = new HashMap<>();
        users.put("abe", new User("abe", "password"));
        users.put("barb", new User("barb", "password"));
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            user.setPassword(null);
            return user;
        }
        return null;
    }
}
