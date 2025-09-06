import java.util.*;

class ATM {
    float Balance;
    int PIN = 1019;
    ArrayList<String> miniStatement = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    int pinAttempts = 0;
    HashMap<String, Float> accounts = new HashMap<>();

    public ATM() {
        accounts.put("12345", 10000f);
        accounts.put("67890", 20000f);
    }

    public void checkpin() {
        System.out.println("Please Enter Your PIN: ");
        int enteredpin = sc.nextInt();
        if (enteredpin == PIN) {
            pinAttempts = 0;
            menu();
        } else {
            pinAttempts++;
            if (pinAttempts >= 3) {
                System.out.println("Too many invalid attempts! Exiting...");
            } else {
                System.out.println("Invalid PIN! Attempts left: " + (3 - pinAttempts));
                checkpin();
            }
        }
    }

    public void menu() {
        System.out.println("Enter Your Choice: \n");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Mini Statement");
        System.out.println("5. PIN Change");
        System.out.println("6. Transfer Funds");
        System.out.println("7. Exit");

        int option = sc.nextInt();

        switch (option) {
            case 1 -> CheckBalance();
            case 2 -> DepositMoney();
            case 3 -> WithdrawMoney();
            case 4 -> MiniStatement();
            case 5 -> PinChange();
            case 6 -> transferFunds();
            case 7 -> exitATM();
            default -> {
                System.out.println("Choose a Valid Option: ");
                menu();
            }
        }
    }

    public void CheckBalance() {
        System.out.println("Your account Balance is: " + Balance);
        menu();
    }

    public void DepositMoney() {
        System.out.println("Enter Deposit Amount: ");
        float amount = sc.nextFloat();
        Balance += amount;
        miniStatement.add("Deposited: " + amount);
        System.out.println("Money Deposited successfully\n");
        menu();
    }

    public void WithdrawMoney() {
        System.out.println("Enter Withdrawal Amount:");
        float amount = sc.nextFloat();
        if (amount > Balance) {
            System.out.println("Insufficient Balance! Please Check Balance and Retry.\n");
        } else {
            Balance -= amount;
            miniStatement.add("Withdrawn: " + amount);
            System.out.println("Withdrawal Successful!\n");
        }
        menu();
    }

    public void MiniStatement() {
        System.out.println("Mini Statement:");
        if (miniStatement.isEmpty()) {
            System.out.println("No transactions yet.\n");
        } else {
            for (String transaction : miniStatement) {
                System.out.println(transaction);
            }
            System.out.println("\nCurrent Balance: " + Balance + "\n");
        }
        menu();
    }

    public void PinChange() {
        System.out.println("Enter Current PIN: ");
        int currentPin = sc.nextInt();
        if (currentPin == PIN) {
            System.out.println("Enter New PIN: ");
            int newPin = sc.nextInt();
            System.out.println("Re-enter New PIN: ");
            int reenteredPin = sc.nextInt();

            if (newPin == reenteredPin) {
                PIN = newPin;
                System.out.println("PIN Changed Successfully!\n");
                checkpin();
            } else {
                System.out.println("PINs do not match! Try Again.\n");
                PinChange();
            }
        } else {
            System.out.println("Incorrect PIN! Try Again.\n");
            menu();
        }
    }

    public void transferFunds() {
        System.out.println("Enter the account number to transfer to:");
        String accountNumber = sc.next();
        if (!accounts.containsKey(accountNumber)) {
            System.out.println("Invalid account number! Please try again.");
            menu();
            return;
        }
        System.out.println("Enter amount to transfer:");
        float transferAmount = sc.nextFloat();
        if (transferAmount > Balance) {
            System.out.println("Insufficient Balance! Transfer failed.\n");
        } else {
            Balance -= transferAmount;
            float recipientBalance = accounts.get(accountNumber);
            accounts.put(accountNumber, recipientBalance + transferAmount);
            miniStatement.add("Transferred: " + transferAmount + " to Account: " + accountNumber);
            System.out.println("Funds Transferred Successfully!\n");
        }
        menu();
    }

    public void exitATM() {
        System.out.println("Thank you for using the ATM!");
    }
}

public class ATMmachine {
    public static void main(String[] args) {
        ATM obj = new ATM();
        obj.checkpin();
    }
}
