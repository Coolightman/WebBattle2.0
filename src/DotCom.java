import java.util.*;
public class DotCom {
    private ArrayList<String> locationCells;
    private String name;

    public void setLocationCells(ArrayList<String> locationCells){
        this.locationCells=locationCells;
    }
    public void setDotComName(String name){
        this.name=name;
    }
    public String getDotComName(){
        return name;
    }

    public String checkYourself(String userGuess){
        String result = "Мимо";
        int index = locationCells.indexOf(userGuess);
        if (index>=0){
            locationCells.remove(index);
            if (locationCells.isEmpty()){
                result = "Потопил";
                System.out.println("Ой! Вы потопили " + name + "  =(");
            }
            else
                result = "Попал";
        }
        return result;
    }
}

