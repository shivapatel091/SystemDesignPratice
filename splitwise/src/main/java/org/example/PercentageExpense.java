package org.example;

import java.math.BigDecimal;
import java.util.List;

public class PercentageExpense extends Expense {
    public PercentageExpense(User paidBy, BigDecimal amount, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(paidBy, amount, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        for (Split split : getSplits()) {
            if(!(split instanceof PercentageSplit)) {
                return false;
            }
        }
        BigDecimal percentageSum = BigDecimal.ZERO;
        for(Split split : getSplits()) {
            PercentageSplit percentageSplit = (PercentageSplit) split;
            percentageSum = percentageSum.add(percentageSplit.getPercentage());
        }
        return percentageSum.compareTo(BigDecimal.valueOf(100)) == 0;
    }
}
