import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HashimChaudhry
 */
//********************** CREATED MOUSE CLICKED ON DEALING DECK CARDS
public class DealingCardListner extends MouseAdapter
{
    //when mouse is pressed event is created and send
    public void mousePressed(MouseEvent evt)
    {
            //dealDeck gets source
            DealingDeck dealDeck = (DealingDeck)evt.getSource();
            //calling deal function in dealDeck
            dealDeck.deal();
    }
}