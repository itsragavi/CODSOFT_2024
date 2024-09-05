import java.util.*;

public class Main {
    static boolean equal_or_not(int random_number, int user_guess, String user_name, int attemptsLeft) {
        if (random_number == user_guess) {
            System.out.println("\nWoah! " + user_name + " You guessed it right!");
            return true;
        } else if (random_number < user_guess) {
            int value = random_number + 10;
            if (attemptsLeft == 1) {
                System.out.println("Oops :( It's wrong! This is your last attempt.");
            } else if (value > user_guess) {
                System.out.println("Oops :( It's wrong! but you are very close. Try a slightly lower number.");
            } else {
                System.out.println("Oops :( It's wrong! You're too high. Try a much lower number.");
            }
        } else {
            int value1 = random_number - 10;
            if (attemptsLeft == 1) {
                System.out.println("Oops :( It's wrong! This is your last attempt.");
            } else if (user_guess > value1) {
                System.out.println("Oops :( It's wrong! but you are very close. Try a slightly higher number.");
            } else {
                System.out.println("Oops :( It's wrong! You're too low. Try a much higher number.");
            }
        }
        System.out.println("Attempts left: " + (attemptsLeft - 1));
        if (attemptsLeft - 1 > 0) {
            System.out.println("Alright, give it another chance, " + user_name);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("******************** NUMBER GAME ********************");
        Random random = new Random();
        Scanner scan = new Scanner(System.in);
        int total_rounds = 3;
        int total_attempts = 3;

        System.out.println("\nHow would you like us to address you?");
        String user_name = scan.nextLine();

        int score = 0;

        for (int round = 1; round <= total_rounds; round++) {
            int random_number = 1 + random.nextInt(100);
            System.out.println("\nRound " + round + ": The random number has been generated.");
            boolean guessedCorrectly = false;

            for (int attempt = 1; attempt <= total_attempts; attempt++) {
                System.out.println("\nGuess the number: ");
                int user_guess = scan.nextInt();
                guessedCorrectly = equal_or_not(random_number, user_guess, user_name, total_attempts - attempt + 1);
                if (guessedCorrectly) {
                    score += (total_attempts - attempt + 1);
                    break;
                }
            }

            if (!guessedCorrectly) {
                System.out.println("\nSorry, you've used all your attempts. The number was: " + random_number);
            }
        }

        System.out.println("\nThank you for playing the number game, " + user_name + "!");
        System.out.println("Your score: " + score);
    }
}
