package org.example;

import java.math.BigDecimal;

public class Split {

    public enum SplitType {
        EQUAL,
        PERCENT,
        EXACT
    }

    SplitType splitType;
    BigDecimal percentage;

}
