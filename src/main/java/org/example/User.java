package org.example;

import java.util.Map;

public class User {
    private String username;
    private String password;
    private Map<User,Long> amountOwed;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
