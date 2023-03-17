/**
 * Manages the list of BabyNames as well as reading and writing to the files
 * with the BabyNames.
 * @author __________
 */
public class BabyNameDatabase {

    // TODO 1: Write the code below this line.
    public String databaseFileName;
    public ArrayList<BabyName> records;
    public File fileRecord;
    public BabyNameDatabase(String userFileName){
        records=new ArrayList<BabyName>();
        databaseFileName=userFileName;
    }
    public ArrayList<BabyName> getRecords(){
        return records;
    }

    /**
     * Reads the csv file that holds the baby name birth data and updates
     * the records variable.
     * @param filename name of the file to read from
     * @throws IOException could not find or close file
     */
    public void readRecordsFromBirthDataFile(String filename) throws IOException {
        // TODO 2: Write the code below this line.
        Scanner in=new Scanner(databaseFileName);
        while(in.hasNextInt){

        }

    }

    /**
     * Processes one formatted line of the csv file into baby names and
     * adds/updates the records array.
     * @param line the string holding the line from the csv file
     * @param year when the data is from
     */
    public void processLineFromBirthDataFile(String line, int year){
        // TODO 3: Write the code below this line.

    }
}