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
public class BlackJackPlayer extends Player{
    
    private double bankRoll;
    private BlackJackHand cards;
    
    public BlackJackPlayer(String name, double totalCredits){
        super(name);
        bankRoll = totalCredits;
        cards = new BlackJackHand(false);      
    }
    //Getter
    public BlackJackHand getCards() {
        return cards;
    }
    //Setter
    public void setCards(BlackJackHand cards) {
        this.cards = cards;
    }
    //Setter
    public void setBankRoll(double credits){
        bankRoll = credits;
    }
    //Getter
    public double getBankRoll(){
        return this.bankRoll;
    }
    
    @Override
    public void play() {
        // Override and do nothing
    }
}
