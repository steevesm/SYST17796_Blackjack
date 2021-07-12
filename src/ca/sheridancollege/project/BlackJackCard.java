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
public class BlackJackCard extends Card {
    
    private boolean down;

    public BlackJackCard(Value value, Suit suit, boolean isFaceDown){
        super(value, suit);
        this.down = isFaceDown;
    }
    
    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
    
    @Override
    public String toString(){
        if (!this.isDown()){
            return this.getValue() + " OF " + this.getSuit();
        } 
        return "Face Down Card";
    }
}
