import java.util.*;

public class BudgetAndBill_Splitter {
    static Scanner sc = new Scanner(System.in);

    public static void AddExpense(
            String[] descriptions, int[] amounts, String[] paidBy,
            String[][] sharedBy, int[] balances, String[] names,
            int total_budget, int expenseCount) {

        sc.nextLine(); // clear buffer
        System.out.print("Enter Description (e.g., Dinner, Movie): ");
        descriptions[expenseCount] = sc.nextLine();

        System.out.print("Enter Amount: ");
        amounts[expenseCount] = sc.nextInt();

        sc.nextLine(); // clear buffer
        System.out.print("Who paid: ");
        paidBy[expenseCount] = sc.nextLine();

        System.out.print("How many people shared: ");
        int numShared = sc.nextInt();
        sc.nextLine();

        sharedBy[expenseCount] = new String[numShared];
        for (int i = 0; i < numShared; i++) {
            System.out.print("Enter name: ");
            sharedBy[expenseCount][i] = sc.nextLine();
        }

        // Split the amount among the shared people
        int share = amounts[expenseCount] / numShared;
        String payer = paidBy[expenseCount];

        for (int i = 0; i < numShared; i++) {
            String person = sharedBy[expenseCount][i];
            for (int j = 0; j < names.length; j++) {
                if (names[j].equals(person)) {
                    balances[j] -= share;  // each person owes their share
                }
                if (names[j].equals(payer)) {
                    balances[j] += amounts[expenseCount]; // payer gets credited
                }
            }
        }

        System.out.println("Expense added successfully!");
    }

    public static void ShowExpenses(
            String[] descriptions, int[] amounts, String[] paidBy,
            String[][] sharedBy, int expenseCount) {

        System.out.println("\n================= Expense List =================");
        if (expenseCount == 0) {
            System.out.println("No expenses added yet.");
            return;
        }

        for (int i = 0; i < expenseCount; i++) {  // loop only till expenseCount
            System.out.print((i + 1) + ". " + descriptions[i] + " - " + amounts[i] +
                    " paid by " + paidBy[i] + " shared with: ");
            for (int j = 0; j < sharedBy[i].length; j++) {
                System.out.print(sharedBy[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("================================================\n");
    }

    public static void ShowBalances(String[] names, int[] balances, int people) {
        System.out.println("\n================= Balances =================");
        for (int i = 0; i < people; i++) {
            System.out.println(names[i] + " balance: " + balances[i]);
        }
        System.out.println("============================================\n");
    }

    public static void ShowRemainingBudget(int total_budget, int[] amounts, int expenseCount) {
        int spent = 0;
        for (int i = 0; i < expenseCount; i++) {
            spent += amounts[i];
        }
        System.out.println("\n================= Remaining Budget =================");
        System.out.println("\nRemaining Budget: " + (total_budget - spent));
        System.out.println("====================================================\n");
    }

    public static void main(String[] args) {
        System.out.print("Enter Total Budget : ");
        int total_budget = sc.nextInt();

        System.out.print("Enter number of People : ");
        int people = sc.nextInt();
        sc.nextLine();

        String[] names = new String[people];
        int[] balances = new int[people];
        for (int i = 0; i < people; i++) {
            System.out.print("Enter Person " + (i + 1) + " : ");
            names[i] = sc.nextLine();
            balances[i] = 0;
        }

        String[] descriptions = new String[100];
        int[] amounts = new int[100];
        String[] paidBy = new String[100];
        String[][] sharedBy = new String[100][];
        int expenseCount = 0;

        while (true) {
            System.out.println("\n================= MENU =================");
            System.out.println("1. Add Expense");
            System.out.println("2. Show Expenses");
            System.out.println("3. Show Balances");
            System.out.println("4. Show Remaining Budget");
            System.out.println("5. Exit");
            System.out.println("========================================");
            System.out.print("Enter Choice : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    AddExpense(descriptions, amounts, paidBy, sharedBy,
                            balances, names, total_budget, expenseCount);
                    expenseCount++;
                    break;
                case 2:
                    ShowExpenses(descriptions, amounts, paidBy, sharedBy, expenseCount);
                    break;
                case 3:
                    ShowBalances(names, balances, people);
                    break;
                case 4:
                    ShowRemainingBudget(total_budget, amounts, expenseCount);
                    break;
                case 5:
                    System.out.println("\nThank you for using Budget and Bill Splitter!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
