import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class Character {
    AbilityScore dex= new AbilityScore(0,"Dexterity");
    AbilityScore str=new AbilityScore(0,"Strength");
    AbilityScore con= new AbilityScore(0,"Constitution");
    AbilityScore wis= new AbilityScore(0,"Wisdom");
    AbilityScore intel= new AbilityScore(0,"Intelligence");
    AbilityScore cha= new AbilityScore(0,"Charisma");
    ArrayList<AbilityScore> statList = new ArrayList<>();

    private static final Map<List<String>, List<String>> classMapping = new HashMap<>();
    static{
        classMapping.put(Arrays.asList("Strength","Constitution"),Arrays.asList("Barbarian","Fighter"));
        classMapping.put(Arrays.asList("Strength","Charisma"),Arrays.asList("Paladin"));
        classMapping.put(Arrays.asList("Dexterity","Constitution"),Arrays.asList("Fighter"));
        classMapping.put(Arrays.asList("Dexterity","Wisdom"),Arrays.asList("Ranger"));
        classMapping.put(Arrays.asList("Intelligence","Dexterity"),Arrays.asList("Wizard"));
    }
    public void setStatList() {
        statList.add(dex);
        statList.add(str);
        statList.add(con);
        statList.add(wis);
        statList.add(intel);
        statList.add(cha);
    }

    int currPoints = 27;
    int[] pointCost ={1,2,3,4,5,7,9,11,13,15,17,19};

    public void setUp() throws URISyntaxException, FileNotFoundException {
        URL resource = getClass().getResource("Races - Sheet1.csv");
        assert resource != null;
        File raceFile = new File(resource.toURI());
        setRace(raceFile);
        doPointBuy();

        suggestClass();
    }
    public void setRace(File raceFile) throws FileNotFoundException {
        Scanner raceReader = new Scanner(raceFile);
        HashMap<String, Integer[]> raceBonuses = new HashMap<>();
        String[] headers = raceReader.nextLine().split(",");
        while(raceReader.hasNextLine()) {
            String[] line = raceReader.nextLine().split(",");
            ArrayList<Integer> raceNumBonuses = new ArrayList<>();
            for(int i=1; i<line.length; i++) {
                raceNumBonuses.add(Integer.parseInt(line[i]));
            }
            Integer[] raceNumArray = raceNumBonuses.toArray(new Integer[5]);
            raceBonuses.put(line[0], raceNumArray);
        }
    }
    public void pointBuy(AbilityScore abilityScore, Scanner userInput) {
        while(abilityScore.value==0) {

            System.out.println("Please enter your value for "+ abilityScore.name+". You have " + currPoints + " points.");
            try {
                int wantedScore = userInput.nextInt();
                if (wantedScore > 20) {
                    System.out.println("Please choose a value between 3-20");
                } else if (wantedScore < 3) {
                    System.out.println("Please choose a value between 3-20");
                } else if (wantedScore <= 8) {
                    abilityScore.changeValue(wantedScore);
                } else {
                    int tempCurrPoints = currPoints - getPointCost(wantedScore);
                    if (tempCurrPoints < 0) {
                        System.out.println("You do not have enough points for that score.");
                    } else {
                        System.out.println("Your "+ abilityScore.name+" is set for " + wantedScore + ".");
                        currPoints = tempCurrPoints;
                        abilityScore.changeValue(wantedScore);
                    }
                }

            } catch (Exception e) {
                System.out.println("That's not a number.");
                userInput.nextInt();
            }

        }
    }
    public void doPointBuy(){
        Scanner userInput = new Scanner(System.in);
            pointBuy(dex, userInput);
            pointBuy(con, userInput);
            pointBuy(wis, userInput);
            pointBuy(intel, userInput);
            pointBuy(str, userInput);
            pointBuy(cha, userInput);
        setStatList();
        userInput.close();

        System.out.println("\nYour stats are:");
        for (AbilityScore abilityScore : statList) {
            System.out.println(abilityScore.name + ": "+ abilityScore.value+" ("+ abilityScore.getModifier()+")");
        }
    }
    public void suggestClass(){
        //.indexOf(element)
        //Sort the arrayList to the highest first
        Collections.sort(statList, Comparator.comparingInt(o -> o.value));
        Collections.reverse(statList);
        String topStat = statList.get(0).name;
        String secondStat = statList.get(1).name;
        System.out.println("Your recommended class is: ");
        List<String> recommendedClasses = classMapping.getOrDefault(Arrays.asList(topStat, secondStat), classMapping.get(Arrays.asList(secondStat, topStat)));

        if(recommendedClasses != null) {
            for (String className : recommendedClasses) {
                System.out.println(className);
            }
        }

    }

    public int getPointCost(int wantedScore){
        return pointCost[wantedScore-9];
    }
}
