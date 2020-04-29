import java.util.Random;
import java.util.Scanner;
 
// dice roller java source code
// Also outputs the dice face as ASCII art
public class DiceRollerInJava {
 
    // This has printing information for all numbers
    // For each number,3x3 matrix represents the face
    int[][][] faceConfig = { { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } }, 
                           { { 0, 0, 1 }, { 0, 0, 0 }, { 1, 0, 0 } },
                           { { 0, 0, 1 }, { 0, 1, 0 }, { 1, 0, 0 } }, 
                           { { 1, 0, 1 }, { 0, 0, 0 }, { 1, 0, 1 } },
                           { { 1, 0, 1 }, { 0, 1, 0 }, { 1, 0, 1 } }, 
                           { { 1, 0, 1 }, { 1, 0, 1 }, { 1, 0, 1 } },
                           { { 1, 0, 1 }, { 1, 1, 1 }, { 1, 0, 1 } },
                           { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } } };
 
    public static void main(String[] args) {
        int totalResultPlayer1 = 0;
        int result;
        Scanner scanner = new Scanner(System.in);
        DiceRollerInJava dice = new DiceRollerInJava();
        while (true) {
            int dieTemp = 6; // temp value for die 
            
            // procedure for rolling eight sided die
            // prompts user if they wish to roll a eight sided die
            // if yes, dieTemp will be set to 8 and will return a value between 1-8 when called roll(dieTemp)
            // else will return a value between 1-6 when called roll(dieTemp)
            System.out.println("would you like to roll a 8 sided die? (type y/n):");
            String eightInput = scanner.nextLine();
            if (eightInput.equalsIgnoreCase("y") || eightInput.equalsIgnoreCase("yes")){
                dieTemp = 8;
                result = dice.roll(dieTemp);
                System.out.println("Rolling eight sided die!");
            }
            else{
                result = dice.roll(dieTemp);
                System.out.println("Rolling six sided die!");                         
            }
            totalResultPlayer1 += result;
            System.out.println("dice face value:" + result);
            System.out.println("total:" + totalResultPlayer1);
            dice.draw(result);
            
            System.out.println("Roll again? (type \"no\" to quit or type \"reset\" to reset the total):");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) 
            {
                System.out.println("Bye!");
                scanner.close();
                return;
            }
            if (input.equalsIgnoreCase("r") || input.equalsIgnoreCase("reset"))
            {
                System.out.println("Reset total.");
                totalResultPlayer1 = 0;
                System.out.println("total:" + totalResultPlayer1);
                System.out.println("Rolling again...");
            } 
        }
    }
 
    // Draw the dice face using ascii characters
    private void draw(int value) {
        int[][] displayVal = faceConfig[value - 1];
        System.out.println("-----");
 
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                if (displayVal[i][j] == 1) {
                    System.out.print("o");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("|");
        }
        System.out.println("-----");
 
    }
 
    // Roll the dice in Java
    private int roll(int value) {
        Random r = new Random();
        return r.nextInt(value) + 1;
    }
}