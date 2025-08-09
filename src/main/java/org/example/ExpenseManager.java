package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {

    List<Expense> expenses = new ArrayList<>();
    public Map<Long , User> userMap;
    Map<Long, Map<Long, BigDecimal>> balanceSheet = new HashMap<>();



    public void addUser(User user) {
        if(userMap == null) {
            userMap = new HashMap<>();
        }
        userMap.put(user.getId(), user);
        balanceSheet.put(user.getId(), new HashMap<Long, BigDecimal>());
    }


    public void addExpense(ExpenseType expenseType, BigDecimal amount, Long paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        User paidByUser = userMap.get(paidBy);
        Expense expense = ExpenseService.createExpense(paidByUser, amount, splits, expenseType, expenseMetadata);
        expenses.add(expense);
        for (Split split : expense.getSplits()) {
            Long paidTo = split.getUser().getId();
            Map<Long, BigDecimal> balances = balanceSheet.get(paidBy);
            if(!balances.containsKey(paidTo)) {
                balances.put(paidTo, new BigDecimal(0));
            }
            balances.put(paidTo, balances.get(paidTo).add(split.getAmount()));
            Map<Long, BigDecimal> deficitBalance = balanceSheet.get(paidTo);
            if(!deficitBalance.containsKey(paidBy)) {
                deficitBalance.put(paidBy, new BigDecimal(0));
            }
            deficitBalance.put(paidBy, deficitBalance.get(paidBy).subtract(split.getAmount()));
        }
    }

    private void print(Long userId, Map.Entry<Long , BigDecimal> balanceSheet) {
        User actualUser  = userMap.get(userId);
        User affectedUser = userMap.get(balanceSheet.getKey());
        BigDecimal balance = balanceSheet.getValue();
        if(balance.compareTo(BigDecimal.ZERO) <= 0){
            System.out.println(actualUser.getUsername() + " owes " + balance.abs() + " to " + affectedUser.getUsername());
        } else {
            System.out.println(affectedUser.getUsername() + " owes " + balance.abs() + " to " + actualUser.getUsername());

        }
    }

    public void showBalance(Long userId) {
        Map<Long, BigDecimal> amountSheet = balanceSheet.get(userId);
        for (Map.Entry<Long, BigDecimal> entry : amountSheet.entrySet()) {
            print(userId, entry);
        }
    }


    public void showBalanceSheet() {
        boolean isEmpty = true;
        for (Map.Entry<Long, Map<Long, BigDecimal>> entry : balanceSheet.entrySet()) {
            for (Map.Entry<Long, BigDecimal> balanceSheet : entry.getValue().entrySet()) {
                if (balanceSheet.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                    isEmpty = false;
                    print(entry.getKey(), balanceSheet);
                }
            }
        }
        if (isEmpty) {
            System.out.println("No balances found");
        }
    }


}
