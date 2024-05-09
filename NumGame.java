package org.codesoft.NumberGame;

import java.util.Random;
import java.util.Scanner;

public class NumGame {
    public static void main(String[] args) {
            Random rand = new Random();
            int numberToGuess = rand.nextInt(50) + 1;
            Scanner scanner = new Scanner(System.in);
            int guess;

            System.out.println("Guess a number between 1 and 50:");
            int counter = 0;
            while (true) {
                counter++;
                if (counter > 3) {
                    System.out.println("You've exceeded the maximum number of attempts.");
                    break;
                }

                guess = scanner.nextInt();

                if (guess == numberToGuess) {
                    System.out.println("Congratulations, you guessed the number!");
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println("Your guess is too low. Try again:");
                } else {
                    System.out.println("Your guess is too high. Try again:");
                }
            }
            scanner.close();
        }
    }
