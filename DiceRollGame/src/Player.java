public class Player extends Dice{

    private int score;
    private String name;


    public void Player(){
        Player("");

    }
    public void Player(String name){
        this.score=0;
        this.name=name;
    }



    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void addScore(int score){
        this.score += score;
    }

    public int getScore(){
        return this.score;
    }

}
