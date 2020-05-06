import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

import java.util.*;

// dice roller java source code
// Also outputs the dice face as ASCII art
public class DiceRollerInJava {

    

    ArrayList highscores = new ArrayList<Integer>();

    public static void main(String[] args) {
        int totalResultPlayer1 = 0;
        int totalResultComputer = 0;
        int numberOfDices = 0;

        Scanner scanner = new Scanner(System.in);
        DiceRollerInJava dice = new DiceRollerInJava();

        System.out.println("Select Mode: ");
        System.out.println("1. Single Player");
        System.out.println("2. Play against Computer");
        System.out.println("Insert input: ");

        String inputMode = scanner.nextLine();

        // Added error handling for input mode
        while (!inputMode.equalsIgnoreCase("1") && !inputMode.equalsIgnoreCase("2")) {
            System.out.println("Unknown input! Try again!");
            System.out.println("Insert input: ");
            inputMode = scanner.nextLine();
        }

        while (true) {
            // Display game option
            System.out.println("Please choose one option to proceed.");
            System.out.println("1. Play");
            System.out.println("2. Scoreboard");
            System.out.println("3. Exit");

            int gameOption = readPositiveIngeter(scanner);
            if (gameOption == 1) {
                totalResultPlayer1 = 0;
                System.out.println("Please enter the number of dices you want to play: ");
                numberOfDices = readPositiveIngeter(scanner);

                // Enter the game
                while (true) {
                    // procedure for rolling eight sided die
                    // prompts user if they wish to roll a eight sided die
                    // if yes, dieTemp will be set to 8 and will return a value between 1-8 when called roll(dieTemp)
                    // else will return a value between 1-6 when called roll(dieTemp)
                    int[][][] faceConfig;
                    int dieTemp = 6;
                    System.out.println("would you like to roll a 8 sided die? (type y/n):");
                    
                    String eightInput = scanner.nextLine();
                    if (eightInput.equalsIgnoreCase("y") || eightInput.equalsIgnoreCase("yes")) {
                        dieTemp = 8;
                        // This has printing information for all numbers
                        faceConfig = new int[][][] { { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } },
                                { { 0, 0, 1 }, { 0, 0, 0 }, { 1, 0, 0 } }, { { 0, 0, 1 }, { 0, 1, 0 }, { 1, 0, 0 } },
                                { { 1, 0, 1 }, { 0, 0, 0 }, { 1, 0, 1 } }, { { 1, 0, 1 }, { 0, 1, 0 }, { 1, 0, 1 } },
                                { { 1, 0, 1 }, { 1, 0, 1 }, { 1, 0, 1 } }, { { 1, 0, 1 }, { 1, 1, 1 }, { 1, 0, 1 } },
                                { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } } };
                        System.out.println("Rolling eight sided die!");
                    } else {
                        dieTemp = 6;
                        // This has printing information for all numbers
                        faceConfig = new int[][][] { { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } }, { { 0, 0, 1 }, { 0, 0, 0 }, { 1, 0, 0 } },
            { { 0, 0, 1 }, { 0, 1, 0 }, { 1, 0, 0 } }, { { 1, 0, 1 }, { 0, 0, 0 }, { 1, 0, 1 } },
            { { 1, 0, 1 }, { 0, 1, 0 }, { 1, 0, 1 } }, { { 1, 0, 1 }, { 1, 0, 1 }, { 1, 0, 1 } } };
                        System.out.println("Rolling six sided die!");
                    }

                    ArrayList<Integer> results = dice.roll(numberOfDices, dieTemp);

                    totalResultPlayer1 += sumArray(results);
                    System.out.print("dice face value: ");
                    for (int i = 0; i < results.size(); ++i) {
                        System.out.print(results.get(i) + " ");
                    }
                    System.out.println("\nTotal: " + totalResultPlayer1);
                    dice.draw(results, faceConfig);

                    if (inputMode.equalsIgnoreCase("2")) {
                        ArrayList<Integer> resultComputer = dice.roll(numberOfDices, dieTemp);
                        totalResultComputer += sumArray(resultComputer);
                        System.out.print("dice face value: ");
                        for (int i = 0; i < resultComputer.size(); ++i) {
                            System.out.print(resultComputer.get(i) + " ");
                        }
                        System.out.println("\nTotal: " + totalResultComputer);
                        dice.draw(resultComputer, faceConfig);
                    }

                    System.out.println("Roll again? (type \"no\" to quit or type \"reset\" to reset the total):");
                    String input = scanner.nextLine();

                    while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("yes")
                            && !input.equalsIgnoreCase("n") && !input.equalsIgnoreCase("no")
                            && !input.equalsIgnoreCase("r") && !input.equalsIgnoreCase("reset")) {
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

            if (gameOption == 2) {
                // Sort and display highscores
                Collections.sort(dice.highscores, Collections.reverseOrder());
                dice.DisplayHighScores(10);
            }

            if (gameOption == 3) {
                // end game
                scanner.close();
                return;

            }

        }
    }

    // Display highscore to the terminal
    private void DisplayHighScores(int numberEntries) {
        System.out.println("***** Score Board *****");
        for (int i = 0; i < Math.min(numberEntries, highscores.size()); ++i) {
            System.out.println((i + 1) + ". " + highscores.get(i));
        }
    }

    // Display the value on dices to the terminal
    private void draw(ArrayList<Integer> values, int[][][] faceConfig) {
        int[][][] faceConfiguration = faceConfig;
        for (int i = 0; i < values.size(); ++i) {
            System.out.print("-----");
        }
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int valueIndex = 0; valueIndex < values.size(); ++valueIndex) {
                System.out.print("|");
                int value = values.get(valueIndex);
                int[][] displayVal = faceConfiguration[value - 1];
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

    // Roll n dices to the terminal

    // Roll the dice in Java

    private ArrayList<Integer> roll(int numberOfDices, int dieTemp) {
        ArrayList<Integer> values = new ArrayList<Integer>();
        Random r = new Random();
        for (int i = 0; i < numberOfDices; ++i) {
            values.add(r.nextInt(dieTemp) + 1);
        }
        return values;
    }

    // Helper functions to add all integers in a arraylist
    private static int sumArray(ArrayList<Integer> array) {
        int sum = 0;
        for (int i = 0; i < array.size(); ++i) {
            sum += array.get(i);
        }
        return sum;
    }

    // Helper functions to read an positive integer from the terminal
    private static int readPositiveIngeter(Scanner scanner) {
        while (true) {
            // Prevent the code from breaking if user input is not Number.
            String input = scanner.nextLine();
            try {
                int num = Integer.parseInt(input);
                if (num > 0) {
                    return num;
                } else {
                    System.out.println("Please enter a positive integer!");
                }
            } catch (NumberFormatException integerError) {
                System.out.println("This is not a number");
            }
        }
    }
}