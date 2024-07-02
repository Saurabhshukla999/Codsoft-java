import java.util.Random;
import java.util.Scanner;

public class Numberguess {
    public static void main(String[] args) {
        int maxAttempts = 10;
        int attempts = 0;
        int userInput;
        boolean hasGuessedCorrectly = false;
        Random random = new Random();
        int randomnumber = random.nextInt(100) + 1;
        try (Scanner scanner = new Scanner(System.in)) {
            while (attempts < maxAttempts) {
                System.out.println("enter your predicted number");
                userInput = scanner.nextInt();
                attempts++;
                
                if (userInput == randomnumber) {
                    System.out.println("you guesed it right  ");
                    hasGuessedCorrectly = true;
                    break;
                }
                
                else if (userInput < randomnumber - 20) {
                    System.out.println("too-low");
                }
                
                else if (userInput < randomnumber - 10 && userInput >= randomnumber - 20) {
                    System.out.println("low");
                }
                
                else if (userInput < randomnumber && userInput >= randomnumber - 10) {
                    System.out.println("close ,but low ");
                } else if (userInput > randomnumber + 20) {
                    System.out.println("too-high");
                }
                
                else if (userInput > randomnumber + 10 && userInput <= randomnumber + 20) {
                    System.out.println("high");
                } else if (userInput > randomnumber && userInput <= randomnumber + 10) {
                    System.out.println("close ,but high ");
                }
            }
            if (hasGuessedCorrectly) {
                System.out.println(" congratulation you has guessed right");
            } else {
                System.out.println(" sorry you has used all your attempts, the number was" + randomnumber);
            }
        }
    }
}
