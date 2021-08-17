/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

/**
 *
 * @author steevesm, 2021
 */
public class BlackJackDealer extends Player{
    
    private double bankRoll;
    private BlackJackHand cards;
    
    public BlackJackDealer(){
        super("Dealer");
        cards = new BlackJackHand(true);      
    }
    //Getter
    public BlackJackHand getCards() {
        return cards;
    }
    //Setter
    public void setCards(BlackJackHand cards) {
        this.cards = cards;
    }
    
    @Override
    public void play() {
        // Override and do nothing
    }
}
