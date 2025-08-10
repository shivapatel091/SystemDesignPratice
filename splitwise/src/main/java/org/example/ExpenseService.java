package org.example;

import java.math.BigDecimal;
import java.util.List;

public class ExpenseService {

    public static Expense createExpense(User paidBy, BigDecimal amount, List<Split> splits, ExpenseType expenseType, ExpenseMetadata expenseMetadata) {
        switch (expenseType) {
            case EXACT -> new ExactExpense(paidBy, amount, splits, expenseMetadata);
            case PERCENTAGE -> {
                for (Split split : splits) {
                    PercentageSplit percentageSplit = (PercentageSplit) split;
                    BigDecimal percentage = percentageSplit.getPercentage();
                    BigDecimal amountByPercent = amount.multiply(percentage).divide(BigDecimal.valueOf(100));
                    split.setAmount(amountByPercent);
                }
                return new PercentageExpense(paidBy, amount, splits, expenseMetadata);
            }
            case EQUAL -> {
                int totalSplits = splits.size();
                for (Split split : splits) {
                    BigDecimal amountByChunks = amount.divide(BigDecimal.valueOf(totalSplits), 2, BigDecimal.ROUND_HALF_UP);
                    split.setAmount(amountByChunks);
                }
                return new EqualExpense(paidBy, amount, splits, expenseMetadata);
            }
            default -> throw new IllegalArgumentException("Invalid expense type");

        }
        throw new IllegalStateException("Could not create expense");
    }

}
