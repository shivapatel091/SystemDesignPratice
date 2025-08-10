package org.example;

import java.math.BigDecimal;
import java.util.List;

public class EqualExpense extends Expense {


    public EqualExpense(User paidBy, BigDecimal amount, List<Split> splits, ExpenseMetadata expenseMetadata) {
       super(paidBy, amount, splits, expenseMetadata);
    }
    @Override
    public boolean validate() {
        for(Split  split : getSplits()) {
           if(!(split instanceof EqualSplit)) {
               return false;
           }
        }
        return true;
    }
}
