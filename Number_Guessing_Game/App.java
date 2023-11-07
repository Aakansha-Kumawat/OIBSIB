import java.util.Random;
import java.util.Scanner;
public class App {

  
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int lower = 1;
        int upper = 100;
        int numberToGuess = random.nextInt(upper - lower + 1) + lower;
        int maximum_attempts = 5;
        int attempts = 0;
        int score = 0;
        
        System.out.println("Welcome to Number Guessing Game!");
        System.out.println("I've selected a random number between " + lower + " and " + upper + ". Can you guess it?");

        while (attempts < maximum_attempts) {
            System.out.print("Enter your guess (Attempt " + (attempts + 1) + "/" + maximum_attempts + "): ");
            int userGuess = scanner.nextInt();
            
            if (userGuess == numberToGuess) {
                System.out.println("Congratulations! You guessed the number.");
                score += (maximum_attempts - attempts) * 10;
                break;
            } else if (userGuess < numberToGuess) {
                System.out.println("The number is higher. Try again.");
            } else {
                System.out.println("The number is lower. Try again.");
            }
            
            attempts++;
        }

        if (attempts == maximum_attempts) {
            System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was: " + numberToGuess);
        }
        
        System.out.println("Your score: " + score);
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
