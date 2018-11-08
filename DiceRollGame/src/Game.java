import javax.swing.*;
import java.util.Scanner;

public class Game extends Dice{
Player play = new Player();
    private static int goal;


        Dice d = new Dice();


    public static void main(String[]args) {
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();
        Dice dice3 = new Dice();

        Player p1 = new Player();
        Player p2 = new Player();


        p1.setName(JOptionPane.showInputDialog("Please enter player 1 name"));
        p2.setName(JOptionPane.showInputDialog("Please enter player 2 name"));
        goal = Integer.parseInt(JOptionPane.showInputDialog("Set a target"));

        while (p1.getScore() <= goal && p2.getScore() <= goal) {
            JOptionPane.showMessageDialog(null,p1.getName() + " to roll");
            System.out.println("Dice one : "+ dice1.roll());
            System.out.println("Dice two : "+ dice2.roll());
            System.out.println("Dice three : "+ dice3.roll());
            System.out.println("Score for that roll : " + diceScore(dice1, dice2, dice3));
            p1.addScore(diceScore(dice1,dice2,dice3));
            System.out.println(p1.getName() + "'s score is now " + p1.getScore());



            JOptionPane.showMessageDialog(null,p2.getName() + " to roll");
            System.out.println("Dice one : "+ dice1.roll());
            System.out.println("Dice two : "+ dice2.roll());
            System.out.println("Dice three : "+ dice3.roll());
            System.out.println("Score for that roll : " + diceScore(dice1, dice2, dice3));
            p2.addScore(diceScore(dice1,dice2,dice3));
            System.out.println(p2.getName() + "'s score is now : " + p2.getScore());
        }

        if(p1.getScore() >= goal && p2.getScore() >= goal){
            JOptionPane.showMessageDialog(null,"DRAW!");
        }
        else if(p1.getScore() >= goal) JOptionPane.showMessageDialog(null,p1.getName() + " WINS");
        else JOptionPane.showMessageDialog(null,p2.getName() + " WINS");


    }


}
