/**
 * Manages the list of BabyNames as well as reading and writing to the files
 * with the BabyNames.
 * @author __________
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
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
        Scanner fileReader = new Scanner(databaseFileName);
        String fakeBabyYear = filename.replace("BabyNames", "");
        fakeBabyYear = fakeBabyYear.replace(".csv", "");
        int babyYear = Integer.parseInt(fakeBabyYear);
        while (fileReader.hasNextLine()) {
            String lineChecked = fileReader.nextLine();
            Scanner lineReader = new Scanner(lineChecked);
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
        lineReader.useDelimiter("    ");
        //remove quotes from all numbers for equality
        lineReader.useDelimiter("\"");
        //remove commas from big numbers
        lineReader.useDelimiter(",");
        //removes ranking number
        lineReader.next();
        //reads name
        String name = lineReader.next();
        BabyName boyName = new BabyName(name, GenderOfName.MALE);
        //Because the commas and quotations were removed. To read the number we have to combine the 2 separated numbers into one
        String numBirths = lineReader.next() + lineReader.next();
        //reads second name and sets it as girl name.
        String name2 = lineReader.next();
        BabyName girlName = new BabyName(name2, GenderOfName.FEMALE);
        //reads second number of births for girl name
        String numBirths2 = lineReader.next() + lineReader.next();
        //add all previously collected variables to records
        records.add(boyName);
        records.add(girlName);
        //searches records for duplicate girl names
        for (BabyName elem : records) {
            //if a duplicate is found, remove it
            if (elem.getName().equals(girlName)) {
                //might be a problem because numBirths2 and 1 are STRINGS not ints
                elem.addData(Integer.parseInt(numBirths2 + 1), year);
                records.remove(records.size() - 1);
                //if the original name was male, set the gender of the name to neutral;
                if (elem.getGender() == GenderOfName.MALE) {
                    elem.setGender(GenderOfName.NEUTRAL);
                }
            }
        }
        //searches records for duplicate boy names
        for (BabyName elem : records) {
            //if a duplicate is found, remove it
            if (elem.getName().equals(boyName)) {
                //might be a problem because numBirths2 and 1 are STRINGS not ints
                elem.addData(Integer.parseInt(numBirths + 1), year);
                records.remove(records.size() - 1);
                //if the original name was male, set the gender of the name to neutral;
                if (elem.getGender() == GenderOfName.FEMALE) {
                    elem.setGender(GenderOfName.NEUTRAL);
                }


            }
        }
    }
}