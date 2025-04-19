package org.example.handler;

import java.util.Random;
import java.util.Scanner;

public class Game {

    private Scanner scanner;
    private int level;
    private int generatedNumber;
    private int attempts;
    private int attemptsLeft;

    public Game(){
        scanner = new Scanner(System.in);
    }

    public void handleGame(){
        String continueChoice;
        long timeStart, timeEnd, timeTotal;

        printHello();

        do {
            printLevels();
            do {
                System.out.print("Enter your choice: ");
                level = getUserInputNumber();
            } while (level < 1 || level > 3);

            attempts = 10 / level;
            attemptsLeft = attempts;
            generateNumber();

            System.out.println("Game started!");

            timeStart = System.currentTimeMillis();
            while (attemptsLeft > 0) {
                System.out.print("Your guess: ");
                int guess = getUserInputNumber();

                attemptsLeft--;

                if (guess == generatedNumber) {
                    timeEnd = System.currentTimeMillis();
                    timeTotal = (timeEnd - timeStart) / 1000;
                    System.out.println("Congratulations! You guessed it in " + (attempts - attemptsLeft) + " attempts in "
                            + timeTotal + " seconds.");
                    break;
                }

                if (attemptsLeft == 0) {
                    System.out.println("You lose! Try again!");
                    break;
                }

                System.out.println("Your guess is incorrect! Try again.");
                System.out.println(attemptsLeft + " attempts left.");
                if (guess < generatedNumber)
                    System.out.println("Try to guess number greater than that ;)");
                else
                    System.out.println("Try to guess number less than that ;)");
            }

            do{
                System.out.print("It's end of the game. Do you want to play again?[yes/no]: ");
                continueChoice = scanner.next();
            }while(!(continueChoice.equals("yes") || continueChoice.equals("no")));

        }while(continueChoice.equals("yes"));
    }

    private void printHello(){
        System.out.println("""
                Welcome to the Number Guessing Game!
                I'm thinking of a number between 1 and 100.
                You need to enter a number between 1 and 100.""");
    }

    private void printLevels(){
        System.out.println("""
                Select difficulty level:\s
                1. Easy (10 chances)
                2. Medium (5 chances)
                3. Hard (3 chances and limited time for answer).""");
    }

    private void generateNumber(){
        Random rand = new Random();
        generatedNumber = rand.nextInt(1, 101);
    }

    private int getUserInputNumber(){
        while(true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Pass correct number!");
            }
        }
    }

}
