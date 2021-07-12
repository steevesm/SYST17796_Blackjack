/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import java.util.Random;

/**
 *
 * @author steevesm, 2021
 */
public class BlackJackHand extends GroupOfCards{
    
    private Value value;
    private Suit suit;
    
    
    public BlackJackHand(boolean isDealer){
        
        Suit[] suits = Suit.values();
        Value[] values = Value.values();
        int suitLength = suits.length;
        int valueLength = values.length;
        int randIndexValue;
        int randIndexSuit;
        
        if(isDealer){
            for(int i=0;i<2;i++){
                randIndexValue = new Random().nextInt(valueLength);
                randIndexSuit = new Random().nextInt(suitLength);
                if(i%2 == 0){
                    this.cards.add(new BlackJackCard(values[randIndexValue], suits[randIndexSuit], false));
                } else {
                    this.cards.add(new BlackJackCard(values[randIndexValue], suits[randIndexSuit], true));
                }
                     
            }
        } else {
            for(int i=0;i<2;i++){
                randIndexValue = new Random().nextInt(valueLength);
                randIndexSuit = new Random().nextInt(suitLength);
                this.cards.add(new BlackJackCard(values[randIndexValue], suits[randIndexSuit], false));     
            }
        }
        
  
    }
    
}
