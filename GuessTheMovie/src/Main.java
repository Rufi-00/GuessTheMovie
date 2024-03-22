package com.company;

import java.io.FileNotFoundException;
import java.lang.NullPointerException;
import java.util.Scanner;


public class Main {
   
    public static void main(String[] args) {
        try {
     
            Game game = new Game();
        
            String randomMovie = game.generateRandomMovie(game.getMovieList("movies.txt"));
       
            game.setRandomMovie(randomMovie);
           
            String encryptedMovie = game.encryptMovie(randomMovie);
           
            game.setEncryptedMovie(encryptedMovie);
           
            Scanner input = new Scanner(System.in);
            char guess;
           
            int wrongAttempts = 0;
            
            game.setWrongAttempts(wrongAttempts);
           
            System.out.println("\nWelcome to \"Guess the movie\"! The game will randomly pick a movie title " +
                    "and show you how many letters it's made up of. \n" +
                    "Your goal is to try to figure out the movie by guessing one letter at a time. \n" +
                    "If a letter is indeed in the title, the computer will reveal its correct position in the word, \n" +
                    "if not, you lose a point. If you lose 10 points, game over!\n" +
                    "Let's begin!\n");
         
            while (!game.gameOver()) {
                System.out.print("You are guessing: ");
              
                System.out.println(game.getEncryptedMovie());
          
                System.out.print("You have guessed (" + game.getWrongAttempts() + ") wrong letters: ");
                
                System.out.println(game.getWrongGuesses());
                
                System.out.println("You have (" + game.getAttemptsLeft() + ") wrong guesses left.");
                
                System.out.print("Guess a letter: ");
               
                guess = input.next().toLowerCase().charAt(0);
               
                if (game.isValidGuess(guess)) {
                    
                    if (!game.hasAlreadyGuessed(guess)) {
                        
                        game.checkGuess(guess);
                    } else {
                        System.out.println("You have already guessed '" + guess + "'.");
                    }
                } else {
                    System.out.println("Please enter a letter only.");
                }
                
                System.out.println();
            }

            
            if (game.gameWon()) {
                System.out.println("CORRECT...YOU WIN!");
                System.out.println("You have guessed '" + randomMovie + "' correctly.");
            } else {
                System.out.println("GAME OVER...YOU LOSE!");
                System.out.println("The movie was '" + randomMovie + "'.");
            }

            
            input.close();
        } catch(FileNotFoundException fnf) {
            System.out.println("Movie database not found.");
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }
}