package org.example;

import java.util.Map;

public class Expense {
    User user;
    Long amount;
    Map<User, Split> splits;
    Split.SplitType type;

    public Expense(User user, Long amount, Split.SplitType type) {
        this.user = user;
        this.amount = amount;
    }

    
}
