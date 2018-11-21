/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessnumber;

import java.util.Random;

/**
 *
 * @author pedago
 */
public class Player {
    private final String username;
    private final int numberToGuess;
    private int guessCount;
    private int lastGuess;
    
    public Player(String username) {
        this.username = username;
        
        Random rng = new Random();
        numberToGuess = rng.nextInt(101);
        
        guessCount = 0;
        lastGuess = -1;
    }
    
    protected Player() {
        this("EMPTYUSER");
    }
    
    public String getUsername() {
        return username;
    }
    
    public int getNumberToGuess() {
        return numberToGuess;
    }
    
    public int getGuessCount() {
        return guessCount;
    }
    
    public void guess(int guessedNumber) {
    lastGuess = guessedNumber;
        guessCount++;
    }
    
    public int getLastGuess() {
        return lastGuess;
    }
    public String getLastGuessComment() {
        if (lastGuess > numberToGuess)
            return "trop haut";
        else
            return "trop bas";
    }
}
