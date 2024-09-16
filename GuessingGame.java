import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int roundsWon = 0;
        int totalAttempts = 0;

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            boolean correctGuess = false;

            System.out.println("Welcome to the number guessing game!");
            System.out.println("I'm thinking of a number between 1 and 100.");
            System.out.println("You have 10 attempts to guess the number.");

            while (attempts < 10 && !correctGuess) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess < numberToGuess) {
                    System.out.println("Your guess is too low. Try again!");
                } else if (guess > numberToGuess) {
                    System.out.println("Your guess is too high. Try again!");
                } else {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    correctGuess = true;
                    roundsWon++;
                }
            }

            if (!correctGuess) {
                System.out.println("Sorry, you didn't guess the number. The number was " + numberToGuess);
            }

            totalAttempts += attempts;

            System.out.print("Do you want to play again? (yes/no): ");
            String input = scanner.next();
            playAgain = input.equalsIgnoreCase("yes");
        }

        System.out.println("Thanks for playing! You won " + roundsWon + " rounds.");
        System.out.println("Your average attempts per round were " + (double) totalAttempts / roundsWon);
    }
}