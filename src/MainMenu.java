import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HashimChaudhry
 */

public class MainMenu extends JFrame
{
    private String version;
    //card dimensions
    public int cardWidth = 71;
    public int cardHeight = 96;
    //background colour
    public Color backgroundColor = new Color(0,112,26);    
    //Player and cards
    public Player player;
    public Deck[] deckArray;
    public Vector cardList;
    //For random numbers
    Random random = new Random();    
    //For different Panels
    public DeckMouseListner deckMouse;
    public CardMouseListner cardMouseMotion;
    public ScorePanel scorePanel;
    public BackgroundPanel backgroundPanel;
    public VictoryPanel victoryPanel;    
    //for deck
    public Deck deck;
    public DealingDeck dealDeck;
    public CompletedDeck completedDeck;
    //For menubar menu items
    private JCheckBoxMenuItem beginner;
    private JCheckBoxMenuItem intermediate;
    private JCheckBoxMenuItem advance;
    private int difficultyLevel;    
    //For difficulty levels
    public static final int SIMPLE_DIFF = 1;
    public static final int MIDDLE_DIFF = 2;
    public static final int ADVANCED_DIFF = 3;

	// constructor for araneid game
	public MainMenu(int diffLevel)
        {
                //Name of window
		super("Spider Solitaire Game");
                //initializing diff with difficulty level
                this.difficultyLevel = diffLevel;                
                //version
                this.version = "0.0.1";
                //closing operation
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Size this frame
		setSize(800,600);
                //setting resizablity to false
                setResizable(false);
		// create menu bar for game
		JMenuBar menuBar = new JMenuBar();
                //Creating menu
		JMenu menuGame = new JMenu("Game");
		menuBar.add(menuGame);    //adding menu to menu bar
                //Action object for getting the action performed on menu bar items*
                Action action;		
                // use a action to construct a menu item for start a new game
		action = new AbstractAction("New Game") 
                {
                        //calling restart function for new ga
			public void actionPerformed(ActionEvent evt) 
                        {	restartGame();	}
		};
                
                //Adding New Game action to menubar file
		menuGame.add(new JMenuItem(action));
                //seperator for visibility
		menuGame.addSeparator();
                //For low level difficulty if action is performed
		action = new AbstractAction("Beginner Level")
                {
                        //for action performed
			public void actionPerformed(ActionEvent evt) 
                        {
                            beginner.setState(false);
                            advance.setState(false);//middle level diff
                            intermediate.setState(false);//advanced level diff
                            difficultyLevel = SIMPLE_DIFF; //setting difficulty to simple
                            restartGame();//starting game
			}
		};
                
		beginner = new JCheckBoxMenuItem(action);		
                //assigning simple level to difficulty level
                if(this.difficultyLevel == SIMPLE_DIFF)
                {   beginner.setState(true);  }		
                //adding simple
                menuGame.add(beginner);

               //------------------------Intermediate Level
		action = new AbstractAction("Intermediate Level") 
                {
			public void actionPerformed(ActionEvent evt)
                        {
                            beginner.setState(false);
                            intermediate.setState(true);
                            advance.setState(false);
                            difficultyLevel = MIDDLE_DIFF;
                            restartGame();
			}
		};
                
		intermediate = new JCheckBoxMenuItem(action);
		
                if (this.difficultyLevel == MIDDLE_DIFF)
                {   intermediate.setState(true); }		
                menuGame.add(intermediate);

                //---------------------- Difficull Level
                //functions performed are similar to simple level part
		action = new AbstractAction("Advance Level")
                {
			public void actionPerformed(ActionEvent evt) 
                        {
                            beginner.setState(false);
                            intermediate.setState(false);
                            advance.setState(true);
                            difficultyLevel = ADVANCED_DIFF;
                            restartGame();
			}
		};
                
                advance = new JCheckBoxMenuItem(action);
		if (this.difficultyLevel == ADVANCED_DIFF)
                {   advance.setState(true);   }		
                menuGame.add(advance);

                //------------------ When exit is clicked
		// use a action to construct a menu item for exit menu
		action = new AbstractAction("Exit")
                {
                	public void actionPerformed(ActionEvent evt) 
                        {
				dispose();
				System.exit(0);
			}
		};
                
		menuGame.addSeparator();
		menuGame.add(new JMenuItem(action));
                
                //------------------ For Help
		JMenu menuHelp = new JMenu("Help");
                
                action = new AbstractAction("How to play")
                {
                        public void actionPerformed(ActionEvent e)
                        {
                            AboutHelp aboutHelp = new AboutHelp();
                            aboutHelp.show();
                        }
                };
                JMenuItem menuHelping = new JMenuItem(action);
                menuHelp.add(menuHelping);
                menuHelp.addSeparator();
                
                //Creating new about
		action = new AbstractAction("About")
                {
			public void actionPerformed(ActionEvent evt)
                        {
				AboutAuthor aboutAuthor = new AboutAuthor(getVersion());
				aboutAuthor.show();
			}
		};
                
		menuHelp.add(new JMenuItem(action));
		menuBar.add(menuHelp);

                //For displaying the menu bar that we created
		this.setJMenuBar(menuBar);
		//for display
                show();
                //initializing
                init();
	}

// method:
//-------------------------- Initialize the game when a new game starts
	public void init() 
        {
            //getting computer name
            String userName=null;
            try
            {
                userName = System.getProperty("user.name");            
            }
            catch(SecurityException se)
            {}
            
            //in case if no computer name is found
            if(userName.length() == 0)
            {   userName = "player1";    }
            
            //making object of player
            this.player = new Player(userName);
            // create deck of card
            cardList = new Vector();
            
            //for totalDecks
            int totalDeck = 0;
            
            switch(this.difficultyLevel)
            {
                case MIDDLE_DIFF:
                {
                    while(totalDeck != 4)
                    {
                        int i = 1;
			while( i < 34 )
                        {
                            //if to get only 2 type of cards
/*Spade type card*/         if((i== 1 || i== 2 || i== 3 || i== 4 || i== 5 || i== 6 || i== 7 || i== 8 || i== 9 || i==10 || i==11 || i==12 || i==13)
/*Heart type card*/          ||(i==21 || i==22 || i==23 || i==24 || i==25 || i==26 || i==27 || i==28 || i==29 || i==30 || i==31 || i==32 || i==33) )
                            {
				Card card = new Card(Integer.toString(i));
				this.cardList.add(card);
                            }
                            i++;
			}
                        
                        totalDeck++;
                    }
			
                    break;
                }
                
                case ADVANCED_DIFF:
                {
                    while(totalDeck != 2)
                    {
                        int i = 1;
                        while( i < 74 )
                        {
/*Spade type Card*/         if( (i== 1 || i== 2 || i== 3 || i== 4 || i== 5 || i== 6 || i== 7 || i== 8 || i== 9 || i==10 || i==11 || i==12 || i==13)
/*Heart Type Card*/          || (i==21 || i==22 || i==23 || i==24 || i==25 || i==26 || i==27 || i==28 || i==29 || i==30 || i==31 || i==32 || i==33)
/*Club Type Card*/           || (i==41 || i==42 || i==43 || i==44 || i==45 || i==46 || i==47 || i==48 || i==49 || i==50 || i==51 || i==52 || i==53)
/*Diamond Type Card*/        || (i==61 || i==62 || i==63 || i==64 || i==65 || i==66 || i==67 || i==68 || i==69 || i==70 || i==71 || i==72 || i==73)       )
                            {
                                Card card = new Card(Integer.toString(i));
                                this.cardList.add(card);
                            }
                            i++;
                        }
                        totalDeck++;
                    }
		break;
                }
                    
                case SIMPLE_DIFF:			
                default:
                {
                    while(totalDeck != 8)
                    {
                        int i=1;
			while(i<14)
                        {
                            Card card = new Card(Integer.toString(i));
                            this.cardList.add(card);
                            i++;
                        }
                        totalDeck++;
                    }
                break;    
		}

            }//switch ending
            
            //connecting backgroundPanel with main
            backgroundPanel = new BackgroundPanel(this);

            // making deck
            this.deck = new Deck(this);
            this.deck.setVisible(false);
            backgroundPanel.add(deck);

            // making score panel
            this.scorePanel = new ScorePanel(this);
            backgroundPanel.add(scorePanel);

            // for mouse listener
            this.deckMouse = new DeckMouseListner(this);
            this.cardMouseMotion = new CardMouseListner(this);

            //10 deckArray for storing some of cards
            deckArray= new Deck[10];
            //these loop will add cards from 104 cards vector
            for (int i=0;i<5;i++)
            {
                //nested loop will add in stack
                for (int j=0; j<deckArray.length; j++)
                {
                    //if deckArray is empty so we are adding mouse listners
                    if (deckArray[j]==null)
                    {
                        deckArray[j] = new Deck(j,this);
                        backgroundPanel.add(deckArray[j]);
                        deckArray[j].addMouseListener(deckMouse);
                        deckArray[j].addMouseMotionListener(cardMouseMotion);
                    }
			
                    deal(deckArray[j]);
		}
            }
            
            //for loop which will add 4 cards to first 4 deck array indexs
            for(int k=0; k<4; k++)
            {      deal( deckArray[k] );        }

            //player deal cards
            dealDeck = new DealingDeck(this);
            backgroundPanel.add(dealDeck);
            //for loop for adding remaining 50 random cards in dealdeck
            for(int i=0;i<5*deckArray.length;i++)
            {
                int r = random.nextInt(cardList.size());
		Card card = (Card)cardList.get(r);
		cardList.remove(r);
		dealDeck.putCard(card);
            }
            dealDeck.deal();

            //for completed cards
            this.completedDeck = new CompletedDeck(this);
            backgroundPanel.add(completedDeck);

            this.getContentPane().add(backgroundPanel);

            this.victoryPanel = new VictoryPanel(this);
	}

	// method: deal to every last of deckArray
	public void deal(Deck targetDeck)
        {
            //get random cards index from 104 cards
            int r = random.nextInt(cardList.size());
            //card is taken from that index
            Card card = (Card)cardList.get(r);
            //removing card from that index
            cardList.remove(r);
            //putting card in deck
            targetDeck.putCard(card);
	}

	public void restartGame()
        {
            //getting the decision from user
            int decision = JOptionPane.showConfirmDialog(null,"Start New Game");
            //if user don't want to start new game then returning to call
            if (decision == JOptionPane.NO_OPTION || decision == JOptionPane.CANCEL_OPTION)
            {       return;     }            
            //else if user start new game
            else if(decision == JOptionPane.YES_OPTION)
            {
                //disposing/ removing the previous panel
                dispose();
                //recursive call to main for new game
                MainMenu newGame = new MainMenu(this.difficultyLevel);
                //for visibility issue of the window
                newGame.setVisible(true);   
            }//else if ending
        }//restart method ending
        
	public String getVersion()
        {	return this.version;	}
        
        public static void main(String[] args) 
        {
            //get the windows appearence settings
	    try
            {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (Exception exception)
            {}
            
            System.out.println(".......................");
            System.out.println("Solitaire Game");
            System.out.println(".......................");

            //object of new game
            MainMenu thisgame = new MainMenu(0);
        }

}//class ending