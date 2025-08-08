package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        User user1 = new User("shiva", "123");
        User user2 = new User("rishabh", "123");
        User user3 = new User("shiva", "123");
        User user4 = new User("shiva", "123");

        Expense expense = new Expense(user1, 100L, Split.SplitType.EQUAL);
        Expense expense2 = new Expense(user2, 200L, Split.SplitType.EQUAL);
        Expense expense3 = new Expense(user3, 300L, Split.SplitType.EQUAL);

        // How much money does the user2 owe to user1

    }
}