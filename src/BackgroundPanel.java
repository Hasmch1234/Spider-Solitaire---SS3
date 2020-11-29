import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HashimChaudhry
 */
//************************* BackgroundPanel is class which have all panel(score board, completed/Deal/foundation Cards)
public class BackgroundPanel extends JPanel
{
	private MainMenu main;
        
	public BackgroundPanel(MainMenu main)
        {
		super();
		this.main = main;
	}

	public void paintComponent(Graphics g) 
        {
		Graphics2D component = (Graphics2D)g;   //graphics component
                
		if (main.cardMouseMotion.getCardIndex()<0)    //if card Index is -1 then setting deck visiblity to false
                {	main.deck.setVisible(false);    }

		component.setColor(main.backgroundColor);   //getting main background colour
		component.fillRect( 0 , 0 , 800 , 600);     //drawing a fill rectangle with background colour for background
                
                //x:distance from left, y:distance from top, w:for internal distance from left side, h:length of cards
		main.scorePanel.reshape( 300 , 430 , 200 , 96 );         //for score panel
		main.dealDeck.reshape( 600 , 430 , 171 , 96 );            //for dealing cards deck
		main.completedDeck.reshape( 15 , 430 , 171 , 96 );        //for completed cards deck
                
                //10 for ten cards deck
                int deckSpace = 78;     //for spacing among deck cards
		int cardSpace = getSize().height/44;    //for spacing among cards
		
                //for loop for setting decks cards shapes which move 10 times
                for (int i=0; i<main.deckArray.length; i++)
                {
                    try
                    {
                        //for setting cards space
			main.deckArray[i].setDeckCardsSpace(cardSpace);
                        //coordinates            from left, from top, cards width, total possible cards height in deck
                        main.deckArray[i].reshape( 7+i*deckSpace, 20, 71, 400 );    
                    }
                    catch(NullPointerException npe){}
                }
                //setting spacing among cards
		main.deck.setDeckCardsSpace(cardSpace);

                //for victory panel if victory panel become visible then setting it coordinates
                if (main.victoryPanel.isVisible())
                {
			main.victoryPanel.reshape( 0 , 0 , 800 , 600 );
		}//if ending

        }//method ending
        
}//class ending