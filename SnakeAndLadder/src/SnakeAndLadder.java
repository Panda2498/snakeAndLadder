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

 JLabel lblComp = new JLabel("Computer");
 JButton btnYou = new JButton("Play");
 JButton newGame = new JButton("New Game");
 
 JLabel lblCompNo = new JLabel("");
 JLabel lblYouNo = new JLabel("");
 
 JLabel lblCompPos = new JLabel("");
 JLabel lblYouPos = new JLabel("");
 
 HashMap<Integer,Integer> ladder = new HashMap<Integer,Integer>();
 HashMap<Integer,Integer> snake = new HashMap<Integer,Integer>();
 
 int youCount = 1;
 int compCount = 1;
 
 int w=15,h=15;
 int x=0,y=0;
 
 StringBuffer compList = null;
 StringBuffer youList = new StringBuffer();
  
 Random dies = new Random();
 
 public SnakeAndLadder()  throws Throwable{
  super("SnakeAndLadder");
  setLayout(null);
 // setLayout(new BorderLayout());
  setVisible(true);
  setResizable(false);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //getContentPane().setBackground(Color.LIGHT_GRAY);
  setBounds(0, 0, 700, 580);
  JLabel background=new JLabel(new ImageIcon("C:\\Users\\Jerry\\Documents\\NetBeansProjects\\SnakeAndLadder\\src\\bord2.jpg"));
  background.setBounds(0, -10, 550, 570);
  add(background);
  
  btnYou.setBounds(560, 200, 60, 30);
  add(btnYou);
  
  lblComp.setBounds(560, 250, 60, 30);
  add(lblComp);
  
  lblYouNo.setBounds(640, 200, 60, 30);
  lblYouNo.setOpaque(true);
  lblYouNo.setBackground(Color.BLUE);
  lblYouNo.setForeground(Color.GREEN);
  add(lblYouNo);
  
  lblCompNo.setBounds(640, 250, 60, 30);
  lblCompNo.setOpaque(true);
  lblCompNo.setBackground(Color.RED);
  lblCompNo.setForeground(Color.GREEN);
  add(lblCompNo);
  
  newGame.setBounds(560, 140, 120, 30);
  add(newGame);
  coinPosition(1,compCount);
  lblCompPos.setBounds(x, y, w, h);
  lblCompPos.setOpaque(true);
  lblCompPos.setBackground(Color.RED);
  background.add(lblCompPos);
  
  coinPosition(2,youCount);
  lblYouPos.setBounds(x, y, w, h);
  lblYouPos.setOpaque(true);
  lblYouPos.setBackground(Color.BLUE);
  background.add(lblYouPos);
  
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
  btnYou.addActionListener(this);
  
  
 }
  public static void main(String args[])  throws Throwable {
 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
  new SnakeAndLadder();
  }
  public void actionPerformed(ActionEvent e) {
   if (e.getSource() == newGame) {
    youCount = 1;
    compCount = 1;
    coinPosition(1,compCount);
    lblCompPos.setBounds(x, y, w, h);
    coinPosition(2,youCount);
    lblYouPos.setBounds(x, y, w, h);
    lblYouNo.setText("");
    lblCompNo.setText("");
    btnYou.setVisible(true);
    repaint();
   } else if (e.getSource() == btnYou) {
    compList = new StringBuffer();
   
    int playAgain = playDies(2);
    coinPosition(2,youCount);
    lblYouPos.setBounds(x, y, w, h);
    repaint();
    if(ladder.containsKey(youCount)) {
     youCount = ladder.get(youCount);
    } else if (snake.containsKey(youCount)){
     youCount = snake.get(youCount);
    }
    coinPosition(2,youCount);
    lblYouPos.setBounds(x, y, w, h);
    repaint();
    if(youCount == 100) {
     btnYou.setVisible(false);
     JOptionPane.showMessageDialog(this, "You win");
    } else if(playAgain == 0) {
     youList = new StringBuffer();
     do {
      playAgain = playDies(1);
      coinPosition(1,compCount);
      lblCompPos.setBounds(x, y, w, h);
      repaint();
      if(ladder.containsKey(compCount)) {
       compCount = ladder.get(compCount);
      } else if (snake.containsKey(compCount)){
       compCount = snake.get(compCount);
      }
      coinPosition(1,compCount);
      lblCompPos.setBounds(x, y, w, h);
      repaint();
      if(compCount == 100) {
       btnYou.setVisible(false);
       JOptionPane.showMessageDialog(this, "Computer win");
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
    youList.append(String.valueOf(diesResult));
    youList.append(",");
    lblYouNo.setText(youList.toString());
    if(youCount+diesResult <= 100) {
     youCount = youCount+diesResult;
     if(diesResult == 1 || diesResult == 5 || diesResult == 6) {
      playAgain = 1;
     }
    }
   } else {
    compList.append(String.valueOf(diesResult));
    compList.append(",");
    lblCompNo.setText(compList.toString());
    if(compCount+diesResult <= 100) {
     compCount = compCount+diesResult;
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