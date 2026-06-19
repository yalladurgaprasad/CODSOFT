import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        final int LOWER_BOUND = 1;
        final int UPPER_BOUND = 100;
        final int MAX_ATTEMPTS = 7;

        int roundsWon = 0;
        int totalRounds = 0;
        boolean playAgain = true;

        System.out.println("=================================");
        System.out.println("    WELCOME TO THE NUMBER GAME");
        System.out.println("=================================");

        // Step 6: Allow multiple rounds
        while (playAgain) {
            totalRounds++;

            // Step 1: Generate a random number within the specified range
            int targetNumber = LOWER_BOUND + random.nextInt(UPPER_BOUND - LOWER_BOUND + 1);
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\n--- Round " + totalRounds + " ---");
            System.out.println("I'm thinking of a number between " + LOWER_BOUND + " and " + UPPER_BOUND + ".");
            System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it."); // Step 5: limit attempts

            // Steps 2-4: Prompt, compare, repeat until correct or out of attempts
            while (attempts < MAX_ATTEMPTS && !guessedCorrectly) {
                System.out.print("\nAttempt " + (attempts + 1) + "/" + MAX_ATTEMPTS + " - Enter your guess: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("That's not a valid number. Please enter a whole number.");
                    scanner.next(); // discard the invalid token
                    continue;
                }

                int guess = scanner.nextInt();
                attempts++;

                if (guess < LOWER_BOUND || guess > UPPER_BOUND) {
                    System.out.println("Please guess a number within the range " + LOWER_BOUND + "-" + UPPER_BOUND + ".");
                } else if (guess == targetNumber) {
                    System.out.println("Correct! You guessed it in " + attempts + " attempt(s).");
                    guessedCorrectly = true;
                    roundsWon++;
                } else if (guess < targetNumber) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }

                if (!guessedCorrectly && attempts == MAX_ATTEMPTS) {
                    System.out.println("\nOut of attempts! The number was " + targetNumber + ".");
                }
            }

            // Step 7: Display the user's score
            System.out.println("\nScore so far: " + roundsWon + " round(s) won out of " + totalRounds + " played.");

            System.out.print("\nDo you want to play again? (yes/no): ");
            String response = scanner.next().trim().toLowerCase();
            playAgain = response.equals("yes") || response.equals("y");
        }

        System.out.println("\n=================================");
        System.out.println("           FINAL SCORE");
        System.out.println("=================================");
        System.out.println("Rounds played: " + totalRounds);
        System.out.println("Rounds won:    " + roundsWon);
        System.out.printf("Win rate:      %.1f%%%n", (roundsWon * 100.0) / totalRounds);
        System.out.println("\nThanks for playing!");

        scanner.close();
    }
}
