package org.example;

import java.math.BigDecimal;
import java.util.List;

public class ExactExpense extends Expense {
    public ExactExpense(User paidBy, BigDecimal amount, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(paidBy, amount, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        for(Split split : getSplits()) {
            if(!(split instanceof ExactSplit)) {
                return false;
            }
        }
        BigDecimal totalAmount = getAmount();
        BigDecimal sumOfExactAmount = BigDecimal.ZERO;
        for(Split split : getSplits()) {
            ExactSplit exactSplit = (ExactSplit) split;
            sumOfExactAmount =  sumOfExactAmount.add(exactSplit.getAmount());
        }

        return totalAmount.compareTo(sumOfExactAmount) == 0;
    }
}
