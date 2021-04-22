/**
* @authors Filipa Ivankovic & Saelda Rrahi
* ISTE 121-801
* Mini Project 1 - Quiz Quest
* Main class
*/

//Importing
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.util.*;
import java.awt.event.*;
import java.io.*;

//QuizQuest start of main class
public class QuizQuest implements ActionListener
{
   //Member variables
   /**
   * JButton jbMove - controls player movement
   * JFrame quest - main frame where the board,button,menu,player icons are located
   */
   JButton jbMove;
   JFrame quest;
   
   /**
   * Integers
   * p1Space - the current space location of p1
   * p2Space - the current space location of p2
   * playerTurn - controls which player turn it is - can be 1 or 2
   */
   public int p1Space = 1;
   public int p2Space = 1;
   int playerTurn = 1;
   
   /**
   * Booleans
   * colored = true - controls whether colors for the board are enabled or disabled
   * victory = false - switch that checks whether a player has reached the last space(16) - switches to true when they did
   * questionUp = false - switches to true when question is popped up, this disables the move button until the question has been answered
   */
   private boolean colored = true;
   private boolean victory = false;
   private boolean questionUp = false;
   
   /**
   * JMenuItem 
   * jmiExit,jmiAbout,jmiColor,jmiReset,jmiCredits
   * JPanel
   * jpCommands
   */
   private JMenuItem jmiExit,jmiAbout,jmiColor,jmiReset,jmiCredits;
   private JPanel jpCommands;
   
   /**
   * Player Icons
   * Player 1 is represented by the color RED
   * Player 2 is by the color BLUE
   * Icons were taken and slightly edited from: https://archives.bulbagarden.net/wiki/Category:Generation_VI_menu_sprites
   */
   private final Icon p1 = new ImageIcon("Icons/p1.png");
   private final Icon p2 = new ImageIcon("Icons/p2.png");
   
   /**
   * Global DrawPane inner class which contains the paintComponent(Graphics g)
   */
   DrawPane paint;
   
   /**
   * ArrayLists that hold the coordinates for the squares
   * Player1 moves on these coordinates
   * [0] = 10 , [1] = 110, [2] = 210, [3] = 310
   */
   private ArrayList<Integer> fieldXPos = new ArrayList<Integer>();
   private ArrayList<Integer> fieldYPos = new ArrayList<Integer>();
   
   /**
   * Arraylist that holds X coordinates for Player2
   * Uses the same coordinates for Y as Player 1
   * [0] = 50, [1] = 150, [2] = 250, [3] = 350
   */
   private ArrayList<Integer> p2XPos = new ArrayList<Integer>();
   
   
   //QuizQuest constructor
   public QuizQuest()
   {
      //quest JFrame
      quest = new JFrame();
      
      //JPanel
      jpCommands = new JPanel();
      jbMove = new JButton("Move it");
      
      //Adding JButton jbMove to JPanel jpCommands
      jpCommands.add(jbMove);
      
        
      //JMenu
      JMenuBar menubar = new JMenuBar();
      JMenu jmMenu1 =new JMenu("File");
      JMenu jmMenu2 = new JMenu("Options");
      jmiExit = new JMenuItem("Exit");
      jmiAbout = new JMenuItem("Help");//About how to play
      jmiCredits = new JMenuItem("Credits");
      jmiColor = new JMenuItem("Turn on/off colors");
      jmiReset = new JMenuItem("Reset");
      
      //Adding JMenus to JMenuBar
      menubar.add(jmMenu1);
      menubar.add(jmMenu2);
      
      //Adding JMenuItems to JMenus
      jmMenu1.add(jmiAbout);
      jmMenu1.add(jmiReset);
      jmMenu1.add(jmiCredits);
      jmMenu1.add(jmiExit);
      jmMenu2.add(jmiColor);
      
      
      //Adding positions of X & Y for squares
      for(int x = 10; x<=310;x+=100)
      {
         fieldXPos.add(x);
         fieldYPos.add(x);
      }
      //Adding x coordinate positions for player 2
      for(int p2 = 50; p2<=350; p2+=100)
      {
         p2XPos.add(p2);
      }
      
      //GUI essentials
      quest.setJMenuBar(menubar);
      quest.add(jpCommands,"South");
      quest.setSize(406,488);
      paint = new DrawPane();
      quest.getContentPane().add(paint);
      quest.setTitle("QuizQuest");
      quest.setLocationRelativeTo(null);
      quest.setResizable(false);
      quest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      quest.setVisible(true);
      
      
      /*
      * Adding ActionListener to JButtons & JMenuItems
      */
      jbMove.addActionListener(this);
      jmiExit.addActionListener(this);
      jmiAbout.addActionListener(this);
      jmiReset.addActionListener(this);
      jmiCredits.addActionListener(this); 
      jmiColor.addActionListener(this); 
      
      
      /**
      * ActionListener on JButton jbMove
      * When pressed the current player will move 1 space
      * A object of Question will be created which will display a JFrame window with a question
      * Each question has 4 options which can be picked from
      */
      jbMove.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
               //questionUp if true disables movement until question is answered
               if(questionUp == false)
               {
                  /**
                  * player1 controls - player1 movement and questions
                  */
                  if(playerTurn == 1)
                  {
                     p1Space = p1Space + 1; //Move p1 for 1 space
                     paint.repaint(); //Repaint new position
                     if(victory == false)
                     {
                        victoryCheck(); // Check if p1 reached space 16 - wins if did
                     }
                     Question p1Q = new Question(quest,playerTurn,p1Space,p2Space,colored);//adding question class
                     paint.repaint();
                     if(colored == true && playerTurn == 2)
                     {
                        jpCommands.setBackground(Color.BLUE);//indicates player 2's turn
                     }
                     questionUp = true;//disables use of move button till the question has been answered
                  }//player 1 controls end
               
                  /**
                  * player2 controls -player 1 movement & questions
                  */
                  else if(playerTurn == 2)
                  {
                     p2Space = p2Space + 1;
                     paint.repaint();
                     if(victory == false)
                     {
                        victoryCheck();//Check if p2 reached space 16 - wins if did
                     }
                     Question p2Q = new Question(quest,playerTurn,p1Space,p2Space,colored);
                     paint.repaint();
                     if (colored == true && playerTurn == 1)
                     {
                        jpCommands.setBackground(Color.RED);//indicates player1's turn
                     }
                     questionUp = true;//disables use of move button till the question has been answereed
                  }//player2 controls end
               }//questionUp boolean check end
               else if(questionUp = true)
               {
                  JOptionPane.showMessageDialog(null,"Please answer the given question before proceeding.");
               }
            }//actionPerformed end
         });
   }//QuizQuest constructor end
   
   
   
   //Start of actionPerformed
   @Override
   public void actionPerformed(ActionEvent ae)
   {
      Object choice = ae.getSource();
      
      /*
      * Functionality for jmiExit JMenuItem
      * Closes programm when pressed
      */
      if(choice == jmiExit)
      {
         System.exit(0);
      }
      
      /**
      * Functionality for jmiAbout JMenuItem
      * Shows a sequence of messages that explain the objective and the way the game works to the players
      */
      else if (choice == jmiAbout)
      {
         JOptionPane.showMessageDialog(null,"Welcome to QuizQuest!");
         JOptionPane.showMessageDialog(null,"Your objective is to reach the finish line, while answering questions.");
         JOptionPane.showMessageDialog(null,"But these aren't any regular questions.");
         JOptionPane.showMessageDialog(null,"No, you my friend... ");
         JOptionPane.showMessageDialog(null,"You will be answering humanity's most toughest questions!");
         JOptionPane.showMessageDialog(null,"First one to the finish line will be victorious!");
         JOptionPane.showMessageDialog(null,"LOSER PAYS THE ULTIMATE PRICE OF BLOOD!");
         JOptionPane.showMessageDialog(null,"THEY SHALL LOSE THEIR LIVES!!!1");
         JOptionPane.showMessageDialog(null,"Ok...maybe not...");
         JOptionPane.showMessageDialog(null,"Press the 'Move it' button to start.");
         JOptionPane.showMessageDialog(null,"You will get a question after moving.");
         JOptionPane.showMessageDialog(null,"Answer correctly and move 2 spaces forward as a reward.");
         JOptionPane.showMessageDialog(null,"Answer incorrectly and go back 1 space.");
         JOptionPane.showMessageDialog(null,"It will be, as if you never even moved.");
         JOptionPane.showMessageDialog(null,"Anyway, hope this cleared up some stuff.");
         JOptionPane.showMessageDialog(null,"Now start moving.");
         JOptionPane.showMessageDialog(null,"Good luck & have fun!");       
      }
      /**
      * Pressing the jmiReset JMenuItem activates the reset method
      */
      else if(choice == jmiReset)
      {
         reset();
      }
      
      /**
      * Displays message about authors of the game
      */
      else if(choice == jmiCredits)
      {
         JOptionPane.showMessageDialog(null,"<html><body>Quiz Quest is made by<br>\"TrashCan Productions\"<br>- Filipa Ivankovic & Saelda Rrahi</body></html>");
      }
      
      /**
      * Functionality for jmiColor JMenuItem
      * Turns on/off the color for the board
      * Colors the squares and whether the JPanel jpCommands will change color to indicate the current player's turn
      * Starts off as turned on
      * Becomes disabled when a question has been popped up
      */
      else if (choice == jmiColor && questionUp == false)
      {
         if(colored == false)
         {
            colored = true;
            paint.repaint();
         }
         else if(colored == true)
         {
            colored = false;
            jpCommands.setBackground(null);
            paint.repaint();
         }
      }
   }//actionPerformed end
   
   
   
   //Inner class DrawPane
   class DrawPane extends JPanel
   {
      //Start of paintComponent
      public void paintComponent(Graphics g)
      {
         //Handles redrawing
         super.paintComponent(g);
         
         //Draws the black board
         g.fillRect(0, 0, 400, 400); 
         
         
         g.setColor(Color.white);
         //Cuts out 16 white space, which are the spaces players move on
         for(int i = 10; i <= 400; i+=100)
         {
            for(int j= 10; j<= 400; j+=100)
            {
               g.clearRect(i,j,80,80);
            }
         }      
         
         /**
         * If colored is enabled (controlled by jmiColor JMenuItem) turn on color for squares
         * It is turned on on start of programm
         * Manipulated in the menubar
         */
         if(colored == true)
         {
            //First column
            g.setColor(Color.pink);
            g.fillRect(10,10,80,80);
            g.fillRect(10,110,80,80);
            g.fillRect(10,210,80,80);
            g.fillRect(10,310,80,80);
            
            //Second column
            g.setColor(Color.green);
            g.fillRect(110,10,80,80);
            g.fillRect(110,110,80,80);
            g.fillRect(110,210,80,80);
            g.fillRect(110,310,80,80);
            
            //Third column
            g.setColor(Color.cyan);
            g.fillRect(210,10,80,80);
            g.fillRect(210,110,80,80);
            g.fillRect(210,210,80,80);
            g.fillRect(210,310,80,80);
            
            //Fourth column
            g.setColor(Color.yellow);
            g.fillRect(310,10,80,80);
            g.fillRect(310,110,80,80);
            g.fillRect(310,210,80,80);
            g.fillRect(310,310,80,80);
            
            //jpCommands changes color depending on which player turn it is
            if(playerTurn == 1)
            {
               jpCommands.setBackground(Color.RED);
            }
            else if(playerTurn == 2)
            {
               jpCommands.setBackground(Color.BLUE);
            }
         }//if colored == true end
         
         
         /**
         * Movement for player 1
         * One of the 4 position is taken from the fieldXPos & fieldYPos positions to set player 1's position
         */
         //First column
         if(p1Space == 1)
         {
            p1.paintIcon(this,g,fieldXPos.get(0),fieldYPos.get(0));
         }
         if (p1Space == 2)
         {
            p1.paintIcon(this,g,fieldXPos.get(0),fieldYPos.get(1));
         }
         if (p1Space == 3)
         {
            p1.paintIcon(this,g,fieldXPos.get(0),fieldYPos.get(2));
         }
         if (p1Space == 4)
         {
            p1.paintIcon(this,g,fieldXPos.get(0),fieldYPos.get(3));
         }
         //Second column
         if (p1Space == 5)
         {
            p1.paintIcon(this,g,fieldXPos.get(1),fieldYPos.get(0));
         }
         if (p1Space == 6)
         {
            p1.paintIcon(this,g,fieldXPos.get(1),fieldYPos.get(1));
         }
         if (p1Space == 7)
         {
            p1.paintIcon(this,g,fieldXPos.get(1),fieldYPos.get(2));
         }
         if (p1Space == 8)
         {
            p1.paintIcon(this,g,fieldXPos.get(1),fieldYPos.get(3));
         }
         //Third column
         if (p1Space == 9)
         {
            p1.paintIcon(this,g,fieldXPos.get(2),fieldYPos.get(0));
         }
         if (p1Space == 10)
         {
            p1.paintIcon(this,g,fieldXPos.get(2),fieldYPos.get(1));
         }
         if (p1Space == 11)
         {
            p1.paintIcon(this,g,fieldXPos.get(2),fieldYPos.get(2));
         }
         if (p1Space == 12)
         {
            p1.paintIcon(this,g,fieldXPos.get(2),fieldYPos.get(3));
         }
         //Fourth column
         if (p1Space == 13)
         {
            p1.paintIcon(this,g,fieldXPos.get(3),fieldYPos.get(0));
         }
         if (p1Space == 14)
         {
            p1.paintIcon(this,g,fieldXPos.get(3),fieldYPos.get(1));
         }
         if (p1Space == 15)
         {
            p1.paintIcon(this,g,fieldXPos.get(3),fieldYPos.get(2));
         }
         if (p1Space == 16)
         {
            p1.paintIcon(this,g,fieldXPos.get(3),fieldYPos.get(3));
         }
         
         /**
         * Movement for player2
         * Same thing as player 1, except that player 2 uses different x coordinates
         */
         if(p2Space == 1)
         {
            p2.paintIcon(this,g,p2XPos.get(0),fieldYPos.get(0));
         }
         if (p2Space == 2)
         {
            p2.paintIcon(this,g,p2XPos.get(0),fieldYPos.get(1));
         }
         if (p2Space == 3)
         {
            p2.paintIcon(this,g,p2XPos.get(0),fieldYPos.get(2));
         }
         if (p2Space == 4)
         {
            p2.paintIcon(this,g,p2XPos.get(0),fieldYPos.get(3));
         }
         //Second column
         if (p2Space == 5)
         {
            p2.paintIcon(this,g,p2XPos.get(1),fieldYPos.get(0));
         }
         if (p2Space == 6)
         {
            p2.paintIcon(this,g,p2XPos.get(1),fieldYPos.get(1));
         }
         if (p2Space == 7)
         {
            p2.paintIcon(this,g,p2XPos.get(1),fieldYPos.get(2));
         }
         if (p2Space == 8)
         {
            p2.paintIcon(this,g,p2XPos.get(1),fieldYPos.get(3));
         }
         //Third column
         if (p2Space == 9)
         {
            p2.paintIcon(this,g,p2XPos.get(2),fieldYPos.get(0));
         }
         if (p2Space == 10)
         {
            p2.paintIcon(this,g,p2XPos.get(2),fieldYPos.get(1));
         }
         if (p2Space == 11)
         {
            p2.paintIcon(this,g,p2XPos.get(2),fieldYPos.get(2));
         }
         if (p2Space == 12)
         {
            p2.paintIcon(this,g,p2XPos.get(2),fieldYPos.get(3));
         }
         //Fourth column
         if (p2Space == 13)
         {
            p2.paintIcon(this,g,p2XPos.get(3),fieldYPos.get(0));
         }
         if (p2Space == 14)
         {
            p2.paintIcon(this,g,p2XPos.get(3),fieldYPos.get(1));
         }
         if (p2Space == 15)
         {
            p2.paintIcon(this,g,p2XPos.get(3),fieldYPos.get(2));
         }
         if (p2Space == 16)
         {
            p2.paintIcon(this,g,p2XPos.get(3),fieldYPos.get(3));
         }
      }//paintComponent end
   }//DrawPane class
   
   
   
   /**
   * VictoryCheck method
   * Check if either player has reached the square space 16 (last space)
   * Game is won for that player who reached the end square 
   */
   public void victoryCheck()
   {
      if(p1Space == 16)
      {
         JOptionPane.showMessageDialog(null,"PLAYER 1 REACHED THE END SPACE & WON!");
         JOptionPane.showMessageDialog(null,"CONGRATULATIONS PLAYER 1!");
         JOptionPane.showMessageDialog(null,"PLAYER 2, YOU LOSE!");
         victory = true;  
      }
      else if(p2Space == 16)
      {
         JOptionPane.showMessageDialog(null,"PLAYER 2 REACHED THE END SPACE & WON!");
         JOptionPane.showMessageDialog(null,"CONGRATULATIONS PLAYER 2!");
         JOptionPane.showMessageDialog(null,"PLAYER 1, YOU LOSE!");
         victory = true;
      }
      if(victory == true)
      {
         JOptionPane.showMessageDialog(null,"Game is over.");
         JOptionPane.showMessageDialog(null,"You can go now.");
         JOptionPane.showMessageDialog(null,"The game will reset automatically now, if you wish to continue playing");
         reset();
      }   
   }//VictoryCheck end
   
   
   
   /**
   * reset method
   * Resets the game state to the original - players are put back to square 1
   */
   public void reset()
   {
      p1Space = 1;
      p2Space = 1;
      playerTurn = 1;
      paint.repaint();
   }
   
   
   
   /**
   * start method
   * Enters the values for the new instance of Quiz Quest for the global variables
   * This method works in conjustion with the setPosition static method
   * start method makes sure all the components are correctly displayed when a new instance is made
   */
   public void start(int _p1Space,int _p2Space, int _playerTurn, boolean _colored){
         
         playerTurn = _playerTurn;
         p1Space = _p1Space;
         p2Space =_p2Space;
         colored = _colored;
         
         if(colored == true)
         {
            if(playerTurn == 1)
            {
               jpCommands.setBackground(Color.RED);
            }
            else if(playerTurn == 2)
            {
               jpCommands.setBackground(Color.BLUE);
            }
         }
         questionUp = false; 
   }//start static method end
   
   
   
   /**
   * Static method setPosition
   * Is summoned in the Question subclass
   * After each answer make a new instance of the game with the new player's positions,playerTurn and check whether boolean colored is turned on/off
   */
   public static void setPosition(int _player1Position,int _player2Position, int _playerTurn, boolean _colored)
   {
      //this.close();
      
      //Saves into variables
      int playerTurn = _playerTurn;
      int p1Space = _player1Position;
      int p2Space = _player2Position;
      boolean colored = _colored; //Checks whether the colored option is on/off, so it can make the new window correctly
      
      //QuizQuest object
      QuizQuest objective = new QuizQuest();
      
      //Passes the new positions of players to the new Instance with player turn
      objective.start(p1Space,p2Space,playerTurn,colored);
      
      //Checks whether a player is on space 16 to declare a winner
      objective.victoryCheck();
   }//setPosition static method end


   //Main programm start
   public static void main(String [] args)
   {
      //QuizQuest object game
      QuizQuest game = new QuizQuest();
   }//Main programm end  
}//QuizQuest main class end