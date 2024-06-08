public class AbilityScore {
    int value;
    String name;

    public AbilityScore(int value, String name){
        this.value = value;
        this.name = name;
    }

    public int getModifier() {
        if (value > 11) {
            if (value >= 16) {
                if(value<18) {
                    return 3;
                }else {
                    if(value==20) {
                        return 5;
                    }
                    return 4;
                }
            } else {
                if (value < 14) {
                    return 1;
                } else {
                    return 2;
                }
            }
        } else if (value < 10) {
            if (value > 5) {
                if (value > 7) {
                    return -1;
                } else {
                    return -2;
                }
            } else {
                if (value == 3) {
                    return -4;
                } else {
                    return -3;
                }
            }
        } else {
            return 0;
        }
    }

    public void changeValue(int value){
        this.value= value;
    }
}
