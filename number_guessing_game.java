import java.util.Random;
import java.util.Scanner;

public class number_guessing_game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = 5; // Limit the number of attempts per round
        int totalRounds = 3; // Define the total number of rounds
        int totalScore = 0;

        System.out.println("Welcome to the Guess the Number game!");
        System.out.println("I have selected a random number between " + lowerBound + " and " + upperBound + ". Try to guess it.");

        for (int round = 1; round <= totalRounds; round++) {
            int numberToGuess = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;
            int roundScore = 0;

            System.out.println("Round " + round + ":");
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess < lowerBound || userGuess > upperBound) {
                    System.out.println("Please guess within the range " + lowerBound + " to " + upperBound + ".");
                } else if (userGuess < numberToGuess) {
                    System.out.println("The number is higher. Attempts left: " + (maxAttempts - attempts));
                } else if (userGuess > numberToGuess) {
                    System.out.println("The number is lower. Attempts left: " + (maxAttempts - attempts));
                } else {
                    roundScore = maxAttempts - attempts + 1;
                    totalScore += roundScore;
                    System.out.println("Congratulations! You guessed the number " + numberToGuess + " in " + attempts + " attempts.");
                    System.out.println("Round " + round + " Score: " + roundScore);
                    break;
                }
            }

            if (attempts >= maxAttempts) {
                System.out.println("You've run out of attempts. The number was " + numberToGuess + ". Round " + round + " Score: 0");
            }
        }

        System.out.println("Game over. Total Score: " + totalScore);

        scanner.close();
    }
}
