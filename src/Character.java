import java.util.Scanner;

public class Character {
    int dex=0;
    int str=0;
    int con=0;
    int wis=0;
    int intel=0;
    int cha=0;
    int currPoints = 27;
    int[] pointCost ={1,2,3,4,5,7,9,11,13,15,17,19};
    public void pointBuy(String statname, int stat) {
        //Dexterity:
        while(stat==0) {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Please enter your value for "+statname+". You have " + currPoints + " points.");
            try {
                int wantedScore = userInput.nextInt();
                if (wantedScore > 20) {
                    System.out.println("Please choose a value between 3-20");
                } else if (wantedScore < 3) {
                    System.out.println("Please choose a value between 3-20");
                } else if (wantedScore <= 8) {
                    stat = wantedScore;
                } else {
                    int tempCurrPoints = currPoints - getPointCost(wantedScore);
                    if (tempCurrPoints < 0) {
                        System.out.println("You do not have enough points for that score.");
                    } else {
                        System.out.println("Your "+statname+" is set for " + wantedScore + ".");
                        currPoints = tempCurrPoints;
                        stat = wantedScore;
                    }
                }
            } catch (Exception e) {
                System.out.println("That's not a number.");

            }
        }
    }
    public void doPointBuy(){
        pointBuy("Dexterity", dex);
        pointBuy("Strength", str);
        pointBuy("Constitution", con);
        pointBuy("Wisdom", wis);
        pointBuy("Intelligence", intel);
        pointBuy("Charisma", cha);
    }
    public int getPointCost(int wantedScore){
        return pointCost[wantedScore-9];
    }
}
