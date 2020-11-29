import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HashimChaudhry
 */
//************************* CREATION FOR MOVEMENT OF SELECTED CARD ************************//
public class CardMouseListner extends MouseMotionAdapter
{
        //setting cards width and height and index(-1)
	private int cardIndex;
	private MainMenu main;

        //mainMenu constructor 
	public CardMouseListner(MainMenu main)
        {
		this.main=main;
                cardIndex=-1;
        }
        
        //mouse dragged event
	public void mouseDragged(MouseEvent evt)
        {
            //if card index is greater then -1
            if (this.cardIndex > -1)
            {
                //getting deck of cards from event source
                Deck deck = (Deck)evt.getSource();
                //getting stack of cards from deck
                Stack cards = (Stack)deck.getCardStack();
                //clearing main deck for use
                main.deck.clearDeck();
                        
                //intializing count to 0
                int counter = 0;
                //for loop which move from cardIndexNumber to card size
                for (int i = cards.size()-this.cardIndex; i<cards.size(); i++)
                {
                    //cards at selected index
                    Card carditem = (Card)cards.elementAt(i);
                    //adding card to deck
                    main.deck.putCard(carditem);
                    counter++;    //inc to count
                }

                //Point get point of x,y
                Point point = (Point)evt.getPoint();
                //deck reshaping with changes made in for loop
                main.deck.reshape( deck.getX()+point.x-48, point.y, 71 , (main.deck.getDeckCardsSpace()+5)*(counter-1)+96 );//x, y, height, width
                main.deck.setVisible(true);//setting visibility to true
            }
	}
        
        //move moved event
	public void mouseMoved(MouseEvent evt)
        {
                //getting deck of cards from event source
		Deck deck = (Deck)evt.getSource();
                //if deck is not null
		if (deck != null)
                {
                    //getting the coordinates from event
                    Point point = (Point)evt.getPoint();
                    //Controller object for getting the index on which mouse is present
                    Controller control = new Controller(this.main);
                    //getting index
                     int index = control.getCardIndexOnMouse(point.y,deck);
                    //if index is greater then zero
                    if (index>0)
                    {
                        //setting card index of deck
			deck.setCardIndexOnMouse(index);
			deck.setMouseEnter(true);//setting mouse click to true
			deck.repaint(); // repainting
                    }
                        
		}//if ending
	}
        
        //setting card index
	public void setCardIndex(int value)
        {	this.cardIndex=value;	}

        //getting card index
	public int getCardIndex()
        {	return this.cardIndex;	}
        
        //removing card
	public void removeCardIndex()
        {	this.cardIndex=-1;	}

}
