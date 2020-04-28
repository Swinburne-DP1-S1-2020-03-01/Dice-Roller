import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
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
                           { { 1, 0, 1 }, { 1, 0, 1 }, { 1, 0, 1 } } };
 
    public static void main(String[] args) {
        int totalResultPlayer1 = 0;
        int numberOfDices = 0;
        Scanner scanner = new Scanner(System.in);
        DiceRollerInJava dice = new DiceRollerInJava();

        System.out.println("Please enter the number of dices you want to play: ");
        // Prevent the code from breaking if user input is not Number.
        while (true) {
            String input = scanner.nextLine();
            try {
                numberOfDices = Integer.parseInt(input);
                if (numberOfDices > 0) {
                    break;
                }
                else {
                    System.out.println("Please enter a positive integer!");    
                }
            } catch (NumberFormatException integerError) {
                System.out.println("This is not a number");
            }
        }

        while (true) {
            ArrayList<Integer> results = dice.roll(numberOfDices);

            totalResultPlayer1 += sumArray(results);
            System.out.print("dice face value: ");
            for (int i = 0; i < results.size(); ++i)
            {
                System.out.print(results.get(i) + " ");
            }    
            System.out.println("\nTotal: " + totalResultPlayer1);
            dice.draw(results);
 
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
 
    private void draw(ArrayList<Integer> values) {
        for (int i = 0; i < values.size(); ++i) {
            System.out.print("-----");    
        }
        System.out.println();
 
        for (int i = 0; i < 3; i++) {
            for (int valueIndex = 0; valueIndex < values.size(); ++valueIndex) {
                System.out.print("|"); 
                int value = values.get(valueIndex);
                int[][] displayVal = faceConfig[value - 1];
                for (int j = 0; j < 3; j++) {
                    if (displayVal[i][j] == 1) {
                        System.out.print("o");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.print("|");
            }
            System.out.println();
        }

        for (int i = 0; i < values.size(); ++i) {
            System.out.print("-----");    
        }
        System.out.println();
    }
 
    // Roll the dice in Java
    private ArrayList<Integer> roll(int numberOfDices) {
        ArrayList<Integer> values = new ArrayList<Integer>();
        Random r = new Random();
        for (int i = 0; i < numberOfDices; ++i) {
            values.add(r.nextInt(6) + 1);
        }
        return values;
    }

    private static int sumArray(ArrayList<Integer> array)
    {
        int sum = 0;
        for (int i = 0; i < array.size(); ++i) {
            sum += array.get(i);
        }
        return sum;
    }
}