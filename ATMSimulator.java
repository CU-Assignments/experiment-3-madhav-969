import java.util.Scanner;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class InvalidPINException extends Exception {
    public InvalidPINException(String message) {
        super(message);
    }
}

public class ATMSimulator {
    private static final int CORRECT_PIN = 1234;
    private static double balance = 3000.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter PIN: ");
            int enteredPin = scanner.nextInt();

            if (enteredPin != CORRECT_PIN) {
                throw new InvalidPINException("Invalid PIN entered.");
            }

            System.out.print("Withdraw Amount: ");
            double withdrawAmount = scanner.nextDouble();

            if (withdrawAmount > balance) {
                throw new InsufficientBalanceException("Insufficient balance.");
            }

            balance -= withdrawAmount;
            System.out.printf("Withdrawal successful. New balance: %.2f%n", balance);

        } catch (InvalidPINException | InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred.");
        } finally {
            System.out.printf("Current Balance: %.2f%n", balance);
            scanner.close();
        }
    }
}
