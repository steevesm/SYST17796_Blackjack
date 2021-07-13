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
    
    private Suit[] suits = suit.values();
    private Value[] values = value.values();

    
    public BlackJackHand(boolean isDealer){
       this.getNewHand(isDealer);
    }
    
    public void getNewHand(boolean isDealer){
        int suitLength = suits.length;
        int valueLength = values.length;
        int randIndexValue;
        int randIndexSuit;
        
        if(!this.cards.isEmpty()){
           this.cards.clear();
        }
        
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
    
    public int combinedValue(BlackJackHand hand, boolean aceIsOne){
        int[] numArray = new int[2];
        
        for(int i=0;i<2;i++){
            Value val = hand.cards.get(i).getValue();
            if(val.equals(Value.Two)){
                numArray[i] = 2;
            } else if (val.equals(Value.THREE)){
                numArray[i] = 3;
            } else if (val.equals(Value.FOUR)){
                numArray[i] = 4;
            } else if (val.equals(Value.FIVE)){
                numArray[i] = 5;
            } else if (val.equals(Value.SIX)){
                numArray[i] = 6;
            } else if (val.equals(Value.SEVEN)){
                numArray[i] = 7;
            } else if (val.equals(Value.EIGHT)){
                numArray[i] = 8;
            } else if (val.equals(Value.NINE)){
                numArray[i] = 9;
            } else if (val.equals(Value.TEN) || val.equals(Value.JACK) || val.equals(Value.QUEEN) || val.equals(Value.KING) ){
                numArray[i] = 10;
            } else if (val.equals(Value.ACE) && aceIsOne){
                numArray[i] = 1;
            } else if (val.equals(Value.ACE) && !aceIsOne){
                numArray[i] = 11;
            } else {
                return 0;
            }
        }
            
        return numArray[0] + numArray[1];
        
    }
    
}
