import java.util.Random;
import java.util.Scanner;

// dice roller java source code
// Also outputs the dice face as ASCII art
public class DiceRollerInJava {

    // This has printing information for all numbers
    // For each number,3x3 matrix represents the face
    int[][][] faceConfig = { { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } }, { { 0, 0, 1 }, { 0, 0, 0 }, { 1, 0, 0 } },
            { { 0, 0, 1 }, { 0, 1, 0 }, { 1, 0, 0 } }, { { 1, 0, 1 }, { 0, 0, 0 }, { 1, 0, 1 } },
            { { 1, 0, 1 }, { 0, 1, 0 }, { 1, 0, 1 } }, { { 1, 0, 1 }, { 1, 0, 1 }, { 1, 0, 1 } } };

    public static void main(String[] args) {
        int totalResultPlayer1 = 0;
        int totalResultComputer = 0;
        Scanner scanner = new Scanner(System.in);
        DiceRollerInJava dice = new DiceRollerInJava();

        System.out.println("Select Mode: ");
        System.out.println("1. Single Player");
        System.out.println("2. Play against Computer");
        System.out.println("Insert input: ");

        String inputMode = scanner.nextLine();

        //Added error handling for input mode
        while(!inputMode.equalsIgnoreCase("1") && !inputMode.equalsIgnoreCase("2")){
            System.out.println("Unknown input! Try again!");
            System.out.println("Insert input: ");
            inputMode = scanner.nextLine();
        }

        while (true) {

            int result = dice.roll();
            totalResultPlayer1 += result;
            System.out.println("dice face value of player:" + result);
            System.out.println("total of player:" + totalResultPlayer1);
            dice.draw(result);

            //Code to display when user selects to play with the computer
            if (inputMode.equalsIgnoreCase("2")) {
                int resultComputer = dice.roll();
                totalResultComputer += resultComputer;
                System.out.println("dice face value of computer:" + resultComputer);
                System.out.println("total of computer:" + totalResultComputer);
                dice.draw(resultComputer);
            }

            System.out.println("Roll again? (type \"no\" to quit or type \"reset\" to reset the total):");
            String input = scanner.nextLine();

            while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("n")
                    && !input.equalsIgnoreCase("no") && !input.equalsIgnoreCase("r")
                    && !input.equalsIgnoreCase("reset")) {
                System.out.println("Unknown input! Try again!");
                System.out.println("Roll again? (type \"no\" to quit or type \"reset\" to reset the total):");

                input = scanner.nextLine();
            }

            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                continue;
            } else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                System.out.println("Bye!");
                scanner.close();
                return;
            } else if (input.equalsIgnoreCase("r") || input.equalsIgnoreCase("reset")) {
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
    private int roll() {
        Random r = new Random();
        return r.nextInt(6) + 1;
    }
}