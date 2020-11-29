import java.awt.Image;
import java.awt.Toolkit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HashimChaudhry
 */
//*************************** SIMPLE CARD CLASS **********************************//
public class Card
{
        //---------------Data memeber
	private Deck deck;  //for deck association
	private Image current;  //current status of card
	private final Image front;    //front side of card
	private final Image rear;     //back side of card
	private final String value;    //value of card

	public Card(String value)
        {
                Toolkit tk = Toolkit.getDefaultToolkit();       //toolKit object with default (check .getDefaultToolkit())
		this.value = value;                              //value/type of card		
		this.front = tk.getImage("card/"+value+".png");  //getting image of card according to value(card number)
		this.rear = tk.getImage("card/rear.jpeg");       //getting rear image of card
		this.flipToRear();                                //turning to rear               
	}

        //flip card to rear side
	public void flipToRear() 
        {	this.current=this.rear; 	}
        //flip card to front
	public void flipToFront()
        { 	this.current = this.front;	}
        //return current image of card
	public Image getCurrent()
        {	return this.current;	}
        //return front of card
	public Image getFront() 
        {	return this.front;	}
        //return rear of card
	public Image getRear() 
        {	return this.rear;	}
        //return value(type of card)
	public String getValue() 
        {	return this.value; 	}
        //setting deck
	public void setDeck(Deck deck) 
        {	this.deck = deck;	}
        //getting Deck
	public Deck getDeck() 
        {	return this.deck;	}

}