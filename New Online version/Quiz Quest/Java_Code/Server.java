/**
* @authors Filipa Ivankovic, Ivo Ivankovic & Saelda Rrahi
* ISTE 121-801
* Final Project - Quiz Quest
* The Server class
*/

//Importing
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.text.*;

//Start of Server
public class Server implements ActionListener
{
   /**
   * Member variables
   * ServerSocket server
   * Vector<PrintWriter> pwList = new Vector<PrintWriter>() - writer contained for each individual client that connect to the server
   */
   private ServerSocket server;
   private Vector<ObjectOutputStream> oosList = new Vector<ObjectOutputStream>();
   private ArrayList<String> names = new ArrayList();
   private boolean gameStarted = true;
   private JFrame serverGUI;
   private JTextArea jtaArea;
   private JMenuItem jmiExit,jmiInfo,jmiCredits;
   private JTextField jtfClients, jtfGame;
   private int clientNum = 0;
   
   //Lab7Server constructor
   public Server()
   {
      //ServerScoket server
      ServerSocket server;
      
      //createGUI for Server
      createGUI();
      
      try
      {
        
         //These are just here as additional information about the server - they don't play a part in the connection between server & client
         String localhost = "LocalHost: " + InetAddress.getLocalHost() + "\n";
         String getByName = "getByName " + InetAddress.getByName("localhost") + "\n";
         System.out.println(localhost);
         jtaArea.append(localhost);
         System.out.println(getByName);
         jtaArea.append(getByName);
         
         //Add port number to ServerSocket
         server = new ServerSocket(16789);
         Socket cs = null;
         
         //Run server
         while(true)
         {
            cs = server.accept();
            System.out.println("Connection established with a client.");
            jtaArea.append("Connection established with a client.\n");
            clientNum = clientNum + 1;
            jtfClients.setText(clientNum + "");
            
            //Thread
            ThreadServer ths = new ThreadServer(cs);
            ths.start();
         }
      }
      catch(BindException be)
      {
         System.out.println("Server already running on this computer.");
         jtaArea.append("Server already running on this computer.\n");
      }
      catch(IOException io)
      {
         System.out.println("Server is dead.\n");
         jtaArea.append("Server is dead.\n");
      }   
   }//Lab7 constructor end
   
   
   /**
   * createGUI method
   * Create GUI for Server
   */
   private void createGUI()
   {
      //JFrame ServerGUI
      serverGUI = new JFrame();
      
      //Menu for serverGUI
      JMenuBar menubar = new JMenuBar();
      JMenu jmMenu1 = new JMenu("File");
      //JMenuItems 
      jmiExit = new JMenuItem("Exit");
      jmiInfo = new JMenuItem("Info");
      jmiCredits = new JMenuItem("Credits");
      
      //Adding to Menu
      jmMenu1.add(jmiInfo);
      jmMenu1.add(jmiCredits);
      jmMenu1.add(jmiExit);
      menubar.add(jmMenu1);

      //JTextArea jtaArea
      jtaArea = new JTextArea(10,10);
      jtaArea.setEditable(false);
      DefaultCaret caret = (DefaultCaret)jtaArea.getCaret();
      caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); //Automatically scrolls textarea to bottom
      JScrollPane jspArea = new JScrollPane(jtaArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      
      //JPanel jpSouth - for JTextFields  
      JPanel jpSouth = new JPanel();
      
      //JTextField jtfClients
      JLabel jlClients = new JLabel("Client number:");
      jtfClients = new JTextField(3);
      jtfClients.setEditable(false);
      jtfClients.setText(0 + "");
      
      //JTextField jtfGame
      JLabel jlGame = new JLabel("Quiz Quest active:");
      jtfGame = new JTextField(3);
      jtfGame.setEditable(false);
      jtfGame.setText("NO");
      
      //Adding to jpSouth
      jpSouth.add(jlClients);
      jpSouth.add(jtfClients);
      jpSouth.add(jlGame);
      jpSouth.add(jtfGame);
      
      //Adding to JFrame serverGUI
      serverGUI.add(jspArea,"Center");
      serverGUI.add(jpSouth,"South");
      
      //GUI essentials
      serverGUI.setTitle("Quiz Quest - Server");
      serverGUI.setSize(500,255);
      serverGUI.setJMenuBar(menubar);
      serverGUI.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// Nothing on close enables window adapter
      serverGUI.setVisible(true);
      
      //Adding ActionListenr to JMenuItems
      jmiExit.addActionListener(this);
      jmiInfo.addActionListener(this);
      jmiCredits.addActionListener(this);
      
      /**
      * Add windowListener to serverGUI for closing the game
      * public void windowClosing - gives user a warning message for closing the server
      */
      serverGUI.addWindowListener(
         new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e)
            {
               if(JOptionPane.showConfirmDialog(jmiExit,"Exiting the server will break all active games and client connections.\nProceed?","Do you wish to proceed?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
               {
                  System.exit(0);
               }
            }
         });
         
   }//createGUI method end
   
   
   /**
   * actionPerformed method
   * Add functionality to JMenuItems
   */
   @Override
   public void actionPerformed(ActionEvent ae)
   {
      Object choice = ae.getSource();
      
      //jmiExit choice
      if(choice == jmiExit)
      {
         //Warn the user that exiting the server will break the game and all connections with clients
         if(JOptionPane.showConfirmDialog(jmiExit,"Exiting the server will break all active games and client connections.\nProceed?","Do you wish to proceed?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
         {
            System.exit(0);
         }
      }
      //jmiInfo choice - display some information to user
      if(choice == jmiInfo)
      {
         JOptionPane.showMessageDialog(null,"Welcome to QuizQuest Server!");
         JOptionPane.showMessageDialog(null,"You need the Server, if you want to keep the game alive!");
         JOptionPane.showMessageDialog(null,"This is the life essense of the game itself and everyone connected!");
         JOptionPane.showMessageDialog(null,"Just keep this running in the background, so things can be nice and peachy!");
         JOptionPane.showMessageDialog(null,"Okay?");
         JOptionPane.showMessageDialog(null,"<html><font color=#ff0000>If you wish to MURDER and DESTROY everything, then KILL the server.</font></html>");
         JOptionPane.showMessageDialog(null,"Bye-bye!");
      }
      //jmiCredits choice - display credits for the game
      else if(choice == jmiCredits)
      {
         JOptionPane.showMessageDialog(null,"<html><body>Quiz Quest is made by<br>\"TrashCan Productions\"<br>- Filipa Ivankovic, Ivo Ivankovic & Saelda Rrahi</body></html>");
      }
   }//actionPerfromed end
   
   //ThreadServer inner protected class start
   protected class ThreadServer extends Thread
   {
      //Member variables
      Socket cs;
      
      //Threadserver constructor
      public ThreadServer(Socket _cs)
      {
         cs = _cs;
         System.out.println(cs);
      }//ThreadServer constructor end
      
      
      //run method
      public void run()
      {
         //Variables
         ObjectOutputStream oos;
         ObjectInputStream ois;
           
         try
         {
            //Reader
            ois = new ObjectInputStream(cs.getInputStream());
            
            //Writer
            oos = new ObjectOutputStream(cs.getOutputStream());
            
            //Add the PrintWriter to the pwList
            oosList.add(oos);
            
            /*
            for(int i = 0; i <pwList.size();i++)
            {
               System.out.println(pwList.get(i));
            }
            */
            
            //This initial connection line will be displayed when a neew client hoins the server
            String msg = ois.readUTF();
            for(ObjectOutputStream oosW: oosList)
            {
               oosW.writeUTF(msg + " has joined the chat."); //msg is client's username
               jtaArea.append(msg + " has joined the chat.\n");
               names.add(msg);
               oosW.flush();
            }
         
           /*
           * while server is active - read messages
           */
            while(true)
            {	
               //Read the login message
               String message = ois.readUTF();
               
               //when user starts game
               if(message.contains("gsc100#$%^"))
               {
                  gameStarted = true;
                  jtfGame.setText("YES");
                  
                  //send other users that game when started
                  System.out.println(message);
                  
                  //Server GUI jtaArea appending
                  String forAppend = message;
                  forAppend = forAppend.replace("gsc100#$%^,","");
                  
                  //message containing start means that the game has begun by a client
                  if(message.contains("start"))
                  {
                     forAppend = forAppend.replace(",start","");
                     jtaArea.append("Game started by client: " + forAppend + "\n\n");
                  }
                  //message containing change means an action has been exucuted by a client/player
                  else if(message.contains("change"))
                  {
                     forAppend = forAppend.replace(",change","");
                     jtaArea.append(forAppend + " executed an action.\n");
                  }
                  //message containing true means that the message contains values for player character movement/positions
                  else if(message.contains("true"))
                  {
                     //Taking out the desired value by splitting and replace teh String's parts
                     forAppend = forAppend.replace(";true","");
                     String[] splitClient = forAppend.split(",");
                     String[] splitValues = splitClient[1].split(";");
                     
                     //Display player positions and the upcoming player's turn
                     jtaArea.append("P1: " + splitValues[0] + "   P2: " + splitValues[1] + "   Next turn: P" + splitValues[2] + "\n");
                     
                     //Display the winner if one player has reached the final 16th space on the board
                     if(splitValues[0].equals("16") || splitValues[1].equals("16"))
                     {
                        //The winner will be the opposite of the "next player turn"
                        String winnerPlayer = "";
                        if(splitValues[2].equals("2"))
                        {
                           winnerPlayer = "1";
                        }
                        else
                        {
                           winnerPlayer = "2";
                        }
                        
                        //Show victory on GUI
                        jtaArea.append("Player " + winnerPlayer + " (" + splitClient[0] + ")" + " has reached the goal!\n");
                        jtaArea.append("Quiz Quest has been reset.\n\n");//Game automatically resets when a player reaches the end goal
                     }
                  }
                  
                  //sendMsg
                  sendMsg(message);
                  
                  //reset message
                  message = "";
               }
               if(message.equals("end"))//String that ends it
               {
                  ois.close();
                  oos.close();
                  cs.close();
                  break;
               }
               /**
               * When the server receives the message "RIP", the server will displa which user has left the chat
               */
               if(message.equals("RIP"))
               {
                  for(ObjectOutputStream oosW: oosList)
                  {
                     //disconnected will always be the user's (clientName) name
                     String disconnected = ois.readUTF();
                     oosW.writeUTF(disconnected + " has left the chat.");
                     jtaArea.append(disconnected + " has left the chat.\n");
                     oosW.flush();                   
                  }
               }
               else
               {
                  //sendMsg to all active clients
                  sendMsg(message);
               }
            }
           //System.out.println("RIP Client " + cs.getInetAddress() + " disconnected.");
         }
         catch(IOException io)
         {
            System.out.println("A client has disconnected from the server.");
            jtaArea.append("A client has disconnected from the server.\n");
            
            //Display new number of clients
            clientNum = clientNum - 1;
            jtfClients.setText(clientNum + "");
            
            //Game is unable to continue with less than 2 players
            if (clientNum < 2)
            {
               jtfGame.setText("DEAD");
            }
         } 
      }//run method end
   }//ThreadServer class end
   
   /**
   * sendMsg method
   * @param String msg
   * Goes through the oosList to display the newest messge to all active clients
   */
   public void sendMsg(String msg)
   {
      for(ObjectOutputStream a : oosList)
      {
         try
         {
            //Write the msg
            a.writeUTF(msg);
            a.flush();
         }
         catch(IOException ioe)
         {
            System.out.println(ioe.getMessage());
         }
      }
   }//sendMsg method end
   
   //Start of main programm
   public static void main(String [] args)
   {
      //Server object
      Server objective = new Server(); 
   }//Main programm end
}//Server main class end