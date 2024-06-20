package Game;

import java.util.Scanner;
import java.util.Random;

public class GuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalAttempts = 0;
        int roundsWon = 0;

        System.out.println("\nWelcome to the Number Guessing Game!\n");

        while (playAgain) {
            int generatedNumber = random.nextInt(100) + 1;
            int attempts = 0;

            displayInstructions();

            System.out.println("Round " + (roundsWon + 1) + ":");

            // Loop for up to 5 attempts given to the user
            boolean guessedCorrectly = false;
            while (attempts < 5) {
                int guess = getValidGuess(sc, attempts);

                attempts++;

                if (guess == generatedNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    totalAttempts += attempts;
                    roundsWon++;
                    guessedCorrectly = true;
                    break;
                } else if (guess < generatedNumber) {
                    System.out.println("Too low, the number is greater than " + guess + ". You have " + (5 - attempts) + " attempts left.");
                } else {
                    System.out.println("Too high, the number is less than " + guess + ". You have " + (5 - attempts) + " attempts left.");
                }
            }

            if (!guessedCorrectly) {
                // Checking if the last guess was incorrect
                System.out.println("Sorry, you didn't guess the correct number.\nThe correct number was: " + generatedNumber);
            }

            displayScore(roundsWon);

            playAgain = askToPlayAgain(sc);
        }

        System.out.println("\nThank you for playing!");
        System.out.println("Total Attempts: " + totalAttempts);
        System.out.println("Total Rounds Won: " + roundsWon);
        sc.close();
    }

    // Method to display game instructions
    private static void displayInstructions() {
        System.out.println("Instructions:");
        System.out.println("- You have to guess the number generated between 1 to 100.");
        System.out.println("- You have 5 attempts to guess the correct number.");
        System.out.println("- After each guess, you will be informed if your guess is too high or too low.\n");
    }

    // Method to get a valid guess from the user
    private static int getValidGuess(Scanner sc, int attemptNumber) {
        int guess;
        while (true) {
            System.out.print("Guess #" + (attemptNumber + 1) + ": ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.next(); // Clear the invalid input
            } else {
                guess = sc.nextInt();
                if (guess < 1 || guess > 100) {
                    System.out.println("Invalid input. Guess should be between 1 and 100.");
                } else {
                    break;
                }
            }
        }
        return guess;
    }

    // Method to display current score
    private static void displayScore(int roundsWon) {
        System.out.println("\nScore: " + roundsWon + " round(s) won.\n");
    }

    // Method to ask if the user wants to play again
    private static boolean askToPlayAgain(Scanner sc) {
        while (true) {
            System.out.print("Do you want to play again? (yes/no): ");
            String playChoice = sc.next();
            if (playChoice.equalsIgnoreCase("yes")) {
                System.out.println(); // Adding a blank line for better readability
                return true;
            } else if (playChoice.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }
}
