/**
* @author Filipa Ivankovic & Saelda Rrahi
* ISTE 121-801
* Homework 07 - Multi-Threaded Client/Server
*/

//Importing
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//Lab7Client main class start
public class Client
{
   /**
   * JButton jbSend - button to send messages
   * JTextField jftWrite -  user inputs messages here
   * JTextArea jtaArea - client messages and server messages are displayed here
   * ObjectOutputStream oos
   * ObjectInputStream ois
   * Quiz Quest qq - object of the QuizQuest class - sent to user depending on whose turn it is
   * static String allPositions - a String that contains all the necessary information for Server and Client when updating the game's state
   * boolean gameStarted - boolean that checks whether or not the game is actually running
   * static int p1p,p2p,pturn - player 1 position, player 2 position abd player turn - changed as the game progresses
   */
   JButton jbSend, jbStart;
   public static JButton jbChange; 
   static JTextField turn = new JTextField("");
   JTextField jtfWrite;
   JTextArea jtaArea;
   static ObjectOutputStream oos;
   ObjectInputStream ois;
   QuizQuest qq;
   static String allPositions;
   private boolean gameStarted = true;
   static int p1p = 1;
   static int p2p = 1;
   static int pturn = 1;
   static boolean col = true;
   
   /**
   * String clientName - username for the client - user inputs username through Scanner object
   * String ip - ip adress taken by args[0]
   * JMenuItem jmiExit - exits GUI
   * JMenuItem jmiAbout - gives the user some information about Quiz Quests's Client
   * JFrame client - the frame
   */ 
   String clientName;
   String ip;
   JMenuItem jmiExit,jmiAbout,jmiCredits;
   JFrame client;
   
   /**
   * Client constructor
   * @param String _clientName
   * @param String _ip
   */
   public Client(String _clientName,String _ip)
   {  
      //JFrame
      client = new JFrame();
      
      //Storing clientName & ip into global variables
      ip = _ip;
      clientName = _clientName;
      
      //Start connecting
      connect();
      
      //Threader th object
      Threader th = new Threader();
      
      //JMenuBar
      JMenuBar menubar = new JMenuBar();
      JMenu jmMenu1 = new JMenu("File");
      jmiExit = new JMenuItem("Exit");
      jmiAbout = new JMenuItem("About");
      jmiCredits = new JMenuItem("Credits");
      
      //Adding to JMenubar & JMenu
      jmMenu1.add(jmiAbout);
      jmMenu1.add(jmiCredits);
      jmMenu1.add(jmiExit);
      menubar.add(jmMenu1);
      
      //JPanell jpChat
      JPanel jpChat = new JPanel();
      
      //JPanel JLabel title - added at the top of GUI as shown in example
      JPanel jpTitle = new JPanel();
      JLabel jlTitle = new JLabel("Quiz Quest Chatroom - " + clientName);
      jpTitle.add(jlTitle);
      
      //JTextField - takes in user inputs
      jtfWrite = new JTextField();
      jtfWrite.setPreferredSize( new Dimension(250, 20));
      
      //Jbutton jbSend
      jbSend = new JButton("Send");
      jbStart = new JButton("Start Game");
      jbChange = new JButton("Change");
      
      //Adding components to JPanel jpChat
      jpChat.add(jtfWrite);
      jpChat.add(jbSend);
      jpChat.add(jbStart);
      jpChat.add(jbChange);
      
      //JTextArea
      jtaArea = new JTextArea(10,30);
      jtaArea.setLineWrap(true);
      jtaArea.setWrapStyleWord(true);
      jtaArea.setEditable(false);
      
      //JScrollPane - allows the JTextArea to be scrollable
      JScrollPane jspChat = new JScrollPane(jtaArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      
      //GUI essentials
      client.add(jpTitle,"North");
      client.add(jspChat,"Center");
      client.add(jpChat,"South");
      client.setTitle("Quiz Quest Client");
      client.setSize(500,300);
      client.setVisible(true);
      client.setJMenuBar(menubar);
      client.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//Allows the windowlistener to work
      client.setLocationRelativeTo(null);
      
      //Adding actionListener from Inner protected class Threader
      jbSend.addActionListener(th);
      jbStart.addActionListener(th);
      jbChange.addActionListener(th);
      jmiExit.addActionListener(th);
      jmiAbout.addActionListener(th);
      jmiCredits.addActionListener(th);
      
      /**
      * Window listener
      * A confirmation box appears for exiting client
      * Check if the client is still active - after exit send server 'RIP' message which will trigger an event
      * The event tells which client has disconnected
      */
      client.addWindowListener(new WindowAdapter(){
         @Override
         public void windowClosing(WindowEvent e) {
            if(JOptionPane.showConfirmDialog(client,"Do you wish to exit the chat?","Close Window?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
            {
               try
               {
                  //If user picks 'YES' option on Pane
                  oos.writeUTF("RIP");
                  oos.flush();
                  oos.writeUTF(clientName);
                  oos.flush();
                  System.exit(0);
               }
               catch(IOException ioe)
               {
                  System.out.println("Unable to close programm through the window listener.");
                  System.exit(0);
               }
            }
         }
      });//windowListener end
      
      //Start Threader object
      th.start();
      
   }//Lab7Client constructor end
   
   
   /**
   * connect method
   * Start the socket for the client
   * ip is taken from args[0], port is hardcoded
   * Method is called at the beggining of constructor for Client
   */
   public void connect()
   {
      try
      {
         //Socket cs - IP adress localhost and port 16789
         Socket cs = new Socket(ip,16789);
         
         //Writer
         OutputStream os = cs.getOutputStream();
         oos = new ObjectOutputStream(os);
         
         //Reader
         InputStream is = cs.getInputStream();
         ois = new ObjectInputStream(is);
      }
      catch(UnknownHostException uhe)
      {
        System.out.println("UnknownHostException: No host.");
        uhe.printStackTrace();
      }
      catch(IOException io)
      {
         System.out.println("Error! Can't connect to server.");  
      }
   }//Connect end
   
  
   
   /**
   * Threader inner protected class extends Thread implements ActionListener
   * Reads the the messages & writes them in the JTextArea jtaArea 
   */
   protected class Threader extends Thread implements ActionListener
   {
      //Start of run method
      public void run()
      {
         try
         {
            //Write the username to the server
            oos.writeUTF(clientName);
            oos.flush();
        
            while(true)
            {
            	String msgInput = ois.readUTF();
               if(turn.getText().equals("change"))
               {
            	   try
                  {
                    oos.writeUTF("gsc100#$%^," + clientName + "," + "change");
                    oos.flush();
                    //set and change all values...
                    
                    turn.setText("");
                  }
                  catch (IOException e)
                  {
                    e.printStackTrace();
                  }
               }
               
               if(msgInput.contains("gsc100#$%^"))
               {
            	  
            	   if(gameStarted)
                  {	
            		   System.out.println("Game started.");
            		   gameStarted = false;
            		   jbStart.setEnabled(false);
            		   qq = new QuizQuest();
            		   qq.quest.dispose();
              	  	}
               
                  //jbStart.setEnabled(false);
            	  
                  String[] values = msgInput.split(",");

                  //client which didn't change turn gets to play next
                  if(values[2].equals("start") && values[1].equals(clientName))
                  {
                	  qq = qq.setPosition(p1p, p2p, pturn, col);
                  }
                  
                  //If the String containt "change", it means that a user has pressed the move button and answered a question
                  if(values[2].equals("change") && values[1].equals(clientName))
                  {
                	  
                	  try 
                    {
                       oos.writeUTF("gsc100#$%^," + clientName + "," + allPositions);
                       oos.flush();
                      
                	  }
                    catch (IOException e)
                    {
                		  e.printStackTrace();
                	  }
                	  System.out.println("Next turn.");
                	  qq.quest.dispose();  
                  }
                  //Detect ";" character - this will mean that the string contains player character movement
                  if(values[2].contains(";")&& !values[1].equals(clientName))
                  {
                     //Get the values by splitting the String
                	   String s = values[2];
                  	String[] values2 = s.split(";");
                  	System.out.println(s);
                  	
                	  int p1p = Integer.parseInt(values2[0]);
                	  int p2p = Integer.parseInt(values2[1]);
                	  int pturn = Integer.parseInt(values2[2]);
                	  boolean col = Boolean.parseBoolean(values2[3]);
                	  
                	   qq = qq.setPosition(p1p, p2p, pturn, col);
                  }
                  //for the one that send close the window

                  msgInput = "";
               }
               if(msgInput.equals(null)){
                  jtaArea.setText("Connection error\n");
               }
               if(!msgInput.equals("")) {
            	   jtaArea.append(msgInput + "\n");
               }
                
            }//while loop end
         }//try end
         catch(IOException io)
         {
            //Set 'user friendly' message for the chat after server has died
            jtaArea.setText("Unfortunately, the server is no longer with us.\n With much saddness in our hearts, we must tell you that the server has passed away.\n We are sorry!\n");
            try
            {
               oos.close();
            }
            catch(IOException ioe)
            {
               System.out.println(ioe.getMessage());
            }
         }
      }//run method end
     
      /**
       * Start of actionPerformed
       * Send the message from the JTextField jtfWrite to the JTextArea jtaArea
       * Prints the message with Print Writer
       */
      @Override
      public void actionPerformed(ActionEvent ae)
      { 
         //Choice
         Object choice = ae.getSource();
         
         //When pressing jbSend
         if(choice == jbStart)
         {//starts game
            try 
            {
               oos.writeUTF("gsc100#$%^," + clientName + ",start" );
               oos.flush();
            }
            catch (IOException e) 
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
         
         //Pressing jbChange
         if(choice == jbChange)
         {
        	   turn.setText("change");
        	   try
            {
                oos.writeUTF("");
                oos.flush();
            }
            catch (IOException e)
            {
             // TODO Auto-generated catch block
               e.printStackTrace();
            }
          }//jbChange choice end
         
         //Pressing jbSend
         if(choice == jbSend)
         {
            String msg = jtfWrite.getText(); //gets text from JTextField jtfWrite
            if(msg != null)
            {
               try
               {
            	
                  oos.writeUTF(clientName + ": " + msg); //prints msg
                  oos.flush(); //flush Print Writer
               }
               catch(IOException ioe)
               {
                  System.out.println("Unable to send client welcome message.");
               }
               jtfWrite.setText(""); //reset jtfWrite
               jtaArea.requestFocus(); //put focus on the JTextArea jtaArea
            }
         }//jbSend choice end
         
         /**
         * jmiExit - send msg 'RIP' to server - shows disconnected msg to remaining clients
         * This client's GUI terminates
         */
         if(choice == jmiExit)
         {
            try
            {
               oos.writeUTF("RIP");
               oos.flush();
               oos.writeUTF(clientName);
               oos.flush();
               System.exit(0);
            }
            catch(Exception e)
            {
               System.exit(0);
            }
         }//jmiExit choice end
         
         /**
         * jmiAbout - presents the player with info about the chat and some other stuff
         */
         if(choice == jmiAbout)
         {
            JOptionPane.showMessageDialog(null,"Welcome to the Quiz Quest Chatroom!");
            JOptionPane.showMessageDialog(null,"You are accessing this info from your Client!");
            JOptionPane.showMessageDialog(null,"Pressing the \"Start game\" button should...");
            JOptionPane.showMessageDialog(null,"Well, it should be self-explanotory...");
            JOptionPane.showMessageDialog(null,"You ain't very bright, if you don't understand self-explanatory things...");
            JOptionPane.showMessageDialog(null,"Anyway, your objective is to reach the finish line, while answering questions.");
            JOptionPane.showMessageDialog(null,"More info will be provided once you start the game.");
            JOptionPane.showMessageDialog(null,"Find it in the upper menu (below \"File\").");
            JOptionPane.showMessageDialog(null,"In this chatroom, you can write a message and send it to the other person playing with you!");
            JOptionPane.showMessageDialog(null,"That is, if you actually have another person to play with...");
            JOptionPane.showMessageDialog(null,"Hope you are a sociable person, because this game doesn't offer a partner.");
            JOptionPane.showMessageDialog(null,"You could though, awkwardly play it alone, by booting up multiple instances of the game...");
            JOptionPane.showMessageDialog(null,"Hoooooooooooow lonely~~~");
            JOptionPane.showMessageDialog(null,"Yeah, whatever...");
            JOptionPane.showMessageDialog(null,"Once you have two clients connected you can press the start button.");
            JOptionPane.showMessageDialog(null,"Two players! No more, and no less!");
            JOptionPane.showMessageDialog(null,"<html>Otherwise, you are messing with the game, and incurring <font color=#ff0000>my wrath</font></html>!");
            JOptionPane.showMessageDialog(null,"Let's avoid that, for your safety and well-being!");
            JOptionPane.showMessageDialog(null,"Have fun!");
         }
         /**
         * jmiCredits - display credits for the game
         */
         else if(choice == jmiCredits)
         {
            JOptionPane.showMessageDialog(null,"<html><body>Quiz Quest is made by<br>\"TrashCan Productions\"<br>- Filipa Ivankovic, Ivo Ivankovic & Saelda Rrahi</body></html>");
         }
      }//End of actionPerformed
   }//Threader inner protected class end
   
   /**
   * makeChange method
   * A method which sends messages to the server, updating the game's state
   */
   public static void makeChange()
   {
	   turn.setText("change");
	   try 
      {
         oos.writeUTF("");
         oos.flush(); 
      }
      catch (IOException e) 
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }//makeChange method end

   /**
   * static setPosition method
   * Takes in the p1Position, p2Position, playerTurn, boolean colored into a single String - allPositions
   */
   public static void setPosition(int player1Position, int player2Position, int playerTurn, boolean colored) 
   {
	   allPositions = player1Position + ";" + player2Position + ";" + playerTurn+ ";" + colored;	
   }//setPosition method end

    
   /**
   * Start of main programm
   * A GUI is created that functions as a login system
   * Clients must enter a valid ip & a name
   * There isn't any validation here for those two entries; this will have to be done by the users themselves
   * This login system was implemented to get rid of the scanner and arg reading portion of the client; this made this simple programm, even more simpler to use - how lovely
   * There is a menu with some JMenuItems that provide basic functions - help,credits and exit
   * Pressing the JButton jbEnter will create an instance of the Client, aswell as dispose the login system, as it is not necessary any longer
   */
   public static void main(String [] args)
   {
      //JFrame login - the "login" GUi
      JFrame login = new JFrame();
      
      //JMenubar, JMenu, JMenuItems
      JMenuBar menubar = new JMenuBar();
      JMenu jmMenu1 = new JMenu("File");
      JMenuItem jmiExit = new JMenuItem("Exit");
      JMenuItem jmiInfo = new JMenuItem("Info");
      JMenuItem jmiCredits = new JMenuItem("Credits");
      
      //Adding to Menu
      jmMenu1.add(jmiInfo);
      jmMenu1.add(jmiCredits);
      jmMenu1.add(jmiExit);
      menubar.add(jmMenu1);
      
      //JTextFields & JLabels
      JPanel jpInfo = new JPanel(new FlowLayout());
      JLabel jlIp = new JLabel("Enter Ip adress:");
      JLabel jlName = new JLabel("Enter Username:");
      JTextField jtfIp = new JTextField(15);
      JTextField jtfName = new JTextField(15);
      
      //JButton jbEnter
      JPanel jpButton1 = new JPanel();
      JButton jbEnter = new JButton("Enter");
      
      //Adding stuff to JPanels
      jpInfo.add(jlIp);
      jpInfo.add(jtfIp);
      jpInfo.add(jlName);
      jpInfo.add(jtfName);
      jpButton1.add(jbEnter);
      
      //GUI essentials
      login.pack();
      login.setLocationRelativeTo(null);
      login.setJMenuBar(menubar);
      login.add(jpInfo,"Center");
      login.add(jpButton1,"South");
      login.setTitle("Quiz Quest Login");
      login.setSize(300,170);
      login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      login.setResizable(false);
      login.setVisible(true);
      
      /**
      * Adding functionality to all JMenuItems & JButton
      * This is done through inner classing; they may be a nicer looking way, but this is the fastest
      */
      //Enter button - takes the info from the text fields to create instance of Client - its very important that the ip is correctly entered; otherwise, you have to reset the client
      jbEnter.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e)
          {
             //Get the ip & username from the JTextFields
             String ip = jtfIp.getText();
             String clientName = jtfName.getText();
             
             //Set some default values just in case, if fields left blank
             if(ip.equals(""))
             {
               ip = "localhost";
             }
             if(clientName.equals(""))
             {
               //Assign user random numeric name because having the same names leads to problems in the programm as the clientName is kind of an identification key
               int randomUsername = (int) (Math.random() * 10000);
               String rando = randomUsername + "";
               clientName = rando;
             }
             
             //Print the ip & name - this are mostly for testing purposes
             System.out.println(ip);
             System.out.println(clientName);
             
             //Dispose login GUI
             login.dispose();
             
             //Create Client object with username and ip
             Client objective = new Client(clientName,ip);
          }
        });
       //Exit JMenuItem - exits the login
       jmiExit.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e)
          {
             System.exit(0);
          }
        });
       //Info JMenuItem - provides some information on how to login in the usual Quiz Quest tone
       jmiInfo.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e)
          {
             JOptionPane.showMessageDialog(null,"Welcome to the Quiz Quest Login System!");
             JOptionPane.showMessageDialog(null,"You will need to enter a valid ip adress & a name for yourself.");
             JOptionPane.showMessageDialog(null,"Nobody cares what your stupid name is; however the ip is important.");
             JOptionPane.showMessageDialog(null,"You will need to enter the precise ip to join a specif Quiz Quest server.");
             JOptionPane.showMessageDialog(null,"To join your local server, just input: \"localhost\".");
             JOptionPane.showMessageDialog(null,"Do not have spaces in your ip & watch out for lower/upper case characters.");
             JOptionPane.showMessageDialog(null,"Accomplishing this correctly will land you in the Quiz Quest Chatroom.");
             JOptionPane.showMessageDialog(null,"Once you enter, you can find more info there.");
             JOptionPane.showMessageDialog(null,"Hopefully, this helps you out to start playing Quiz Quest.");
             JOptionPane.showMessageDialog(null,"Good luck!"); 
          }
        });
       //Credits JMenuItem - provides credits for Quiz Quest
       jmiCredits.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e)
          {
             JOptionPane.showMessageDialog(null,"<html><body>Quiz Quest is made by<br>\"TrashCan Productions\"<br>- Filipa Ivankovic, Ivo Ivankovic & Saelda Rrahi</body></html>");
          }
        });         
   }//Main programm end
}//Client main class end
