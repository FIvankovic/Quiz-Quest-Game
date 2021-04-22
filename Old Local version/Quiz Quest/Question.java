/**
* @authors Filipa Ivankovic & Saelda Rrahi
* ISTE 121-801
* Mini Project 1 - Quiz Quest
* Secondary class
*/

//Importing
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit.*;
import java.util.*;

/**
* This class is responsible for the question generation and question GUI
* Version: 10/03/2019
*/
public class Question extends JFrame implements ActionListener{    

   
   /**
   * Member variables   
   * Values taken from main class QuizQuest
   * questionNum - takes a random number generated from QuizQuest to display appropriate question
   * playerTurn - determines which player's turn it is
   * playerPosition - current player's position - increased/decreased depending on correct/incorrect answers
   */
   int questionNum;
   int playerTurn = 0;
   public int player1Position = 0;
   public int player2Position = 0;
   boolean colored = true;
   
   /**
   * GUI components
   * JRadioButton a,b,c,d - the question answers
   * JFrame quest - stores the main class JFrame to dispose it later
   */
   JRadioButton a,b,c,d;
   JFrame quest;
   
   //Creating constructor           
   public Question(JFrame _quest,int _playerTurn,int _player1Position, int _player2Position, boolean _colored)
   {
      //Generate random number
      questionNum = (int) (Math.random() * 11);
      //questionNum = 10;//Debug/testing tool for each question individually
      
      //Set varible values taken from the main class
      setQuestionNum(questionNum);
      this.quest = _quest;
      setPlayerTurn(_playerTurn);
      setPlayer1Position(_player1Position);
      setPlayer2Position(_player2Position);
      colored = _colored;
      
      
      //Creating Panel that will hold text 
      JPanel jpN = new JPanel();
      
      //Creting JLabel for the title of the JFrame
      JLabel jlTitle = new JLabel();
      Font fontTitle = new Font("Arial", Font.BOLD,20);
      jlTitle.setFont(fontTitle);
      
      
         
      //Adding all text in the JPanel
      jpN.add(jlTitle);
      
      //Creating another JPanel that will hold the Radio buttons
      JPanel jpN1 = new JPanel();
      
      
      //JRadioButtons
      a = new JRadioButton();
      b = new JRadioButton();
      c = new JRadioButton();
      d = new JRadioButton();
      
      
      //Adding JRadioButtons to JPanel jpN1
      jpN1.add(a);
      jpN1.add(b);
      jpN1.add(c);
      jpN1.add(d);
      
              
      //GUI essentials
      this.setLocationRelativeTo(null);
      this.add(jpN);
      this.add(jpN1);
      this.setSize(500,250);//set the size of frame 
      this.setVisible(true);//make this frame visible
      this.setDefaultCloseOperation(0); //Prevents user from closing the question through the 'X' button
      add(jpN,"North");
      add(jpN1,"South");
      
      
      //Adding ActionListener to JRadioButtons
      a.addActionListener(this);
      b.addActionListener(this);
      c.addActionListener(this);
      d.addActionListener(this);
      
      
      /**
      * Depending on the randomly generated questionNum, display the appropriate title,question and answers
      * In this version there are 11 questions
      * Each question has 4 different options that have their own scenarios
      * This means that there are 44 different scenarios depending on the player's choice
      * Some questions have only one correct answer, while others have multiple
      */
      if (questionNum == 0)
      {
         this.setTitle("Question #1 for Player " + playerTurn);
         String question1 = ("<html><br/> Where did I put my keys?<br/> </html>");
         jlTitle.setText(question1);
         a.setText("In your bag");
         b.setText("In the car");
         c.setText("In the door");
         d.setText("In the university");
      }
      else if (questionNum == 1)
      {
         this.setTitle("Question #2 for Player " + playerTurn);
         String question2 = ("<html><br/>Why do I cry myself to sleep?<br/></html>");
         jlTitle.setText(question2);
         a.setText("I don't know");
         b.setText("Somebody cut the garlic");
         c.setText("Tragic backstory");
         d.setText("Dystopia");
      }
      else if (questionNum == 2)
      {
         this.setTitle("Question #3 for Player " + playerTurn);
         String question3 = ("<html>What is the correct way to spell \"pig\"?</html>");
         jlTitle.setText(question3);
         a.setText("Pig");
         b.setText("pigg");
         c.setText("<html><i>Speak Chinese</i></html>");
         d.setText("Pigee");
      }
      else if (questionNum == 3)
      {
         fontTitle = new Font("Arial",Font.PLAIN,15);
         jlTitle.setFont(fontTitle);
         this.setSize(750,250);
         this.setTitle("Question #4 for Player " + playerTurn);
         jlTitle.setText("<html><body><div style='text-align: center;'>Dwayne is boy with very long hair.<br>He wishes to get a haircut.<br>Unfortunately, Dwayne is so poor, he can't afford a hairdresser.<br>Poor Dwayne.<br><br>How should Dwayne resolve this issue,<br>while taking into consideration that Dwayne is an idiot?</div></body></html>");
         a.setText("Use scissors, Dwayne");
         b.setText("Don't cut your hair, Dwayne");
         c.setText("Put on a hat, Dwayne");
         d.setText("Use a lawnmower, Dwayne");
      }
      else if (questionNum == 4)
      {
         fontTitle = new Font("Arial",Font.PLAIN,15);
         jlTitle.setFont(fontTitle);
         this.setTitle("Question #5 for Player " + playerTurn);
         jlTitle.setText("<html><body><div style='text-align: center;'> Everytime I go <strike>torrent</strike> <b>LEGALLY PURCHASE</b> stuff online, I get these ads.<br><br> Ads promising me that I will get a hot single girlfriend,<br> if I clicked on them.<br><br> Well, I clicked on a million of these, <br>and I still haven't received my hot single girlfriend.<br><br>What happened to her?<br></div></body></html>");
         a.setText("You are an idiot");
         b.setText("Stolen packaging");
         c.setText("Tubercolosis");
         d.setText("Ads are scams");
      }
      else if(questionNum == 5)
      {
         fontTitle = new Font("Arial",Font.PLAIN,15);
         jlTitle.setFont(fontTitle);
         this.setSize(650,380);
         this.setTitle("Question #6 for Player " + playerTurn);
         jlTitle.setText("<html><body><div style='text-align: center;'>I am concerned mother that needs help with her son's behaviour.<br><br>My son likes to spend most of his free time on his computer.<br><br>Usually, I don't bother him, but recently, I saw him visit a weird website.<br><br>When I confronted him about this, he told me that it was his<br> favorite Mongolian basket weaving website.<br><br> I didn't understand what he meant by that, but <br> I told him to not go to such weird websites.<br><br>To that he called me a \"stupid b*itch\" and laughed at me.<br><br>What should I do?<br>Should I be concerned with my child's behaviour?</div></body></html>");
         a.setText("Disown him.");
         b.setText("Beat him up.");
         c.setText("Cut his Internet connection.");
         d.setText("He is just a kid.");
      }
      else if(questionNum == 6)
      {
         this.setSize(750,150);
         this.setTitle("Question #7 for Player " + playerTurn);
         String question7 = ("I don't like you.");
         jlTitle.setText(question7);
         a.setText("This isn't even a question");
         b.setText("I don't like you either");
         c.setText("Become depressed");
         d.setText("I like the smell of sulfuric acid");
      }
      else if(questionNum == 7)
      {
         this.setSize(750,150);
         this.setTitle("Question #8 for Player " + playerTurn);
         String question8 = ("Which madman puts pineapple on their pizza?!");
         jlTitle.setText(question8);
         a.setText("<html><body>Explain how each individual has <br>their own food preferences.</body></html>");
         b.setText("A degenerate");
         c.setText("Pineapples with dreams");
         d.setText("\"Madman\" is sexist");
      }
      else if(questionNum == 8)
      {
         this.setSize(650,250);
         this.setTitle("Question #9 for Player " + playerTurn);
         jlTitle.setText("<html><body><div style='text-align: center;'><br> I want to see the kids...<br><br>Where did you put the kids?<br>There are my children aswell...<br></div></body></html>");
         a.setText("?????????");
         b.setText("Punch the lunatic");
         c.setText("The children aren't coming back...");
         d.setText("Dad?");
      }
      else if(questionNum == 9)
      {
         this.setTitle("Question #10 for Player " + playerTurn);
         jlTitle.setText("<html><body><div style='text-align: center;'><br>EHEHEHEH!<br><br>If you were injured, what would you do?<br>Are you big and stronk, or weak and little?<br><br>EHEHEHEH!</div></body></html>");
         a.setText("Deal with it");
         b.setText("Pretend like its nothing");
         c.setText("Cry");
         d.setText("Call your mom");
      }
      else if(questionNum == 10)
      {
         this.setSize(600,150);
         this.setTitle("Question #11 for Player " + playerTurn);
         String question3 = ("<html>How do I commit a crime & get away with it?</html>");
         jlTitle.setText(question3);
         a.setText("Commit another crime");
         b.setText("Confess your sins");
         c.setText("Just close your eyes");
         d.setText("Burrow yourself");
      }
      }//Question constructor end
       
       
       
       
      /**
      * actionPerformed
      * ActionEvent is given to the question answers
      * Each a,b,c or d option has their own scenario played out when pressed
      * Which are displayed depends on the random questionNum int
      */
      @Override
      public void actionPerformed(ActionEvent ae)
      {
         Object choice = ae.getSource();
         
         // A CHOICE
         if(choice == a)
         {
            //Question 1: "Where did I put my keys?" - In my bag
            if (questionNum == 0)
            {
               JOptionPane.showMessageDialog(null,"That was the first place I checked.");
               JOptionPane.showMessageDialog(null,"It's not there ya dingus.");
               answerIncorrect();
            }
            //Question 2: "Why do I cry myself to sleep?" - I don't know
            else if (questionNum == 1)
            {
               JOptionPane.showMessageDialog(null,"That ain't really helpful you know...");
               JOptionPane.showMessageDialog(null,"You are supposed to solve my problems for me.");
               answerIncorrect();
            }
            //Question 3: What is the correct way to spell "pig"? - Pig
            else if (questionNum == 2)
            {
               JOptionPane.showMessageDialog(null,"This is very clearly the incorrect way to spell pig.");
               JOptionPane.showMessageDialog(null,"Do you think that I am that dumb to put the answer to my question in the question itself?");
               JOptionPane.showMessageDialog(null,"Hint: Yes I am...");
               JOptionPane.showMessageDialog(null,"But I don't like that, so this choice is <html><font color=#ff0000>incorrect.</font></html>");
               JOptionPane.showMessageDialog(null,"Sucks to be you. Hahaha!");
               answerIncorrect();
            }
            //Question 4: Dwayne's hair - Use scissors
            else if(questionNum == 3)
            {
               JOptionPane.showMessageDialog(null,"You tell Dwayne to use a pair of scissors.");
               JOptionPane.showMessageDialog(null,"Dwayne agrees.");
               JOptionPane.showMessageDialog(null,"Dwayne takes the scissors and cuts one of his bangs.");
               JOptionPane.showMessageDialog(null,"Dwayne is amused by the hair falling to the ground when he cuts it.");
               JOptionPane.showMessageDialog(null,"Dwayne cuts more of his hair.");
               JOptionPane.showMessageDialog(null,"Dwayne cuts his hair till only a small patch of hair is left on top of his head.");
               JOptionPane.showMessageDialog(null,"Dwayne, with his sh*itty cutting skills, tries to eliminate the last patch of hair by stabbing himself with the scissors.");
               JOptionPane.showMessageDialog(null,"Dwayne, no...");
               JOptionPane.showMessageDialog(null,"Dwayne, despite the pain continues to stab the scissors into the top of his head.");
               JOptionPane.showMessageDialog(null,"Dwayne, no, stop...");
               JOptionPane.showMessageDialog(null,"Dwayne has managed to make himself bleed.");
               JOptionPane.showMessageDialog(null,"Dwayne, please...");
               JOptionPane.showMessageDialog(null,"Dwayne continues to stab the scissors into his head until he passes out.");
               JOptionPane.showMessageDialog(null,"An ambulance is called and Dwayne lands himself in the hospital.");
               JOptionPane.showMessageDialog(null,"You are convinced that Dwayne is massive f*cking idiot.");
               JOptionPane.showMessageDialog(null,"At least, he got a haircut.");
               answerCorrect();
            }
            //Question 5: Ads - You are an idiot
            else if (questionNum == 4)
            {
               JOptionPane.showMessageDialog(null,"How rude.");
               JOptionPane.showMessageDialog(null,"Here I am, trying to get a hot single girlfriend, and you are being a massive kill joy.");
               JOptionPane.showMessageDialog(null,"Oh, you know what?");
               JOptionPane.showMessageDialog(null,"I think that you are just jealous of my hot soon-not-to-be single girlfriend.");
               JOptionPane.showMessageDialog(null,"Well, not everyone can be cool as I am.");
               answerIncorrect();
            }
            //Question 6: Troubled child - Disown him
            else if(questionNum == 5)
            {
               JOptionPane.showMessageDialog(null,"That seems way too extreme.");
               JOptionPane.showMessageDialog(null,"I know that my son is problematic, but as his mother, I still love him.");
               answerIncorrect();
            }
            //Question 7: I don't like you. - This isn't even a question
            else if(questionNum == 6)
            {
               JOptionPane.showMessageDialog(null,"Smartass...");
               answerCorrect();
            }
            //Question 8: Which madman puts pineapple on their pizza?! - Food preferences
            else if(questionNum == 7)
            {
               JOptionPane.showMessageDialog(null,"I don't like it when someone is educating me on matters I'd rather stay ignorant about.");
               JOptionPane.showMessageDialog(null,"PINEAPPLES ON PIZZA ARE A CARDINAL SIN!");
               answerIncorrect();
            }
            //Question 9 : The kids - ??????
            else if(questionNum == 8)
            {
               JOptionPane.showMessageDialog(null,"<html><i>Chanelling your inner confusion, an army of questions pops out of your head.</i></html>");
               JOptionPane.showMessageDialog(null,"<html><i>The question marks form a defensive wall around you, protecting you from the lunatic.</i></html>");
               JOptionPane.showMessageDialog(null,"<html><i>Your level 90 question marks easily defeat the level 44 lunatic.</i></html>");
               JOptionPane.showMessageDialog(null,"<html><i>You are victorious.</i></html>");
               JOptionPane.showMessageDialog(null,"<html><i>Together with your question marks, you go on to become the greatest trainer.</i></html>");
               answerCorrect();
            }
            //Question 10: Injured - Deal with it
            else if(questionNum == 9)
            {
               JOptionPane.showMessageDialog(null,"<html>Oh, yu big boi? EHHHHHH?</html>");
               JOptionPane.showMessageDialog(null,"Acting all high and mighty, and sh*t!");
               JOptionPane.showMessageDialog(null,"NO!");
               answerIncorrect();
            }
            //Question 11: Crime - Commit another crime
            else if(questionNum == 10)
            {
               JOptionPane.showMessageDialog(null,"Ok, I commited another crime.");
               JOptionPane.showMessageDialog(null,"This one was very different from the first.");
               JOptionPane.showMessageDialog(null,"The authorities are all confused by this!");
               JOptionPane.showMessageDialog(null,"I think it worked!");
               JOptionPane.showMessageDialog(null,"Thanks, pal!");
               JOptionPane.showMessageDialog(null,"<html><i>Dear player, you may have commited a crime by helping a criminal, but at least you got the question right</i></html>");
               JOptionPane.showMessageDialog(null,"<html><i>It is good to know that you got the right priorities.</i></html>");
               answerCorrect();
            }
         }//A OPTIONS END
         
         
         
         // B CHOICE
         else if(choice == b)
         {
            //Question 1: "Where did I put my keys?" - In the car
            if(questionNum == 0)
            {
               JOptionPane.showMessageDialog(null,"What!?");
               JOptionPane.showMessageDialog(null,"I don't even drive.");
               JOptionPane.showMessageDialog(null,"Are you the type of person that looks down on others who don't own a car?");
               JOptionPane.showMessageDialog(null,"How rude.");
               answerIncorrect();
            }
            //Question 2: "Why do I cry myself to sleep?" - Somebody cut the garlic
            else if(questionNum == 1)
            {
               JOptionPane.showMessageDialog(null,"THOSE DAMN GARLIC CUTTERS!");
               JOptionPane.showMessageDialog(null,"IT IS ALL BECAUSE OF THE GARLIC!");
               JOptionPane.showMessageDialog(null,"IF I REMOVE IT ALL MY PROBLEMS SHALL BE RESOLVED!");
               JOptionPane.showMessageDialog(null,"...");
               JOptionPane.showMessageDialog(null,"Well I removed the garlic and I still feel like crying.");
               JOptionPane.showMessageDialog(null,"At least my room doesn't reek of garlic any longer.");
               answerCorrect();
            }
            //Question 3: What is the correct way to spell "pig"? - Pigg
            else if(questionNum == 2)
            {
               JOptionPane.showMessageDialog(null,"I like the sound of Pigg, I really do, but...");
               JOptionPane.showMessageDialog(null,"HOW DIDN'T YOU NOTICE THAT THE WORD STARTED WITH A LOWER CASE LETTER?!");
               JOptionPane.showMessageDialog(null,"SUCH THINGS ARE UNACCEPTABLE!");
               JOptionPane.showMessageDialog(null,"imagin if we wrete liek dis, yu baboon");
               JOptionPane.showMessageDialog(null,"yu failll at english!");
               answerIncorrect();
            }
            //Question 4: Dwayne - Don't cut hair
            else if(questionNum == 3)
            {
               JOptionPane.showMessageDialog(null,"You tell Dwayne to never cut his hair.");
               JOptionPane.showMessageDialog(null,"Dwayne agrees.");
               JOptionPane.showMessageDialog(null,"As time passes, Dwayne's hair keeps growing.");
               JOptionPane.showMessageDialog(null,"Dwayne's hair reaches Guiness world record length.");
               JOptionPane.showMessageDialog(null,"Sweat, induced by the summer heat makes Dwayne's hair smell like rancid ass.");
               JOptionPane.showMessageDialog(null,"Dwayne is a massive ball of hair.");
               JOptionPane.showMessageDialog(null,"His body isn't visible any longer and he is as blind as a mole.");
               JOptionPane.showMessageDialog(null,"After his death he becomes an urban legend known as the \"Hair strangler\"");
               JOptionPane.showMessageDialog(null,"Stories about the Hair strangler are told to little children, as spooky stories before bedtime.");
               JOptionPane.showMessageDialog(null,"This is your fault.");
               JOptionPane.showMessageDialog(null,"I told you that Dwayne is an idiot.");
               answerIncorrect();
            }
            //Question 5: Ads - Stolen packaging
            else if(questionNum == 4)
            {
               JOptionPane.showMessageDialog(null,"You really think that someone has stolen my hot single girlfriend away from me?");
               JOptionPane.showMessageDialog(null,"How could someone do something like this?");
               JOptionPane.showMessageDialog(null,"Never lucky :,(");
               answerCorrect();
            }
            //Question 6: Troubled child - Beat him up.
            else if (questionNum == 5)
            {
               JOptionPane.showMessageDialog(null,"I could never do that to my child.");
               JOptionPane.showMessageDialog(null,"I don't think I could even do that, if I wanted to.");
               JOptionPane.showMessageDialog(null,"My son is much bigger than me, so he could easily overpower me.");
               JOptionPane.showMessageDialog(null,"I know that he isn't a very good boy, but I want him to be happy.");
               JOptionPane.showMessageDialog(null,"As long as he is happy, then so am I");
               answerIncorrect();
            }
            //Question 7: I don't like you. - I don't like you either
            else if(questionNum == 6)
            {
               JOptionPane.showMessageDialog(null,"We have something in common then...");
               JOptionPane.showMessageDialog(null,"...");
               JOptionPane.showMessageDialog(null,"You know...");
               JOptionPane.showMessageDialog(null,"Your distaste towards me, makes me kind of happy...");
               JOptionPane.showMessageDialog(null,"I am, maybe, even... starting to like you...");
               JOptionPane.showMessageDialog(null,"NOPE!");
               JOptionPane.showMessageDialog(null,"No,no,no,no,no!");
               JOptionPane.showMessageDialog(null,"Just go forward 2 space & leave me alone!");
               answerCorrect();
            }
            //Question 8: Which madman puts pineapple on their pizza?! - A degenerate
            else if(questionNum == 7)
            {
               JOptionPane.showMessageDialog(null,"Damn right!");
               JOptionPane.showMessageDialog(null,"I see you agree with me for once!");
               JOptionPane.showMessageDialog(null,"<html><i>Agreeing with this game's logic and morality makes you question yourself.</i></html>");
               JOptionPane.showMessageDialog(null,"<html><i>\"Have I fallen so low that I call people who like pineapples on their pizza degenerates?\"</i> you question yourself.</html>");
               JOptionPane.showMessageDialog(null,"<html><font color=#ff0000>-20 Morality -10 Wisdom</font> for the rest of the game</html>");
               answerIncorrect();
            }
            //Question 9 : The kids - Punch the lunatic
            else if(questionNum == 8)
            {
               JOptionPane.showMessageDialog(null,"<html>Ready to defend yourself, you start walking towards the lunatic.</html>");
               JOptionPane.showMessageDialog(null,"<html>\"<i>Oh? You are approaching me?</i>\"says the lunatic.</html>");
               JOptionPane.showMessageDialog(null,"<html>\"<i>I can't beat the shit out of you, if I don't get closer.</i>\" you respond.</html>");
               JOptionPane.showMessageDialog(null,"The lunatic is no match for you.");
               JOptionPane.showMessageDialog(null,"In a mere second, you launch 50 punches at the guy & he is out cold!");
               JOptionPane.showMessageDialog(null,"The police is called on you, and both you and the lunatic are put in jail.");
               JOptionPane.showMessageDialog(null,"He is imprisoned for being a threat to children.");
               JOptionPane.showMessageDialog(null,"And you, because you are a threat to society.");
               JOptionPane.showMessageDialog(null,"But you don't mind this...");
               JOptionPane.showMessageDialog(null,"Because you know that children will be able to sleep soundly tonight, as that lunatic is now behind steel bars.");
               JOptionPane.showMessageDialog(null,"Damn, you are a badass!");
               answerCorrect();
            }
            //Question 10: Injured - Pretend like its nothing
            else if(questionNum == 9)
            {
               JOptionPane.showMessageDialog(null,"OHHHHHHHH!");
               JOptionPane.showMessageDialog(null,"YOU REAL STRONK!");
               JOptionPane.showMessageDialog(null,"ME SEE TRUE ME POWER IN YOUU!");
               JOptionPane.showMessageDialog(null,"EHEHEHEH!");
               answerCorrect();
            }
            //Question 11: Crime - Confess your sins
            else if(questionNum == 10)
            {
                JOptionPane.showMessageDialog(null,"Yes,you are right.");
                JOptionPane.showMessageDialog(null,"I should confess my mistakes and face the consequences of my wrongdoings.");
                JOptionPane.showMessageDialog(null,"Thanks, pal.");
                JOptionPane.showMessageDialog(null,"<html><i>Dear player, I know that this might be the more universally accepted and more morally correct choice, but you didn't really answer the question.</i></html>");
                JOptionPane.showMessageDialog(null,"<html><i>The question was how to avoid the consequences of commiting a crime, not what is morally just for you.</i></html>");
                JOptionPane.showMessageDialog(null,"<html><i>Next time you get this question, don't be a massive bootlicker, and properly answer the question.</i></html>");
                answerIncorrect();
                
            }
         }//B OPTIONS END
         
         
         
         // C CHOICE
         else if(choice == c)
         {
            //Question 1: "Where did I put my keys?" - In the door
            if(questionNum == 0)
            {
               JOptionPane.showMessageDialog(null,"OH, yes of course!");
               JOptionPane.showMessageDialog(null,"How could I have forgotten?");
               JOptionPane.showMessageDialog(null,"Now I just have to break down my door, as those were my only pair of keys.");
               JOptionPane.showMessageDialog(null,"Or call a door expert?");
               answerCorrect();
            }
            //Question 2: "Why do I cry myself to sleep?" - Tragic backstory
            else if(questionNum == 1)
            {
               JOptionPane.showMessageDialog(null,"Ah, my tragic backstory...");
               JOptionPane.showMessageDialog(null,"Sit down and let me tell you my tale.");
               JOptionPane.showMessageDialog(null,"It all began when I was born...");
               JOptionPane.showMessageDialog(null,"My father was abusive and my mother was addicted to drugs...");
               JOptionPane.showMessageDialog(null,"Unlike other children, I couldn't rely on the support of my family.");
               JOptionPane.showMessageDialog(null,"There were never there when I needed them and only used me as an outlet for their violent tendencies.");
               JOptionPane.showMessageDialog(null,"Going to school covered in bruises was a common thing for me.");
               JOptionPane.showMessageDialog(null,"Because of this I was never able to make friends.");
               JOptionPane.showMessageDialog(null,"I was always alone.");
               JOptionPane.showMessageDialog(null,"Now that I've grown...");
               JOptionPane.showMessageDialog(null,"...nothing really changed.");
               JOptionPane.showMessageDialog(null,"My parents may be gone, but their influences left scars that will never truly heal.");
               JOptionPane.showMessageDialog(null,"Scars - both on my body and mind...");
               JOptionPane.showMessageDialog(null,"They say it gets better, but it never truly does.");
               JOptionPane.showMessageDialog(null,"Everyday is the same.");
               JOptionPane.showMessageDialog(null,"As humans we aren't meant to be happy, but forge our own hapiness ourselves.");
               JOptionPane.showMessageDialog(null,"I never managed to accomplish this...");
               JOptionPane.showMessageDialog(null,"My life has always been a misery.");
               JOptionPane.showMessageDialog(null,"...");
               JOptionPane.showMessageDialog(null,"Well, that's my backstory.");
               JOptionPane.showMessageDialog(null,"Now you know...");
               JOptionPane.showMessageDialog(null,"However,...");
               JOptionPane.showMessageDialog(null,"... you didn't really answer the given question, so go back one space.");
               answerIncorrect();
            }
            //Question 3: What is the correct way to spell "pig"? - Speak Chinese
            else if(questionNum == 2)
            {
               JOptionPane.showMessageDialog(null,"I have no bloody idea what you said, but I will assume you know what you are talking about.");
               answerCorrect();
            }
            //Question 4: Dwayne's hair - Put on hat
            else if(questionNum == 3)
            {
               JOptionPane.showMessageDialog(null,"Dwayne puts on a hat making him look rather fancy.");
               JOptionPane.showMessageDialog(null,"His hair still needs to be cut.");
               JOptionPane.showMessageDialog(null,"Why did you pick hat out of all the options?");
               JOptionPane.showMessageDialog(null,"What was going through your mind?");
               JOptionPane.showMessageDialog(null,"Your way of thinking simply baffles me.");
               JOptionPane.showMessageDialog(null,"Perhaps, you might be an even bigger idiot than Dwayne.");
               answerIncorrect();
            }
            //Question 5: Ads - Tubercolosis
            else if(questionNum == 4)
            {
               JOptionPane.showMessageDialog(null,"Tuber-colosis?");
               JOptionPane.showMessageDialog(null,"She got sick?");
               JOptionPane.showMessageDialog(null,"My single hot girlfriend got tubercolosis?");
               JOptionPane.showMessageDialog(null,"Aww, geez...");
               JOptionPane.showMessageDialog(null,"Never lucky :,(");
               answerCorrect();
            }
            //question 6: Troubled child
            else if(questionNum == 5)
            {
               JOptionPane.showMessageDialog(null,"Oh, okay.");
               JOptionPane.showMessageDialog(null,"I turned off the Internet provider.");
               JOptionPane.showMessageDialog(null,"My son made weird frog-like noises in a fit of rage.");
               JOptionPane.showMessageDialog(null,"I told him that if he wants his Internet back, he needs to behave.");
               JOptionPane.showMessageDialog(null,"He was very angry at first, but later he came crying downstairs apologizing to me.");
               JOptionPane.showMessageDialog(null,"Thank you for your help.");
               answerCorrect();
            }
            //Question 7: I don't like you. - Become depressed
            else if(questionNum == 6)
            {
               JOptionPane.showMessageDialog(null,"Eyy, now.");
               JOptionPane.showMessageDialog(null,"You can't become sad, because of something like this...");
               JOptionPane.showMessageDialog(null,"Right?");
               JOptionPane.showMessageDialog(null,"Ehhhh...");
               JOptionPane.showMessageDialog(null,"...just go away from me...");
               answerIncorrect();
            }
            //Question 8: Which madman puts pineapple on their pizza?! - Pineapples with dreams
            else if(questionNum == 7)
            {
               JOptionPane.showMessageDialog(null,"<html><i>You tell an epic tale of pineapples with huge ambitions and dreams.</i></html>");
               JOptionPane.showMessageDialog(null,"<html><i>About pineapples that desire to be put on Italian delicacies instead of the usual salad.</i></html>");
               JOptionPane.showMessageDialog(null,"<html><i>Your story makes the game tear up</i> (don't ask us how this works).</html>");
               JOptionPane.showMessageDialog(null,"Wow, your story is really beautiful...");
               JOptionPane.showMessageDialog(null,"I am sorry for doubting the pineapples.");
               JOptionPane.showMessageDialog(null,"From now on, you are welcome on my pizzas, little pineapples.");
               answerCorrect();
            }
            //Question 9 : The kids - The children aren't coming back
            else if(questionNum == 8)
            {
               JOptionPane.showMessageDialog(null,"<html>\"<i>The kids aren't coming back.</i>\", you say.</hmtl>");
               JOptionPane.showMessageDialog(null,"<html>\"<i>NO!</i>\" he shouts.</hmtl>");
               JOptionPane.showMessageDialog(null,"<html>\"<i>They took the kids!</i>\", you continue.</hmtl>");
               JOptionPane.showMessageDialog(null,"<html>\"<i>STOP!</i>\", he cries out, tearing up</hmtl>");
               JOptionPane.showMessageDialog(null,"<html>\"<i>They took them and duct tapped them to a swing set in a park!</i>\", you shout loudly.</hmtl>");
               JOptionPane.showMessageDialog(null,"The man falls to the ground and starts crying.");
               JOptionPane.showMessageDialog(null,"<html>\"<i>We would found them 2 weeks later, and they were dead!</i>\", you finish.</hmtl>");
               JOptionPane.showMessageDialog(null,"The man has lost all hope.");
               JOptionPane.showMessageDialog(null,"What the f*ck...");
               JOptionPane.showMessageDialog(null,"What is wrong with you?!");
               answerIncorrect();
            }
            //Question 10: Injured - Cry
            else if(questionNum == 9)
            {
               JOptionPane.showMessageDialog(null,"HAH!");
               JOptionPane.showMessageDialog(null,"YU BIG BABY!");
               JOptionPane.showMessageDialog(null,"BABY WANT LOLLYPOP?");
               JOptionPane.showMessageDialog(null,"EHEHEH!");
               JOptionPane.showMessageDialog(null,"KEEP CRYING LITTLE BABY!");
               answerIncorrect();
            }
            //Question 11: Crime - Just close your eyes
            else if(questionNum == 10)
            {
               JOptionPane.showMessageDialog(null,"Close my eyes?");
               JOptionPane.showMessageDialog(null,"Alrighty then, I am closing my eyes.");
               JOptionPane.showMessageDialog(null,"OH!");
               JOptionPane.showMessageDialog(null,"Some people are touching me.");
               JOptionPane.showMessageDialog(null,"I'm being pushed around.");
               JOptionPane.showMessageDialog(null,"They are putting something metal on my wrists.");
               JOptionPane.showMessageDialog(null,"I think they are pushing me into a car.");
               JOptionPane.showMessageDialog(null,"Pal, where am I going?");
               JOptionPane.showMessageDialog(null,"<html><i>Dear player, the criminal has been apprehended, and put behind bars.</i></html>");
               JOptionPane.showMessageDialog(null,"<html><i>The criminal didn't get away with the crime; therefore, you answered incorrectly.</i></html>");
               answerIncorrect();
            }
         }//C OPTIONS END
         
         
         
         // D CHOICE
         else if(choice == d)
         {
            //Question 1: "Where did I put my keys?" - In university
            if(questionNum == 0)
            {
               JOptionPane.showMessageDialog(null,"University?");
               JOptionPane.showMessageDialog(null,"How do you...");
               JOptionPane.showMessageDialog(null,"How do you know I attend an university?");
               JOptionPane.showMessageDialog(null,"Are you stalking me?");
               JOptionPane.showMessageDialog(null,"Creep.");
               answerIncorrect();
            }
            //Question 2: "Why do I cry myself to sleep?" - Dystopia
            else if(questionNum == 1)
            {
               JOptionPane.showMessageDialog(null,"The sun is shinning.");
               JOptionPane.showMessageDialog(null,"The birds are chirping.");
               JOptionPane.showMessageDialog(null,"I can hear the laughter of children from my window.");
               JOptionPane.showMessageDialog(null,"I'm in my room covering myself with blankets and crying.");
               JOptionPane.showMessageDialog(null,"Unnable to afford a therapist.");
               JOptionPane.showMessageDialog(null,"No friends or family to turn to.");
               JOptionPane.showMessageDialog(null,"Oh, god what have I become...");
               JOptionPane.showMessageDialog(null,"How did I end up like this?");
               JOptionPane.showMessageDialog(null,"I still remember the good ol' days when I was a kid and the biggest challenge was facing Billy who had a much larger Yugioh card collection than me.");
               JOptionPane.showMessageDialog(null,"F*ck you,Billy...");
               JOptionPane.showMessageDialog(null,"My Dark Magician was no match for your Blue Eyes White Dragon...");
               JOptionPane.showMessageDialog(null,"As I was never able to defeat you, I went down a dark path...");
               JOptionPane.showMessageDialog(null,"YOU RUINED ME BILLY!");
               JOptionPane.showMessageDialog(null,"<html><i>As you listen to this person ramble about his childhood, you wonder...</i></hmtl>");
               JOptionPane.showMessageDialog(null,"<html><i>\"How exactly does this answer relate to dystopia?\"</i></hmtl>");
               JOptionPane.showMessageDialog(null,"<html>Which I say is a perfectly valid thing to be asking oneself.</hmtl>");
               JOptionPane.showMessageDialog(null,"And I have no intention of answering that question, but I'll let you off the hook this time, since I'm in a good mood.");
               answerCorrect();
            }
            //What is the correct way to spell "pig"? - Pigee
            else if (questionNum == 2)
            {
               JOptionPane.showMessageDialog(null,"Pigee has a nice ring to it.");
               JOptionPane.showMessageDialog(null,"Maybe, if we started calling pigs \"pigee\" bacon would taste better?");
               JOptionPane.showMessageDialog(null,"<html><i>Using science and common sence, you explain how this would not work.</i></html>");
               JOptionPane.showMessageDialog(null,"The game doesn't care.");
               JOptionPane.showMessageDialog(null,"But this is the correct answer.");
               answerCorrect();
            }
            //Question 4: Dwayne's hair - Use lawnmover
            else if(questionNum == 3)
            {
               JOptionPane.showMessageDialog(null,"You tell Dwayne to use the lawnmover.");
               JOptionPane.showMessageDialog(null,"Dwayne agrees.");
               JOptionPane.showMessageDialog(null,"Dwayne turns on the lawnmover.");
               JOptionPane.showMessageDialog(null,"Dwayne turns over the lawnmover on its side.");
               JOptionPane.showMessageDialog(null,"Dwayne pushes his head into the lawnmover's spinning blades.");
               JOptionPane.showMessageDialog(null,"Oh, god. Oh, no...");
               JOptionPane.showMessageDialog(null,"Dwayne is...");
               JOptionPane.showMessageDialog(null,"<html>Well, if I explained what happened to Dwayne in detail, this game would be pushed into <font color=#ff0000>18+</font> territory.</html>");
               JOptionPane.showMessageDialog(null,"Let's not go there.");
               JOptionPane.showMessageDialog(null,"Dwayne's hair; however, has been cut.");
               JOptionPane.showMessageDialog(null,"And a lot more...");
               answerCorrect();
            }
            //Question 5: Ads - Ads are scams
            else if(questionNum == 4)
            {
               JOptionPane.showMessageDialog(null,"Scams?");
               JOptionPane.showMessageDialog(null,"N.. no.. they are not scams!");
               JOptionPane.showMessageDialog(null,"I know that sooner or later my hot single girlfriend will be delivered to me.");
               JOptionPane.showMessageDialog(null,"Just you watch!");
               JOptionPane.showMessageDialog(null,"All that lovely lady wanted was my credit card number, the three digits on the back, and the expiration month and year");
               JOptionPane.showMessageDialog(null,"You are just jealous of my success!");
               answerIncorrect();
            }
            //Question 6: Troubled child
            else if (questionNum == 5)
            {
               JOptionPane.showMessageDialog(null,"Kid?");
               JOptionPane.showMessageDialog(null,"Oh, he isn't a kid any more.");
               JOptionPane.showMessageDialog(null,"He will be turning 36 this year.");
               JOptionPane.showMessageDialog(null,"He still lives with me, because he likes my cooking so much.");
               JOptionPane.showMessageDialog(null,"He doesn't leave his room very often and hates to take baths.");
               JOptionPane.showMessageDialog(null,"I wish he got himself a girlfriend or even a boyfriend, because I am very tired.");
               JOptionPane.showMessageDialog(null,"But I love him, and will take care him no matter what.");
               answerIncorrect();
            }
            //Question 7: I don't like you. - I like to smell sulfuric acid
            else if(questionNum == 6)
            {
               JOptionPane.showMessageDialog(null,"You are a degenerate.");
               answerIncorrect();
            }
            //Question 8: Which madman puts pineapple on their pizza?! - "Madman" is sexist
            else if(questionNum == 7)
            {
               JOptionPane.showMessageDialog(null,"<html><i>You explain how saying madman excludes women from being the culprit.</i></html>");
               JOptionPane.showMessageDialog(null,"<html><i>You explain how there are both men & women that are disgusting enough to put pineapples on their pizza.</i></html>");
               JOptionPane.showMessageDialog(null,"In that case, I should have phrased my question a little better.");
               JOptionPane.showMessageDialog(null,"I'll take this into consideration.");
               answerCorrect();
            }
            //Question 9 : The kids - Dad?
            else if(questionNum == 8)
            {
               JOptionPane.showMessageDialog(null,"<html>You look at the man and say: \"<i>Dad?</i>\".</html>");
               JOptionPane.showMessageDialog(null,"The man doesn't respond.");
               JOptionPane.showMessageDialog(null,"He walks up to you and stabs you in the kidneys");
               JOptionPane.showMessageDialog(null,"Ooof, ooof, ouchie!");
               JOptionPane.showMessageDialog(null,"You are in the hospital.");
               JOptionPane.showMessageDialog(null,"Next time, maybe don't assume that some random lunatic is your family member.");
               JOptionPane.showMessageDialog(null,"Ok?");
               answerIncorrect(); 
            }
            //Question 10: Injured - Call your mom
            else if(questionNum == 9)
            {
               JOptionPane.showMessageDialog(null,"OOOHHH!");
               JOptionPane.showMessageDialog(null,"YOU CALL MOM WHEN PROBLEM HAPPEN?");
               JOptionPane.showMessageDialog(null,"Me love my mom, so I respek!");
               JOptionPane.showMessageDialog(null,"She put water on wound and then not hurt me more!");
               JOptionPane.showMessageDialog(null,"EHEHEH!");
               answerCorrect();
            }
            //Question 11: Crime - Burrow yourself
            else if(questionNum == 10)
            {
               JOptionPane.showMessageDialog(null,"Ok,pal, I'll start digging.");
               JOptionPane.showMessageDialog(null,"I live in the underground from now on.");
               JOptionPane.showMessageDialog(null,"I don't think anyone will find me here.");
               JOptionPane.showMessageDialog(null,"Thanks, pal.");
               JOptionPane.showMessageDialog(null,"<html><i>Dear player, you should know how living underground isn't a really safe option.</i></html>");
               JOptionPane.showMessageDialog(null,"<html><i>However, the criminal seems to have played Minecraft, and should be fine.</i></html>");
               JOptionPane.showMessageDialog(null,"<html><i>Most importantly, he avoided getting caught.</i></html>");
               answerCorrect();
            }
         }//D choice options end
         
      }//actionPerformed end
     
     
      /**
      * Setter methods
      * setQuestionNum,setPlayerTurn,setPlayer1Position,setPlayer2Position
      * setQuestionNum - set the current randomly generated question
      * setPlayerTurn - set the current player's turn or the player's turn that is about to follow
      * setPlayer1Position - set Player 1's position on the board
      * setPlayer2Position - set Player 2' position on the board
      */
      public void setQuestionNum(int _questionNum)
      {
         questionNum = _questionNum;
      }
      public void setPlayerTurn(int _playerTurn)
      {
         playerTurn = _playerTurn;
      }
      public void setPlayer1Position(int _player1Position)
      {
         player1Position = _player1Position;
      }
      public void setPlayer2Position(int _player2Position)
      {
         player2Position = _player2Position;
      }
      
      
      
      /**
      * answerIncorrect method
      * If the current player entered the wrong answer this method plays out
      * Decreases the playerPosition by 1 (sending the player 1 space back)
      * It creates an instance of the QuizQuest setPosition static method which makes a new instance of the game with the new player positions
      * setPlayerTurn switches the player turn
      * At the end, the Question JFrame is disposed, aswell as the previous QuizQuest JFrame
      */
      public void answerIncorrect()
      {
         //Incorrect message
         JOptionPane.showMessageDialog(null,"<html><font color=#ff0000>INCORRECT ANSWER!</font></html>");
         
         //Check which players turn it is
         if (playerTurn == 1)
         {
           player1Position = player1Position - 1 ;
           setPlayer1Position(player1Position);
           setPlayerTurn(2);  //Give Player 2 their turn
         }
         else if(playerTurn == 2)
         {
           player2Position = player2Position - 1;
           setPlayer2Position(player2Position);
           setPlayerTurn(1); //Give Player 1 their turn
         }
         //New instance of QuizQuest is made
         QuizQuest.setPosition(player1Position,player2Position,playerTurn,colored);
         
         //Disposing
         quest.dispose();
         this.dispose();
      }//answerIncorrect method end
      
      
      /**
      * answerCorrect method
      * Same thing as answeIncorrect except if the answer is correct the player is moved 2 spaces forward
      */
      public void answerCorrect()
      {
         JOptionPane.showMessageDialog(null,"<html><font color=green>CORRECT ANSWER!</font></html>");
         
         //Check which player's turn it is
         if (playerTurn == 1)
         {
           player1Position = player1Position + 2;
           setPlayer1Position(player1Position);
           setPlayerTurn(2);  //Give Player 2 their turn
         }
         else if(playerTurn == 2)
         {
           player2Position = player2Position + 2;
           setPlayer2Position(player2Position);
           setPlayerTurn(1); //Give Player 2 their turn
         }
         
         //New instance of QuizQuest is made
         QuizQuest.setPosition(player1Position,player2Position,playerTurn,colored);
         
         //Disposing JFrames
         quest.dispose();
         this.dispose();
      }//answerCorrect method end       
}//Questione class end
