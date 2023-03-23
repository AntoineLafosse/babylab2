import java.util.ArrayList;

/**
 * Object that represents a name for a baby. Includes the sex of the name
 * and birth data for the number of babies born with that name in a
 * particular year.
 * @author __________
 */
public class BabyName {
    // TODO 1: Write the code below this line.
    public String name;
    public GenderOfName gender;
    public ArrayList<Integer> birthCounts;
    public ArrayList<Integer> years;
    public BabyName(String userName, GenderOfName userGender)
    {
        name=userName;
        gender=userGender;
        birthCounts = new ArrayList<Integer>();
        years = new ArrayList<Integer>();
    }
    public String getName(){
        return name;
    }
    public GenderOfName getGender(){
        return gender;
    }
    public ArrayList<Integer> getBirthCounts(){
        return birthCounts;
    }
    public ArrayList<Integer> getYears(){
        return years;
    }
    public void setGender(GenderOfName userGender){
        //set gender
        gender=userGender;
    }
    //todo2
    public void addData(int numBirth,int numYear){
        birthCounts.add(numBirth);
        years.add(numYear);
    }

    // TODO 3: Write the code below this line.
    public void addData(ArrayList<Integer> numBirthArr, ArrayList<Integer> numYearArr){
        for(int elem=0;elem<numYearArr.size();elem++){
            years.add(numYearArr.get(elem));
            birthCounts.add(numBirthArr.get(elem));
            if(years.get(years.size()-1)<=years.get(years.size()-2)){
                int temp2=years.get(years.size()-2);
                int temp1=birthCounts.get(years.size()-2);
                birthCounts.set(years.size()-2,numBirthArr.get(elem));
                birthCounts.set(years.size()-1,temp1);
                years.set(years.size()-2,numYearArr.get(elem));
                years.set(years.size()-1,temp2);
            }
        }
    }

    /**
     * Formats the object as a String.
     * @return formatted String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Name: " + name + "\nSex of Name: " + gender.toString().toLowerCase());
        for (int i = 0; i < years.size(); i++){
            if (i == 0){
                result.append("\nData: ");
            }
            else{
                result.append(String.format("(%d, %d) "), birthCounts.get(i), years.get(i));
            }
            if (i == years.size()-1){
                result.deleteCharAt(result.length()-1); // Remove extra space
            }
        }
        return result.toString();
    }
}