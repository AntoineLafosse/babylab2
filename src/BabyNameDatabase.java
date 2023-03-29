/**
 * Manages the list of BabyNames as well as reading and writing to the files
 * with the BabyNames.
 * @author __________
 */
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
public class BabyNameDatabase {

    // TODO 1: Write the code below this line.
    public String databaseFileName;
    public ArrayList<BabyName> records;
    public File fileRecord;

    public BabyNameDatabase(String userFileName) {
        records = new ArrayList<BabyName>();
        databaseFileName = userFileName;
    }

    public ArrayList<BabyName> getRecords() {
        return records;
    }

    /**
     * Reads the csv file that holds the baby name birth data and updates
     * the records variable.
     *
     * @param filename name of the file to read from
     * @throws IOException could not find or close file
     */
    public void readRecordsFromBirthDataFile(String filename) throws IOException {
        // TODO 2: Write the code below this line.
        FileReader readFile=new FileReader(filename);
        Scanner fileReader = new Scanner(readFile);
        String fakeBabyYear = filename.replace("BabyNames", "");
        fakeBabyYear = fakeBabyYear.replace(".csv", "");
        int babyYear = Integer.parseInt(fakeBabyYear);
        while (fileReader.hasNextLine()) {
            String lineChecked = fileReader.nextLine();
            Scanner lineReader = new Scanner(lineChecked);
            lineReader.useDelimiter(",");
            if (lineReader.hasNextInt()) {
                processLineFromBirthDataFile(lineChecked, babyYear);
            }
        }
    }

    /**
     * Processes one formatted line of the csv file into baby names and
     * adds/updates the records array.
     *
     * @param line the string holding the line from the csv file
     * @param year when the data is from
     */
    public void processLineFromBirthDataFile(String line, int year) {
        // TODO 3: Write the code below this line.
        Scanner lineReader = new Scanner(line);
      //remove commas from big number
        lineReader.useDelimiter(",");
        //removes ranking number
        lineReader.next();
        //reads name
        String name = lineReader.next();
        BabyName boyName = new BabyName(name, GenderOfName.MALE);
        //Because the commas and quotations were removed. To read the number we have to combine the 2 separated numbers into one

        String checkBirths=lineReader.next();
        if (checkBirths.contains("\"")){
            String[] removeQuote=checkBirths.split("\"");
            checkBirths=removeQuote[1];
            String checkLine=lineReader.next();
            removeQuote=checkLine.split("\"");
            checkLine=removeQuote[0];
            checkBirths=""+checkBirths+checkLine;
        }
        int numBirths = Integer.parseInt(checkBirths);
        boyName.addData(numBirths,year);
        addToRecords(boyName);
        //reads second name and sets it as girl name.
        String name2 = lineReader.next();
        BabyName girlName = new BabyName(name2, GenderOfName.FEMALE);
        //reads second number of births for girl name
        String checkBirths2=lineReader.next();
        if (checkBirths2.contains("\"")){
            String[] removeQuote=checkBirths2.split("\"");
            checkBirths2=removeQuote[1];
            String checkLine2=lineReader.next();
            removeQuote=checkLine2.split("\"");
            checkLine2=removeQuote[0];
            checkBirths2=""+checkBirths2+checkLine2;
        }
        int numBirths2 = Integer.parseInt(checkBirths2);
        girlName.addData(numBirths2,year);
        addToRecords(girlName);
    }
    public void addToRecords(BabyName babies){
        for(BabyName babeName:records){
            if(babeName.getName().equals(babies.getName())){
                if(babeName.getGender() != babies.getGender()){
                    babeName.setGender(GenderOfName.NEUTRAL);
                }
                babeName.addData(babies.getBirthCounts(),babies.getYears());
                return;
            }
        }
        records.add(babies);
    }
}
