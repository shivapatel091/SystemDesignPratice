package org.example;

import java.math.BigDecimal;

public class Split {

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    protected BigDecimal amount;

    public Split(User user) {
        this.user = user;
    }
}
