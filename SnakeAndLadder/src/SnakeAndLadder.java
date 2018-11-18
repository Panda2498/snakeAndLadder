import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Random;

public class SnakeAndLadder extends JFrame implements ActionListener{

 JLabel lblPlayer2 = new JLabel("Player 2");
 JLabel lblPlayer1 = new JLabel("Player 1");
 JButton btnPlay = new JButton("Play");
 JButton newGame = new JButton("New Game");
 
 JLabel lblPlayerNo2 = new JLabel("");
 JLabel lblPlayerNo1 = new JLabel("");
 
 JLabel lblPlayerPos2 = new JLabel("");
 JLabel lblPlayerPos1 = new JLabel("");
 
 HashMap<Integer,Integer> ladder = new HashMap<Integer,Integer>();
 HashMap<Integer,Integer> snake = new HashMap<Integer,Integer>();
 
 int playerCount1 = 1;
 int playerCount2 = 1;
 
 int w=15,h=15;
 int x=0,y=0;
 
 StringBuffer playerList2 = null;
 StringBuffer playerList1 = new StringBuffer();
  
 Random dies = new Random();
 
 public SnakeAndLadder()  throws Throwable{
  super("SnakeAndLadder");
  setLayout(null);
 // setLayout(new BorderLayout());
  setVisible(true);
  setResizable(false);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //getContentPane().setBackground(Color.LIGHT_GRAY);
  setBounds(0, 0, 780, 580);
  JLabel background=new JLabel(new ImageIcon("C:\\Users\\Jerry\\Documents\\NetBeansProjects\\SnakeAndLadder\\src\\bord2.jpg"));
  background.setBounds(0, 0, 550, 570);
  add(background);
  
  btnPlay.setBounds(620, 200, 60, 30);
  add(btnPlay);
  
  lblPlayer1.setBounds(580,250,60,30);
  add(lblPlayer1);
  
  lblPlayer2.setBounds(580, 300, 60, 30);
  add(lblPlayer2);
  
  
  lblPlayerNo1.setBounds(660, 250, 60, 30);
  
  lblPlayerNo1.setOpaque(true);
  lblPlayerNo1.setBackground(Color.BLUE);
  lblPlayerNo1.setForeground(Color.GREEN);
  add(lblPlayerNo1);
  
  lblPlayerNo2.setBounds(660, 300, 60, 30);
  lblPlayerNo2.setOpaque(true);
  lblPlayerNo2.setBackground(Color.RED);
  
  lblPlayerNo2.setForeground(Color.GREEN);
  add(lblPlayerNo2);
  
  newGame.setBounds(590, 150, 120, 30);
  add(newGame);
  coinPosition(1,playerCount2);
  lblPlayerPos2.setBounds(x, y, w, h);
  lblPlayerPos2.setOpaque(true);
  lblPlayerPos2.setBackground(Color.RED);
  background.add(lblPlayerPos2);
  
  coinPosition(2,playerCount1);
  lblPlayerPos1.setBounds(x, y, w, h);
  lblPlayerPos1.setOpaque(true);
  lblPlayerPos1.setBackground(Color.BLUE);
  background.add(lblPlayerPos1);
  
  repaint();
  
  ladder.put(3, 51);
  ladder.put(6, 27);
  ladder.put(20, 70);
  ladder.put(36, 55);
  ladder.put(63, 95);
  ladder.put(68, 98);
  
  snake.put(25, 5);
  snake.put(34, 1);
  snake.put(47, 19);
  snake.put(65, 52);
  snake.put(87, 57);
  snake.put(91, 61);
  snake.put(99, 69);
  
  newGame.addActionListener(this);
  btnPlay.addActionListener(this);
  
  
 }
  public static void main(String args[])  throws Throwable {
 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
  new SnakeAndLadder();
  }
  public void actionPerformed(ActionEvent e) {
   if (e.getSource() == newGame) {
    playerCount1 = 1;
    playerCount2 = 1;
    coinPosition(1,playerCount2);
    lblPlayerPos2.setBounds(x, y, w, h);
    coinPosition(2,playerCount1);
    lblPlayerPos1.setBounds(x, y, w, h);
    lblPlayerNo1.setText("");
    lblPlayerNo2.setText("");
    btnPlay.setVisible(true);
    repaint();
   } else if (e.getSource() == btnPlay) {
    playerList2 = new StringBuffer();
   
    int playAgain = playDies(2);
    coinPosition(2,playerCount1);
    lblPlayerPos1.setBounds(x, y, w, h);
    repaint();
    if(ladder.containsKey(playerCount1)) {
     playerCount1 = ladder.get(playerCount1);
    } else if (snake.containsKey(playerCount1)){
     playerCount1 = snake.get(playerCount1);
    }
    coinPosition(2,playerCount1);
    lblPlayerPos1.setBounds(x, y, w, h);
    repaint();
    if(playerCount1 == 100) {
     btnPlay.setVisible(false);
     JOptionPane.showMessageDialog(this, "Player1 wins");
    } else if(playAgain == 0) {
     playerList1 = new StringBuffer();
     do {
      playAgain = playDies(1);
      coinPosition(1,playerCount2);
      lblPlayerPos2.setBounds(x, y, w, h);
      repaint();
      if(ladder.containsKey(playerCount2)) {
       playerCount2 = ladder.get(playerCount2);
      } else if (snake.containsKey(playerCount2)){
       playerCount2 = snake.get(playerCount2);
      }
      coinPosition(1,playerCount2);
      lblPlayerPos2.setBounds(x, y, w, h);
      repaint();
      if(playerCount2 == 100) {
       btnPlay.setVisible(false);
       JOptionPane.showMessageDialog(this, "Player2 win");
       break;
      }
     
     } while(playAgain == 1);
    }
   
   }
  }
 
  private int playDies(int player) {
   int playAgain = 0;
   int diesResult = 0;
   while(diesResult == 0) {
    diesResult = dies.nextInt(7);
   }
   if(player == 2){
    playerList1.append(String.valueOf(diesResult));
    playerList1.append(",");
    lblPlayerNo1.setText(playerList1.toString());
    if(playerCount1+diesResult <= 100) {
     playerCount1 = playerCount1+diesResult;
     if(diesResult == 1 || diesResult == 5 || diesResult == 6) {
      playAgain = 1;
     }
    }
   } else {
    playerList2.append(String.valueOf(diesResult));
    playerList2.append(",");
    lblPlayerNo2.setText(playerList2.toString());
    if(playerCount2+diesResult <= 100) {
     playerCount2 = playerCount2+diesResult;
     if(diesResult == 1 || diesResult == 5 || diesResult == 6) {
      playAgain = 1;
     }
    }
   }
   return playAgain;
  }
 
  private void coinPosition(int compOrYou, int count) {
 
   int xpos = count%10;
   int ypos = count/10;
   if(xpos == 0) {
    xpos = 10;
    ypos = ypos-1;
   }
   if(compOrYou == 1) {
    x = 5 + (xpos*55) - 55;
   } else {
    x = 25 + (xpos*55) - 55;
   }
   y = 540 - (ypos*57);
  }
}
