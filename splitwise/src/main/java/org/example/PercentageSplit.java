package org.example;

import java.math.BigDecimal;

public class PercentageSplit extends Split {
    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    private BigDecimal percentage;
    public PercentageSplit(User user, BigDecimal percentage) {
        super(user);
        this.percentage = percentage;
    }
}
