import java.util.ArrayList;
import java.util.List;

public class Emplyoee implements Cloneable{
    private List<String> emplist;

    public Emplyoee(){
        emplist = new ArrayList<String>();
    }
    public Emplyoee(List<String> list){
        this.emplist = list;
    }
    public void loadData(){
        emplist.add("Carl");
        emplist.add("Mark");
        emplist.add("Carla");
    }

    public List<String> getEmplist(){
        return emplist;
    }

    @Override

    public Object clone() throws CloneNotSupportedException{
        List<String> temp = new ArrayList<String>();
        for(String s : this.getEmplist()) {
            temp.add(s);
        }
        return new Emplyoee(temp);
    }
}
