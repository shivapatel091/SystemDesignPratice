package org.example;

import java.math.BigDecimal;

public class ExactSplit extends Split {


    public ExactSplit(User user, BigDecimal amount) {
        super(user);
        this.amount = amount;
    }
}
