package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        User user1 = new User(1L,"shiva", "123");
        User user2 = new User(2L,"rishabh", "123");
        User user3 = new User(3L,"tushar", "123");
        User user4 = new User(4L,"kunal", "123");
        ExpenseManager expenseManager = new ExpenseManager();
        expenseManager.addUser(user1);
        expenseManager.addUser(user2);
        expenseManager.addUser(user3);
        expenseManager.addUser(user4);
        BigDecimal amount = new BigDecimal("400");
        int n = 4;
        List<Split> splits = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            splits.add(new EqualSplit(expenseManager.userMap.get((long) i)));
        }
        List<Split> splits2 = new ArrayList<>();
        for(int i = 2; i <= n; i++){
            splits2.add(new PercentageSplit(expenseManager.userMap.get((long) i), BigDecimal.valueOf(25)));
        }
        expenseManager.addExpense(ExpenseType.EQUAL, amount, user1.getId(), splits2, null);
        // How much money does the user2 owe to user1
        expenseManager.showBalanceSheet();

    }
}