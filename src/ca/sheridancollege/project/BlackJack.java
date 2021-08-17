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
 * @author steevesm, 2021
 * @modifier paulske
 */
public class BlackJack extends Game {

    public BlackJack() {
        super("Black Jack");
    }

    @Override
    public void play() {

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

        this.setPlayer(new BlackJackPlayer(playerID, credits));
        BlackJackHand playerHand = this.getPlayer().getCards();
        playerHand.setSize(2);

        boolean contPlaying = true;
        while (contPlaying) {
            System.out.println("You have " + this.getPlayer().getBankRoll() + "$ left.");
            System.out.println("How much would you like to bet?");
            bet = in.nextDouble();
            in.nextLine(); //Throw away the \n not consumed by nextDouble()
            this.getPlayer().setBankRoll(this.getPlayer().getBankRoll() - bet);

            System.out.println("The Dealer is showing " + dealer.getCards().cards.toString());
            System.out.println("You are showing " + playerHand.cards.toString());
            System.out.println("Your Score is currently: " + this.getPlayer().getCards().combinedValue(playerHand, true));

            boolean choiceValid = false;
            boolean isSplit = false;
            BlackJackHand splitHand1 = new BlackJackHand(playerHand.cards.get(0));
            BlackJackHand splitHand2 = new BlackJackHand(playerHand.cards.get(1));
            int userChoice;
            while (!choiceValid) {
                showOptions();
                userChoice = in.nextInt();

                if (userChoice == 1) {
                    //Hit logic here
                    if (isSplit) {
                        splitHand1.addToHand();
                        splitHand2.addToHand();
                        splitHand1.setSize(splitHand1.getSize() + 1);
                        splitHand2.setSize(splitHand2.getSize() + 1);
                        System.out.println("Hand 1 contains " + splitHand1.cards.toString());
                        System.out.println("Hand 2 contains " + splitHand2.cards.toString());
                        System.out.println("Your Score for hand 1 is currently: " + this.getPlayer().getCards().combinedValue(splitHand1, true));
                        System.out.println("Your Score for hand 2 is currently: " + this.getPlayer().getCards().combinedValue(splitHand2, true));
                    } else {
                        playerHand.addToHand();
                        System.out.println("You are now showing " + playerHand.cards.toString());
                        playerHand.setSize(playerHand.getSize() + 1);
                        System.out.println("Your Score is currently: " + this.getPlayer().getCards().combinedValue(playerHand, true));
                    }
                } else if (userChoice == 2) {
                    //Stay logic here
                    if (isSplit) {
                        System.out.println("You are choosing to stay and your score for hand 1 is: " + this.getPlayer().getCards().combinedValue(splitHand1, true));
                        System.out.println("You are choosing to stay and your score for hand 2 is: " + this.getPlayer().getCards().combinedValue(splitHand2, true));
                    } else {
                        System.out.println("You are choosing to stay and your score is: " + this.getPlayer().getCards().combinedValue(playerHand, true));
                    }
                    choiceValid = true;

                } else if (userChoice == 3) {
                    //Double Down Logic Here 
                    if (!isSplit) {
                        if (playerHand.cards.size() == 2) {
                            playerHand.addToHand();
                            if (this.getPlayer().getBankRoll() - bet > 0) {
                                this.getPlayer().setBankRoll(this.getPlayer().getBankRoll() - bet);
                                bet *= 2;
                                System.out.println("You are now showing " + playerHand.cards.toString());
                                playerHand.setSize(playerHand.getSize() + 1);
                                System.out.println("Your Score is currently: " + this.getPlayer().getCards().combinedValue(playerHand, true));
                                choiceValid = true;
                            } else {
                                
                                System.out.println("You don't have enough money to bet again");
                                choiceValid = false;
                            }
                        } else {
                            System.out.println("You can only double down if your hand contains 2 cards");
                            choiceValid = false;
                        }
                    } else {
                        System.out.println("You cannot double down if your deck is split");
                        choiceValid = false;
                    }

                } else if (userChoice == 4) {
                    //Split Pairs Logic Here
                    if (playerHand.cards.get(0).getValue() == playerHand.cards.get(1).getValue() && playerHand.getSize() == 2) {
                        isSplit = true;
                        System.out.println("You are now showing two hands");
                        System.out.println("Hand 1 contains " + splitHand1.cards.toString());
                        System.out.println("Hand 2 contains " + splitHand2.cards.toString());
                    } else {
                        System.out.println("You must have only two cards of the same value in order to split");
                        choiceValid = false;
                    }

                } else {
                    System.out.println("Invalid Choice, please choose again");
                }
            }

            in.nextLine(); //Throw away the \n not consumed by nextInt()

            //Dealer plays hand
            
            boolean dealerBust = false;
            //here
            dealer.getCards().cards.get(1).setDown(false);
            int dealerTotal = dealer.getCards().combinedValue(dealer.getCards(), true);
            System.out.println("The Dealer is showing " + dealer.getCards().cards.toString());
            while (dealer.getCards().combinedValue(dealer.getCards(), true) < 17 && dealer.getCards().combinedValue(dealer.getCards(), false) < 17) {
                dealer.getCards().addToHand();
                dealerTotal = dealer.getCards().combinedValue(dealer.getCards(), true);
                System.out.println("The Dealer is now showing " + dealer.getCards().cards.toString());
                System.out.println("The Dealer's score is " + dealerTotal);
            }
            if (dealer.getCards().combinedValue(dealer.getCards(), false) > 21 && dealer.getCards().combinedValue(dealer.getCards(), true) > 21) {
                dealerBust = true;
            } else if (dealer.getCards().combinedValue(dealer.getCards(), false) > 21) {
                dealerTotal = dealer.getCards().combinedValue(dealer.getCards(), true);
            } else if (dealer.getCards().combinedValue(dealer.getCards(), true) < 17) {
                dealerTotal = dealer.getCards().combinedValue(dealer.getCards(), false);
            }

            //The following represent the two possible hand values for the player
            int playerHandTotal = this.getPlayer().getCards().combinedValue(playerHand, true);
            int playerHandTotalAce = this.getPlayer().getCards().combinedValue(playerHand, false);

            //The following represent the two possible hand values for each split hand
            int splitHandTotal1 = this.getPlayer().getCards().combinedValue(splitHand1, true);
            int splitHandTotal1Ace = this.getPlayer().getCards().combinedValue(splitHand1, false);
            int splitHandTotal2 = this.getPlayer().getCards().combinedValue(splitHand2, true);
            int splitHandTotal2Ace = this.getPlayer().getCards().combinedValue(splitHand1, false);

            //The following will store the final hand values for each split hand
            //This ensures that the hands will store the most optimal Ace value
            int finalSplitHand1 = 0;
            int finalSplitHand2 = 0;

            //the following represent whether each split hand is a bust
            boolean splitBust1 = false;
            boolean splitBust2 = false;
            if (isSplit) {
                
                //The following if statements determine which splitHandTotal values to use
                if (splitHandTotal1 > 21 && splitHandTotal1Ace > 21) {
                    splitBust1 = true;
                } else if (splitHandTotal1 < 21 && splitHandTotal1Ace > 21) {
                    finalSplitHand1 = splitHandTotal1;
                } else if (splitHandTotal1 < 21 && splitHandTotal1Ace < 21) {
                    finalSplitHand1 = splitHandTotal1Ace;
                }
                if (splitHandTotal2 > 21 && splitHandTotal2Ace > 21) {
                    splitBust2 = true;
                } else if (splitHandTotal2 < 21 && splitHandTotal2Ace > 21) {
                    finalSplitHand2 = splitHandTotal2;
                } else if (splitHandTotal2 < 21 && splitHandTotal2Ace < 21) {
                    finalSplitHand2 = splitHandTotal2Ace;
                }
                
                //The following if statements determine whether the split hands win or lose
                if (splitBust1 == true && splitBust2 == true) {
                    System.out.println("Both split hands are busts and you lose");
                } else if (finalSplitHand1 == 21 || finalSplitHand2 == 21) {
                    System.out.println("You total 21 and you win 1.5x your bet");
                    this.getPlayer().setBankRoll(this.getPlayer().getBankRoll() + bet * 1.5);
                } else if (finalSplitHand1 > dealerTotal || finalSplitHand2 > dealerTotal) {
                    System.out.println("Your total exceeds the dealer's and you win 2x your bet");
                    this.getPlayer().setBankRoll(this.getPlayer().getBankRoll() + bet * 2);
                } else if (finalSplitHand1 == dealerTotal || finalSplitHand2 == dealerTotal) {
                    System.out.println("Your total is tied with the dealer's and your bet is returned");
                    this.getPlayer().setBankRoll(this.getPlayer().getBankRoll() + bet);
                } else if (finalSplitHand1 < dealerTotal && finalSplitHand2 < dealerTotal){
                    System.out.println("Your total is lower than the dealer's and you lose");
                }
            } else {
                //the following if statements determine whether the playerHand wins or loses
                if (playerHandTotal > 21 && playerHandTotalAce > 21) {
                    System.out.println("Your total exceeds 21 and you lose");
                } else if (playerHandTotal == 21 || playerHandTotalAce == 21) {
                    System.out.println("Your total is 21 and you win 1.5x your bet");
                    this.getPlayer().setBankRoll(this.getPlayer().getBankRoll() + bet * 1.5);
                } else if(dealerTotal > 21){
                    System.out.println("The dealer busted! You won!");
                    this.getPlayer().setBankRoll(this.getPlayer().getBankRoll() + 2 * bet);
                } else if (playerHandTotal < dealerTotal) {
                    System.out.println("Your total is lower than the dealer's and you lose");
                } else if (playerHandTotal == dealerTotal) {
                    System.out.println("Your total is tied with the dealer's and your bet is returned");
                    this.getPlayer().setBankRoll(this.getPlayer().getBankRoll() + bet);
                } else {
                    System.out.println("Your total exceeds the dealer's and you win 2x your bet");
                    this.getPlayer().setBankRoll(this.getPlayer().getBankRoll() + 2 * bet);
                }
            }

            //Post Hand Continue Playing Menu
            boolean contValid = false;
            while (!contValid) {
                System.out.println("Would you like to continue playing? (Y/N)");
                String userInput = in.nextLine();

                if (userInput.equals("y") || userInput.equals("Y")) {
                    this.getPlayer().getCards().getNewHand(false);
                    dealer.getCards().getNewHand(true);
                    contValid = true;
                } else if (userInput.equals("n") || userInput.equals("N")) {
                    System.out.println("Thank you for playing!");
                    contValid = true;
                    contPlaying = false;
                } else {
                    System.out.println("Invalid input please try again");
                }
            }

        }
    }

    public static void showOptions() {
        System.out.println("What would you like to do?");
        System.out.println("1: Hit");
        System.out.println("2: Stay");
        System.out.println("3: Double Down");
        System.out.println("4: Split Pairs");
    }

    @Override
    public void declareWinner() {
        //override and do nothing
    }

    public static void main(String[] args) {
        BlackJack newGame = new BlackJack();
        newGame.play();
    }
}
