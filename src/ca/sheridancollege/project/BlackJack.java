/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Matt
 */
public class BlackJack extends Game {
    
    public BlackJack(){
        super("Black Jack");
    }
    
    
    @Override
    public void play(){
        BlackJackDealer dealer = new BlackJackDealer();
        System.out.println("Welcome to the SYST17796" + this.getGameName() + " table!");
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter your name to get started: ");
        String playerID = in.nextLine();
        System.out.println();
        System.out.print("Please enter how much money you're bring to the table: ");
        Double credits = in.nextDouble();
        System.out.println();
        this.setPlayer(new BlackJackPlayer(playerID,credits));
        
        BlackJackHand playerHand = this.getPlayer().getCards();
        
        System.out.println("The Dealer is showing " + dealer.getCards().cards.toString());
        System.out.println("You are showing " + playerHand.cards.toString());
    }

    @Override
    public void declareWinner(){
           //override and do nothing
    } 
    
    public static void main(String[] args){
        BlackJack newGame = new BlackJack();
        newGame.play();
    }
}

