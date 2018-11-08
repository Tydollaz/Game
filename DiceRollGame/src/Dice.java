public class Dice {

    private int result;

    public void Dice(){
        this.result =0;
    }


    public int roll(){
        this.result = (int)(6.0 * Math.random()) + 1;
        return this.result;
    }

    public static int diceScore(Dice a, Dice b,Dice c) {

        if (a.result == b.result && b.result == c.result) {
            return 18;
        } else if (a.result == b.result || a.result == c.result) {
            return a.result * 2;
        } else if (b.result == c.result) {
            return b.result * 2;
        } else return 3;

    }
        public int getResult(){
            return this.result;
        }

        public void reset(){
        this.result = 0;
        }


}
