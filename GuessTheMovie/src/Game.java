package com.company;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


class Game {

    private String randomMovie = "";
    private String encryptedMovie = "";
    private String wrongGuesses = "";
    private String correctGuesses = "";
    private int wrongAttempts;

  
    String[] getMovieList(String filePath) throws FileNotFoundException {
        File movieDatabase = new File(filePath);
        Scanner movieDatabaseScanner = new Scanner(movieDatabase);
        StringBuilder movieListReader = new StringBuilder();
        while (movieDatabaseScanner.hasNextLine()) {
            movieListReader.append(movieDatabaseScanner.nextLine());
            movieListReader.append("\n");
        }
        movieDatabaseScanner.close();
        return movieListReader.toString().trim().split("\n");
    }

   
    String generateRandomMovie(String[] movies) {
        int movieIndex = (int) (Math.random() * movies.length);
        return movies[movieIndex].replaceAll("[^a-zA-Z\\s]", "").toLowerCase();
    }

    
    void setRandomMovie(String randomMovie) {
        this.randomMovie = randomMovie;
    }

    
    private String getRandomMovie() {
        return randomMovie;
    }

    
    String encryptMovie(String randomMovie) {
        return randomMovie.replaceAll("[a-zA-Z]", "-");
    }

    
    void setEncryptedMovie(String encryptedMovie) {
        this.encryptedMovie = encryptedMovie;
    }

   
    String getEncryptedMovie() {
        return encryptedMovie;
    }

   
    void setWrongAttempts(int wrongAttempts) {
        this.wrongAttempts = wrongAttempts;
    }

    
    int getWrongAttempts() {
        return wrongAttempts;
    }

    
    int getAttemptsLeft() {
        return 10 - wrongAttempts;
    }

   
    private void setWrongGuesses(String wrongGuesses) {
        this.wrongGuesses = wrongGuesses;
    }

    
    String getWrongGuesses() {
        return wrongGuesses;
    }

    
    private void setCorrectGuesses(String correctGuesses) {
        this.correctGuesses = correctGuesses;
    }

   
    private String getCorrectGuesses() {
        return correctGuesses;
    }

    
    boolean isValidGuess(char guess) {
        return guess >= 'a' && guess <= 'z';
    }

   
    boolean hasAlreadyGuessed(char guess) {
        return getWrongGuesses().indexOf(guess) >= 0 || getCorrectGuesses().indexOf(guess) >= 0;
    }

   
    
    void checkGuess(char guess) {
        if (getRandomMovie().indexOf(guess) >= 0) {
            StringBuilder encryptedMovieTracker = new StringBuilder(getEncryptedMovie());
            for (int i = 0; i < encryptedMovie.length(); i++) {
                if (guess == randomMovie.charAt(i)) {
                    encryptedMovieTracker.setCharAt(i, guess);
                }
            }
            setEncryptedMovie(encryptedMovieTracker.toString());
            correctGuesses += guess + " ";
            setCorrectGuesses(correctGuesses);
        } else {
            wrongGuesses += guess + " ";
            setWrongGuesses(wrongGuesses);
            wrongAttempts++;
            setWrongAttempts(wrongAttempts);
        }
    }

   
    boolean gameWon() {
        return getEncryptedMovie().indexOf('-') < 0;
    }

   
    private boolean gameLost() {
        return getWrongAttempts() == 10;
    }

    
    boolean gameOver() {
        return gameWon() || gameLost();
    }
}