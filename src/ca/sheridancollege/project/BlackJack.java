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
 * @modifier paulske
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
        System.out.print("Please enter how much money you're bringing to the table: ");
        Double credits = in.nextDouble();
        System.out.println();
        Double bet = 0.00;
        
        this.setPlayer(new BlackJackPlayer(playerID,credits));
        BlackJackHand playerHand = this.getPlayer().getCards();
        
        boolean contPlaying = true;
        while(contPlaying){
            System.out.println("You have " + this.getPlayer().getBankRoll() + "$ left.");
            System.out.println("How much would you like to bet?");
            bet = in.nextDouble();
            in.nextLine(); //Throw away the \n not consumed by nextDouble()
            this.getPlayer().setBankRoll(this.getPlayer().getBankRoll() - bet);
            
            
            System.out.println("The Dealer is showing " + dealer.getCards().cards.toString());
            System.out.println("You are showing " + playerHand.cards.toString());
            System.out.println("Your Score is currently: " + this.getPlayer().getCards().combinedValue(playerHand, true));
            
            

            boolean choiceValid = false;
            int userChoice;
            while(!choiceValid){
                System.out.println("What would you like to do?");
                System.out.println("1: Hit");
                System.out.println("2: Stay");
                System.out.println("3: Double Down");
                System.out.println("4: Split Pairs");
                userChoice = in.nextInt();

                if(userChoice == 1){
                    playerHand.addToHand();
                    System.out.println("You are now showing " + playerHand.cards.toString());
                    System.out.println("Your Score is currently: " + this.getPlayer().getCards().combinedValue(playerHand, true));
                    if(this.getPlayer().getCards().combinedValue(playerHand, true) > 21){
                        //Code for losing here
                        System.out.println("You Lose");
                    }
                    choiceValid = true;
                } else if(userChoice == 2){
                    System.out.println("You are choosing to stay and your score is: " + this.getPlayer().getCards().combinedValue(playerHand, true));
                    choiceValid = true;
                } else if (userChoice == 3){
                    //Double Down Logic Here
                    choiceValid = true;
                } else if (userChoice == 4){
                    //Split Pairs Logic Here
                    choiceValid = true;
                } else {
                   System.out.println("Invalid Choice, please choose again");
                }
            }
            
            
            in.nextLine(); //Throw away the \n not consumed by nextInt()
            

            //Post Hand Continue Playing Menu
            boolean contValid = false;
            while(!contValid){
                System.out.println("Would you like to continue playing? (Y/N)");
                String userInput = in.nextLine();
                
                 if(userInput.equals("y") || userInput.equals("Y")){
                     this.getPlayer().getCards().getNewHand(false);
                     dealer.getCards().getNewHand(true);
                     contValid = true;
                } else if (userInput.equals("n") || userInput.equals("N")){
                     System.out.println("Thank you for playing!");
                     contValid = true;
                     contPlaying = false;
                } else {
                    System.out.println("Invalid input please try again");
                }
            }
           
            
        }
        
        
        
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
