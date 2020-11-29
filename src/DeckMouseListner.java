import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
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
//**************************** DECTECT THE MOVEMENT OF MOUSE ON DECK ************************//
public class DeckMouseListner extends MouseAdapter
{
    //for deck and mainMenu
    private Deck deck;
    private MainMenu main;

    //Constructor for main conection
    public DeckMouseListner(MainMenu main)
    {	this.main=main; 	}
                
    //MouseEntered on deck
    public void mouseEntered(MouseEvent evt)
    {
        //getting deck from event source or from nearest deck
	Deck deck1=(Deck)evt.getSource();	
        //if deck is not equal to deck(class deck data member)
        if (this.deck!=deck1)
        {
            //assigning deck to deck
            this.deck=deck1;
            //putting mouse selection to true
            deck1.setMouseEnter(true);
            deck1.repaint(); // repainting
        }
    }
    //MouseExited From stack of decks
    public void mouseExited(MouseEvent evt)
    {
        //getting deck from event source
        Deck deck=(Deck)evt.getSource();
        //putting mouse selected to false
	deck.setMouseEnter(false);  //after mouse is exited setting mouseenter to false
	deck.repaint(); //  repainting deck
	this.deck=null; //assigning null to deck
                
    }
        
    //Mouse Pressed on deck
    public void mousePressed(MouseEvent evt)
    {
        //getting deck from event source
        Deck deckSrc=(Deck)evt.getSource();
        //getting x,y coordinates from event
	Point point = (Point)evt.getPoint();
	//controller object to get index on which mouse on which mouse is present
        Controller control = new Controller(this.main);
	int index = control.getCardIndexOnMouse(point.y,deckSrc);
	//if index is not -1 then setting cardIindex
        if ( index > -1) 
        {
            main.cardMouseMotion.setCardIndex(index);
	}
    }
        
    //mouse released on deck card's stack
    public void mouseReleased(MouseEvent evt)
    {
        //setting deck visibility to false
        main.deck.setVisible(false);
        //getting stack of cards which is being moved
        Stack cardStack = (Stack)main.deck.getCardStack();

        //if deck(deck) is not null && if deck is different from card selected from deck
        if (this.deck!=null && this.deck!=(Deck)evt.getSource() && cardStack.size()>0)
        {
                //then getting deck from which card stack is taken
                Deck deckSource = (Deck)evt.getSource();
                //initializing pushed card to false                
                boolean pushStatus = false;
                //if deckofcards present  is not empty 
                if (this.deck.getCardStack().size() > 0)
                {
                    Card cardNext = (Card)cardStack.firstElement();
                    Card cardPrevious = (Card)this.deck.getCardStack().lastElement();
                    //Controller object tocheck if cards are addable
                    Controller control = new Controller();
                    if ( control.addAble(cardPrevious,cardNext) )
                    {
                        //if they are addable then pushing cards
                        pushcards((Iterator)cardStack.iterator(), deckSource);
                        pushStatus = true;
                    }
                }
                    
                //pushing cards at empty deck
                else
                {
                    pushcards((Iterator)cardStack.iterator(),deckSource);
                    pushStatus = true;
                }

                // move a list (1-13) after the list has completed
                Controller control =new Controller(this.main);
                //if to check moveable list
                if (control.completedStack(this.deck.getCardStack()))
                {
                        //if list is completed then moving it to completed deck of cards
                        control.moveCompletedList(this.deck.getCardStack());
                        //turning the last card if present to front
			control.turnCard(this.deck);
                }

                //if card is pushed then refresh mouse note
                if (pushStatus==true)
                {
                        //getting the x,y
			Point point = (Point)evt.getPoint();
			int index = control.getCardIndexOnMouse(point.y,this.deck);
			this.deck.setCardIndexOnMouse(index);
			this.deck.setMouseEnter(true);
                }
                else
                {
                    // remove all deck card because no card is pushed into other stack
                    main.deck.clearDeck();
                }

                // auto turn card the card from where card is taken
                control.turnCard(deckSource);
                this.deck.repaint();//repainting
           
        }
    }

    //Method : to pushcards in deck with iterator(stack)
    private void pushcards(Iterator cardlist, Deck deck)
    {
        //while loop to push all card present in stack
        while(cardlist.hasNext())
        {
            Card card = (Card)cardlist.next();//getting card
            deck.removeCard();   //removing card from source
            deck.repaint();  //repainting
            this.deck.putCard(card);    //putting card on deck
	}
	main.player.addScore(-1);   //decreasing score by 1
    }

}
