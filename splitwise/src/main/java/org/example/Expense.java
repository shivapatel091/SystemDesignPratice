package org.example;

import java.math.BigDecimal;
import java.util.List;

public abstract class Expense {

    Long id;

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public ExpenseMetadata getExpenseMetadata() {
        return expenseMetadata;
    }

    public void setExpenseMetadata(ExpenseMetadata expenseMetadata) {
        this.expenseMetadata = expenseMetadata;
    }

    User paidBy;
    BigDecimal amount;
    List<Split> splits;
    ExpenseMetadata expenseMetadata;

    public Expense(User paidBy, BigDecimal amount,  List<Split> splits, ExpenseMetadata expenseMetadata) {
        this.paidBy = paidBy;
        this.amount = amount;
        this.splits = splits;
        this.expenseMetadata = expenseMetadata;
    }

    public abstract boolean validate();
    
}
