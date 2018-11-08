import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class GUI extends Application {

    private static int target =0;
    private static Player player1 = new Player();
   private static Player player2 = new Player();
   Dice dice1 = new Dice();
   Dice dice2 = new Dice();
   Dice dice3 = new Dice();


    public void start(Stage primaryStage){
        primaryStage.setTitle("Dice Game");


        VBox vbox = new VBox();

        Label label = new Label("Enter player 1's name");
        TextField txt = new TextField();

        Label label2 = new Label("Enter player 2's name");
        TextField txt2 = new TextField();

        Label label3 = new Label("Enter a target");
        TextField txt3 = new TextField();

        Button btn = new Button("Click to continue");
        vbox.getChildren().addAll(label,txt,label2,txt2,label3,txt3,btn);
        Scene initialise = new Scene(vbox,400,400);

        primaryStage.setScene(initialise);

        btn.setOnAction(x -> {
            if(!txt.getText().equals("")) player1.setName(txt.getText());
            if(!txt2.getText().equals("")) player2.setName(txt2.getText());
            if(!txt3.getText().equals("")) target = Integer.parseInt(txt3.getText());

            if(player1.getName().length() >0  && player2.getName().length()>0 && target >0){

                //Creates scene for top Pane to be used in BorderPane
                HBox topPane = new HBox();
                topPane.setPadding(new Insets(10,10,10,10));
                Label lbl_Title = new Label("Dice Game");
                topPane.getChildren().add(lbl_Title);

                //Creates scene for left Pane to be used in BorderPane
                VBox leftPane = new VBox();
                leftPane.setPadding(new Insets(10,10,10,10));
                Label lbl_Player1 = new Label("Player 1");
                Label lbl_Player1Name = new Label(player1.getName());
                Label lbl_Player1Score = new Label(player1.getScore() + "");
                Button btn_Player1Dice1 = new Button("Roll dice 1");
                Button btn_Player1Dice2 = new Button("Roll dice 2");
                Button btn_Player1Dice3 = new Button("Roll dice 3");
                Button[] player1buttons= {btn_Player1Dice1,btn_Player1Dice2,btn_Player1Dice3};
                Button btn_Player2Dice1 = new Button("Roll dice 1");
                Button btn_Player2Dice2 = new Button("Roll dice 2");
                Button btn_Player2Dice3 = new Button("Roll dice 3");
                btn_Player2Dice1.setDisable(true);
                btn_Player2Dice2.setDisable(true);
                btn_Player2Dice3.setDisable(true);
                Button[] player2buttons = {btn_Player2Dice1,btn_Player2Dice2,btn_Player2Dice3};

                btn_Player1Dice1.setOnAction(a -> {
                    diceRoll(btn_Player1Dice1, dice1);
                    checkDice(player1,lbl_Player1Score,player2buttons,dice1,dice2,dice3);
                });
                btn_Player1Dice2.setOnAction(a -> {
                    diceRoll(btn_Player1Dice2, dice2);
                    checkDice(player1,lbl_Player1Score,player2buttons,dice1,dice2,dice3);
                });
                btn_Player1Dice3.setOnAction(a -> {
                    diceRoll(btn_Player1Dice3, dice3);
                    checkDice(player1,lbl_Player1Score,player2buttons,dice1,dice2,dice3);
                });



                leftPane.getChildren().addAll(lbl_Player1,lbl_Player1Name,lbl_Player1Score,btn_Player1Dice1,btn_Player1Dice2,btn_Player1Dice3);

                //Creates scene for right Pane to be used in BorderPane
                VBox rightPane = new VBox();
                rightPane.setPadding(new Insets(10,10,10,10));
                Label lbl_Player2 = new Label("Player 2");
                Label lbl_Player2Name = new Label(player2.getName());
                Label lbl_Player2Score = new Label(player2.getScore() + "");


                //Creates scene for center Pane to be used in BorderPane
                GridPane middlePane = new GridPane();
                middlePane.setPadding(new Insets(10,10,10,10));
                Label lbl_Leader = new Label("Winning :");
                middlePane.setConstraints(lbl_Leader,3,3);
                Button btn_reset = new Button("Reset");
                middlePane.setConstraints(btn_reset,3,5);
                btn_reset.setVisible(false);

                btn_reset.setOnAction(z -> {
                    player1.setScore(0);
                    player2.setScore(0);
                    primaryStage.setScene(initialise);


                });
                middlePane.getChildren().addAll(lbl_Leader,btn_reset);


                rightPane.getChildren().addAll(lbl_Player2,lbl_Player2Name,lbl_Player2Score,btn_Player2Dice1,btn_Player2Dice2,btn_Player2Dice3);


                //Creates scene for bottom Pane to be used in BorderPane
                HBox bottomPane = new HBox();
                bottomPane.setPadding(new Insets(10,10,10,10));
                Label lbl_TargetScore = new Label("Target Score : " + target);
                bottomPane.getChildren().add(lbl_TargetScore);


                BorderPane bp = new BorderPane();
                bp.setTop(topPane);
                bp.setLeft(leftPane);
                bp.setRight(rightPane);
                bp.setCenter(middlePane);
                bp.setBottom(bottomPane);

                Scene scene = new Scene(bp,300,300);


                btn_Player2Dice1.setOnAction(a -> {
                    diceRoll(btn_Player2Dice1, dice1);
                    boolean turnOver =checkDice(player2,lbl_Player2Score,player1buttons,dice1,dice2,dice3);
                    if(turnOver) checkWin(player1,player2,lbl_Leader,primaryStage,scene,player1buttons,player2buttons,btn_reset);
                });

                btn_Player2Dice2.setOnAction(b -> {
                    diceRoll(btn_Player2Dice2, dice2);
                    boolean turnOver =checkDice(player2,lbl_Player2Score,player1buttons,dice1,dice2,dice3);
                    if(turnOver) checkWin(player1,player2,lbl_Leader,primaryStage,scene,player1buttons,player2buttons,btn_reset);
                });

                btn_Player2Dice3.setOnAction(c -> {
                    diceRoll(btn_Player2Dice3, dice3);
                    boolean turnOver =checkDice(player2,lbl_Player2Score,player1buttons,dice1,dice2,dice3);
                    if(turnOver) checkWin(player1,player2,lbl_Leader,primaryStage,scene,player1buttons,player2buttons,btn_reset);
                        });


                primaryStage.setScene(scene);
            }

        });


//        grid.setVgap(8);
  //      grid.setHgap(10);



        primaryStage.show();
    }

    public void diceRoll(Button a,Dice b){
        b.roll();
        System.out.println(b.getResult());
        a.setDisable(true);


    }

    public static void setScore(Player player, Dice a, Dice b, Dice c, Label scoreLabel){
        player.addScore(Dice.diceScore(a,b,c));
        scoreLabel.setText( player.getScore() + "");
        a.reset();
        b.reset();
        c.reset();
    }

    public static void swapTurn(Button[] array) {

        for (Button btn : array) {
            if (btn.isDisabled()) btn.setDisable(false);


        }
    }

        public static boolean checkDice(Player currentPlayer, Label playerScore, Button [] otherButtons, Dice a,Dice b, Dice c){
            if (a.getResult() != 0 && b.getResult() != 0 && c.getResult() != 0) {
                setScore(currentPlayer, a, b, c, playerScore);
                swapTurn(otherButtons);
                return true;
            }
            return false;
        }

        public static void checkWin(Player p1, Player p2,Label winner, Stage stage, Scene scene,Button[] btn1,Button[] btn2,Button but){

        if(p1.getScore()>= target || p2.getScore()>= target) {
            if (p1.getScore() >= target && p2.getScore() >= target)
                winner.setText(p1.getName() + " and " + p2.getName() + " DRAW!");
            else if (p1.getScore() >= target) {
                winner.setText(p1.getName() + " WINS!");
                System.out.println(p1.getScore());
            } else{
                winner.setText(p2.getName() + " WINS!");
                p2.getScore();
            }

            for(Button btn : btn1) btn.setDisable(true);
            for(Button btn : btn2) btn.setDisable(true);


            but.setVisible(true);
        //    stage.setScene(initialisation);
        }

        }

    public static void main(String[]args){




        launch(args);
    }
}
